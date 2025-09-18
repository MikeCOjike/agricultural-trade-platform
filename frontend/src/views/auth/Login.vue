<template>
  <div class="login-container">
    <div class="login-form">
      <div class="login-header">
        <h1>🌾 农产品跨境贸易云平台</h1>
        <p>用户登录</p>
      </div>
      
      <el-form :model="form" :rules="rules" ref="formRef" size="large">
        <el-form-item prop="username">
          <el-input
            v-model="form.username"
            placeholder="请输入用户名"
            prefix-icon="User"
          />
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
        
        <el-form-item>
          <el-button type="primary" @click="handleLogin" :loading="loading" style="width: 100%">
            登录
          </el-button>
        </el-form-item>
      </el-form>
      
      <div class="login-footer">
        <el-link @click="$router.push('/register')">还没有账号？立即注册</el-link>
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
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()
const loading = ref(false)
const formRef = ref<FormInstance>()

const form = reactive({
  username: '',
  password: ''
})

const rules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      
      try {
        const success = await userStore.login({
          username: form.username,
          password: form.password
        })
        
        if (success) {
          router.push('/dashboard')
        }
      } catch (error) {
        console.error('登录失败:', error)
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
.login-container {
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.login-form {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;

  .login-header {
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

  .login-footer {
    text-align: center;
    margin-top: 20px;
  }
}

@media (max-width: 480px) {
  .login-form {
    padding: 20px;
    margin: 10px;
  }
}
</style>
