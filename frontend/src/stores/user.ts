import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { login as apiLogin, getCurrentUser, type User, type LoginRequest } from '@/api/user'

export const useUserStore = defineStore('user', () => {
  // 状态
  const token = ref<string>('')
  const user = ref<User | null>(null)
  const loading = ref(false)

  // 计算属性
  const isLoggedIn = computed(() => !!token.value && !!user.value)
  const userType = computed(() => user.value?.userType || null)
  const userName = computed(() => user.value?.realName || user.value?.username || '')

  // 初始化（从localStorage恢复状态）
  const initStore = () => {
    const savedToken = localStorage.getItem('token')
    const savedUser = localStorage.getItem('user')
    
    if (savedToken) {
      token.value = savedToken
    }
    
    if (savedUser) {
      try {
        user.value = JSON.parse(savedUser)
      } catch (error) {
        console.error('解析用户信息失败:', error)
        localStorage.removeItem('user')
      }
    }
  }

  // 登录
  const login = async (loginData: LoginRequest) => {
    try {
      loading.value = true
      const response = await apiLogin(loginData)
      
      if (response.code === 200 && response.data) {
        const { token: newToken, user: newUser } = response.data
        
        // 保存到状态
        token.value = newToken
        user.value = newUser
        
        // 保存到localStorage
        localStorage.setItem('token', newToken)
        localStorage.setItem('user', JSON.stringify(newUser))
        
        ElMessage.success('登录成功！')
        return true
      } else {
        ElMessage.error(response.message || '登录失败')
        return false
      }
    } catch (error: any) {
      console.error('登录失败:', error)
      ElMessage.error(error.message || '登录失败，请检查网络连接')
      return false
    } finally {
      loading.value = false
    }
  }

  // 退出登录
  const logout = () => {
    token.value = ''
    user.value = null
    
    // 清除localStorage
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    
    ElMessage.success('已退出登录')
  }

  // 获取用户信息
  const fetchUserInfo = async () => {
    if (!token.value) {
      return false
    }

    try {
      const response = await getCurrentUser()
      
      if (response.code === 200 && response.data) {
        user.value = response.data
        localStorage.setItem('user', JSON.stringify(response.data))
        return true
      } else {
        // Token可能已过期
        logout()
        return false
      }
    } catch (error) {
      console.error('获取用户信息失败:', error)
      logout()
      return false
    }
  }

  // 更新用户信息
  const updateUserInfo = (newUser: User) => {
    user.value = newUser
    localStorage.setItem('user', JSON.stringify(newUser))
  }

  // 检查权限
  const hasPermission = (requiredUserType?: string) => {
    if (!requiredUserType) return true
    return user.value?.userType === requiredUserType
  }

  // 检查是否为管理员
  const isAdmin = computed(() => {
    return user.value?.userType === 'REGULATORY_AUTHORITY'
  })

  return {
    // 状态
    token,
    user,
    loading,
    
    // 计算属性
    isLoggedIn,
    userType,
    userName,
    isAdmin,
    
    // 方法
    initStore,
    login,
    logout,
    fetchUserInfo,
    updateUserInfo,
    hasPermission
  }
})
