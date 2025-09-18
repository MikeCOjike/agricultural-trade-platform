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
        
        <el-form-item prop="userType">
          <el-select v-model="form.userType" placeholder="请选择用户类型" style="width: 100%">
            <el-option label="出口企业" value="EXPORT_ENTERPRISE" />
            <el-option label="进口企业" value="IMPORT_ENTERPRISE" />
            <el-option label="物流服务商" value="LOGISTICS_PROVIDER" />
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
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'

const router = useRouter()
const loading = ref(false)
const formRef = ref<FormInstance>()

const form = reactive({
  username: '',
  email: '',
  userType: '',
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
  
  await formRef.value.validate((valid) => {
    if (valid) {
      loading.value = true
      
      // 模拟注册请求
      setTimeout(() => {
        loading.value = false
        ElMessage.success('注册成功！请登录')
        router.push('/login')
      }, 1000)
    }
  })
}
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
