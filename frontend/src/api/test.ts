import request from '@/utils/request'

export interface HealthResponse {
  status: string
  timestamp: string
  message: string
  version: string
}

// 健康检查
export const healthCheck = () => {
  return request<HealthResponse>({
    url: '/test/health',
    method: 'get'
  })
}

// Hello World
export const helloWorld = () => {
  return request<string>({
    url: '/test/hello',
    method: 'get'
  })
}
