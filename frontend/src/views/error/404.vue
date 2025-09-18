<template>
  <div class="error-container">
    <div class="error-content">
      <div class="error-image">
        <div class="number">404</div>
      </div>
      <div class="error-info">
        <h1>页面不存在</h1>
        <p>抱歉，您访问的页面不存在或已被删除</p>
        <div class="error-actions">
          <el-button type="primary" @click="goHome">
            <el-icon><House /></el-icon>
            返回首页
          </el-button>
          <el-button @click="goBack">
            <el-icon><Back /></el-icon>
            返回上页
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.push('/')
}

const goBack = () => {
  router.go(-1)
}
</script>

<style lang="scss" scoped>
.error-container {
  height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.error-content {
  text-align: center;
  max-width: 500px;

  .error-image {
    margin-bottom: 40px;

    .number {
      font-size: 120px;
      font-weight: bold;
      color: #409eff;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
      margin-bottom: 20px;
    }
  }

  .error-info {
    h1 {
      font-size: 32px;
      color: #333;
      margin-bottom: 16px;
    }

    p {
      font-size: 16px;
      color: #666;
      margin-bottom: 40px;
      line-height: 1.5;
    }

    .error-actions {
      display: flex;
      justify-content: center;
      gap: 16px;
      flex-wrap: wrap;
    }
  }
}

@media (max-width: 480px) {
  .error-content {
    .error-image .number {
      font-size: 80px;
    }

    .error-info {
      h1 {
        font-size: 24px;
      }

      .error-actions {
        flex-direction: column;
        align-items: center;

        .el-button {
          width: 200px;
        }
      }
    }
  }
}
</style>
