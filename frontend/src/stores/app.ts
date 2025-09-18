import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAppStore = defineStore('app', () => {
  const loading = ref(false)
  const title = ref('农产品跨境贸易云平台')
  const version = ref('1.0.0')
  const theme = ref<'light' | 'dark'>('light')
  
  // 初始化应用
  const initApp = () => {
    console.log(`正在初始化${title.value} v${version.value}...`)
    // 可以在这里加载用户配置、主题等
  }
  
  // 设置加载状态
  const setLoading = (status: boolean) => {
    loading.value = status
  }
  
  // 切换主题
  const toggleTheme = () => {
    theme.value = theme.value === 'light' ? 'dark' : 'light'
    document.documentElement.className = theme.value
  }
  
  return {
    loading,
    title,
    version,
    theme,
    initApp,
    setLoading,
    toggleTheme
  }
})
