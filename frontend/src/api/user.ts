import request from '@/utils/request'

// 用户类型枚举
export enum UserType {
  EXPORT_ENTERPRISE = 'EXPORT_ENTERPRISE',
  IMPORT_ENTERPRISE = 'IMPORT_ENTERPRISE', 
  LOGISTICS_PROVIDER = 'LOGISTICS_PROVIDER',
  REGULATORY_AUTHORITY = 'REGULATORY_AUTHORITY'
}

// 用户状态枚举
export enum UserStatus {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  LOCKED = 'LOCKED'
}

// 用户信息接口
export interface User {
  id: number
  username: string
  email: string
  phone?: string
  realName: string
  userType: UserType
  status: UserStatus
  avatarUrl?: string
  lastLoginTime?: string
  createdAt: string
}

// 注册请求接口
export interface RegisterRequest {
  username: string
  password: string
  email: string
  phone?: string
  realName: string
  userType: UserType
}

// 登录请求接口
export interface LoginRequest {
  username: string
  password: string
}

// 登录响应接口
export interface LoginResponse {
  token: string
  tokenType: string
  user: User
}

// 分页查询参数
export interface UserQueryParams {
  page?: number
  size?: number
  sort?: string
  direction?: 'asc' | 'desc'
  userType?: UserType
  keyword?: string
}

// 分页响应接口
export interface PageResponse<T> {
  content: T[]
  totalElements: number
  totalPages: number
  size: number
  number: number
  first: boolean
  last: boolean
}

/**
 * 用户注册
 */
export const register = (data: RegisterRequest) => {
  return request<User>({
    url: '/users/register',
    method: 'post',
    data
  })
}

/**
 * 用户登录
 */
export const login = (data: LoginRequest) => {
  return request<LoginResponse>({
    url: '/users/login',
    method: 'post',
    data
  })
}

/**
 * 获取当前用户信息
 */
export const getCurrentUser = () => {
  return request<User>({
    url: '/users/profile',
    method: 'get'
  })
}

/**
 * 分页查询用户
 */
export const getUsers = (params: UserQueryParams = {}) => {
  return request<PageResponse<User>>({
    url: '/users',
    method: 'get',
    params
  })
}

/**
 * 根据ID查询用户
 */
export const getUserById = (id: number) => {
  return request<User>({
    url: `/users/${id}`,
    method: 'get'
  })
}

/**
 * 更新用户状态
 */
export const updateUserStatus = (id: number, status: UserStatus) => {
  return request({
    url: `/users/${id}/status`,
    method: 'put',
    params: { status }
  })
}

/**
 * 删除用户
 */
export const deleteUser = (id: number) => {
  return request({
    url: `/users/${id}`,
    method: 'delete'
  })
}

/**
 * 获取用户类型选项
 */
export const getUserTypeOptions = () => {
  return [
    { label: '出口企业', value: UserType.EXPORT_ENTERPRISE },
    { label: '进口企业', value: UserType.IMPORT_ENTERPRISE },
    { label: '物流服务商', value: UserType.LOGISTICS_PROVIDER },
    { label: '监管机构', value: UserType.REGULATORY_AUTHORITY }
  ]
}

/**
 * 获取用户状态选项
 */
export const getUserStatusOptions = () => {
  return [
    { label: '正常', value: UserStatus.ACTIVE, type: 'success' },
    { label: '未激活', value: UserStatus.INACTIVE, type: 'warning' },
    { label: '已锁定', value: UserStatus.LOCKED, type: 'danger' }
  ]
}
