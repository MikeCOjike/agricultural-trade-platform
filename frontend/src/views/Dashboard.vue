<template>
  <div class="dashboard">
    <el-container>
      <el-header class="header">
        <div class="header-left">
          <h1>🌾 农产品跨境贸易云平台</h1>
          <el-tag type="success">v{{ appStore.version }}</el-tag>
        </div>
        <div class="header-right">
          <el-button @click="appStore.toggleTheme()">
            <el-icon><Moon v-if="appStore.theme === 'light'" /><Sunny v-else /></el-icon>
          </el-button>
          <el-button type="primary" @click="$router.push('/login')">登录</el-button>
        </div>
      </el-header>
      
      <el-main class="main">
        <div class="welcome-section">
          <el-card class="welcome-card">
            <template #header>
              <div class="card-header">
                <span>🚀 欢迎使用农产品跨境贸易云平台</span>
              </div>
            </template>
            
            <div class="welcome-content">
              <p class="description">
                基于Spring Boot + Vue3的数字化贸易服务解决方案
              </p>
              <p class="features">
                ✨ 智能化报关 | 🔍 全程溯源 | 🌐 多语言支持 | 📊 数据驱动 | ✅ 合规保障
              </p>
              
              <div class="action-buttons">
                <el-button type="primary" size="large" @click="testConnection">
                  <el-icon><Connection /></el-icon>
                  测试后端连接
                </el-button>
                <el-button size="large" @click="$router.push('/register')">
                  <el-icon><User /></el-icon>
                  立即注册
                </el-button>
              </div>
            </div>
          </el-card>
        </div>
        
        <div class="features-section">
          <el-row :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="feature in features" :key="feature.title">
              <el-card class="feature-card" shadow="hover">
                <div class="feature-icon">{{ feature.icon }}</div>
                <h3>{{ feature.title }}</h3>
                <p>{{ feature.description }}</p>
              </el-card>
            </el-col>
          </el-row>
        </div>
        
        <div class="status-section" v-if="systemStatus">
          <el-card>
            <template #header>
              <span>系统状态</span>
            </template>
            <el-descriptions :column="2" border>
              <el-descriptions-item label="系统状态">
                <el-tag :type="systemStatus.status === 'UP' ? 'success' : 'danger'">
                  {{ systemStatus.status === 'UP' ? '正常运行' : '异常' }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="系统版本">
                {{ systemStatus.version }}
              </el-descriptions-item>
              <el-descriptions-item label="检查时间">
                {{ systemStatus.timestamp }}
              </el-descriptions-item>
              <el-descriptions-item label="系统信息">
                {{ systemStatus.message }}
              </el-descriptions-item>
            </el-descriptions>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useAppStore } from '@/stores/app'
import { healthCheck } from '@/api/test'
import type { HealthResponse } from '@/api/test'

const appStore = useAppStore()
const systemStatus = ref<HealthResponse | null>(null)

const features = [
  {
    icon: '👥',
    title: '用户管理',
    description: '多角色用户注册认证，权限管理'
  },
  {
    icon: '📋',
    title: '资质管理',
    description: '企业资质证书管理，到期提醒'
  },
  {
    icon: '📦',
    title: '报关核验',
    description: 'HS编码匹配，报关单生成'
  },
  {
    icon: '📄',
    title: '合同管理',
    description: '多语言合同模板，电子签名'
  },
  {
    icon: '🔍',
    title: '溯源管理',
    description: '产品全程溯源，质量追踪'
  },
  {
    icon: '📊',
    title: '订单管理',
    description: '订单全流程管理，数据分析'
  }
]

const testConnection = async () => {
  try {
    appStore.setLoading(true)
    const response = await healthCheck()
    systemStatus.value = response.data
    ElMessage.success('后端连接成功！')
  } catch (error) {
    console.error('连接测试失败:', error)
    ElMessage.error('后端连接失败，请检查后端服务是否启动')
  } finally {
    appStore.setLoading(false)
  }
}

onMounted(() => {
  // 页面加载时自动测试连接
  testConnection()
})
</script>

<style lang="scss" scoped>
.dashboard {
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
}

.header {
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;

  .header-left {
    display: flex;
    align-items: center;
    gap: 12px;

    h1 {
      margin: 0;
      font-size: 24px;
      color: #409eff;
    }
  }

  .header-right {
    display: flex;
    align-items: center;
    gap: 12px;
  }
}

.main {
  padding: 20px;
}

.welcome-section {
  margin-bottom: 30px;

  .welcome-card {
    text-align: center;

    .card-header {
      font-size: 18px;
      font-weight: bold;
    }

    .welcome-content {
      .description {
        font-size: 16px;
        color: #666;
        margin: 20px 0;
      }

      .features {
        font-size: 14px;
        color: #999;
        margin: 20px 0;
      }

      .action-buttons {
        margin-top: 30px;
        display: flex;
        justify-content: center;
        gap: 16px;
        flex-wrap: wrap;
      }
    }
  }
}

.features-section {
  margin-bottom: 30px;

  .feature-card {
    text-align: center;
    margin-bottom: 20px;
    transition: transform 0.3s ease;

    &:hover {
      transform: translateY(-5px);
    }

    .feature-icon {
      font-size: 48px;
      margin-bottom: 16px;
    }

    h3 {
      margin: 0 0 12px 0;
      color: #333;
    }

    p {
      margin: 0;
      color: #666;
      font-size: 14px;
    }
  }
}

.status-section {
  margin-bottom: 20px;
}

@media (max-width: 768px) {
  .header {
    .header-left h1 {
      font-size: 18px;
    }
  }

  .welcome-content .action-buttons {
    flex-direction: column;
    align-items: center;
  }
}
</style>
