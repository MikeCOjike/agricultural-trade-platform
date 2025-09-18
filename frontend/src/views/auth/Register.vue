<template>
  <div class="register-container">
    <div class="register-form">
      <div class="register-header">
        <h1>🌾 农产品跨境贸易云平台</h1>
        <p>用户注册</p>
      </div>
      
      <el-form :model="form" :rules="rules" ref="formRef" size="large">
        <el-form-item prop="username">
          <el-input
            v-model="form.username"
            placeholder="请输入用户名"
            prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item prop="email">
          <el-input
            v-model="form.email"
            placeholder="请输入邮箱"
            prefix-icon="Message"
          />
        </el-form-item>
        
        <el-form-item prop="realName">
          <el-input
            v-model="form.realName"
            placeholder="请输入真实姓名"
            prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item prop="userType">
          <el-select v-model="form.userType" placeholder="请选择用户类型" style="width: 100%">
            <el-option 
              v-for="option in userTypeOptions" 
              :key="option.value"
              :label="option.label" 
              :value="option.value" 
            />
          </el-select>
        </el-form-item>
        
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item prop="confirmPassword">
          <el-input
            v-model="form.confirmPassword"
            type="password"
            placeholder="请确认密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="handleRegister" :loading="loading" style="width: 100%">
            注册
          </el-button>
        </el-form-item>
      </el-form>
      
      <div class="register-footer">
        <el-link @click="$router.push('/login')">已有账号？立即登录</el-link>
        <el-divider />
        <el-link @click="$router.push('/dashboard')">返回首页</el-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { register, getUserTypeOptions, type UserType } from '@/api/user'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()
const loading = ref(false)
const formRef = ref<FormInstance>()
const userTypeOptions = getUserTypeOptions()

const form = reactive({
  username: '',
  email: '',
  realName: '',
  userType: '' as UserType | '',
  password: '',
  confirmPassword: ''
})

const validateConfirmPassword = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

const rules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  realName: [
    { required: true, message: '请输入真实姓名', trigger: 'blur' }
  ],
  userType: [
    { required: true, message: '请选择用户类型', trigger: 'change' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { validator: validateConfirmPassword, trigger: 'blur' }
  ]
}

const handleRegister = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      
      try {
        const response = await register({
          username: form.username,
          email: form.email,
          realName: form.realName,
          userType: form.userType as UserType,
          password: form.password
        })
        
        if (response.code === 200) {
          ElMessage.success('注册成功！请登录')
          router.push('/login')
        } else {
          ElMessage.error(response.message || '注册失败')
        }
      } catch (error: any) {
        console.error('注册失败:', error)
        ElMessage.error(error.message || '注册失败，请检查网络连接')
      } finally {
        loading.value = false
      }
    }
  })
}

// 页面加载时检查是否已登录
onMounted(() => {
  if (userStore.isLoggedIn) {
    router.push('/dashboard')
  }
})
</script>

<style lang="scss" scoped>
.register-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.register-form {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;

  .register-header {
    text-align: center;
    margin-bottom: 30px;

    h1 {
      color: #333;
      margin-bottom: 8px;
      font-size: 24px;
    }

    p {
      color: #666;
      font-size: 14px;
    }
  }

  .register-footer {
    text-align: center;
    margin-top: 20px;
  }
}

@media (max-width: 480px) {
  .register-form {
    padding: 20px;
    margin: 10px;
  }
}
</style>
