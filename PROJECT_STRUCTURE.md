# 项目结构说明

本文档详细说明了农产品跨境贸易云平台的完整项目结构。

## 📁 完整目录结构

```
agricultural-trade-platform/
├── README.md                          # 项目说明文档
├── LICENSE                            # MIT开源协议
├── CONTRIBUTING.md                    # 贡献指南
├── CHANGELOG.md                       # 更新日志
├── PROJECT_STRUCTURE.md               # 项目结构说明（本文件）
├── .gitignore                         # Git忽略文件配置
├── env.example                        # 环境变量配置示例
├── docker-compose.yml                 # Docker编排配置
│
├── backend/                           # Spring Boot后端
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/agritrade/platform/
│   │   │   │       ├── AgriTradePlatformApplication.java  # 主启动类
│   │   │   │       ├── config/                            # 配置类
│   │   │   │       │   ├── SecurityConfig.java
│   │   │   │       │   ├── RedisConfig.java
│   │   │   │       │   ├── SwaggerConfig.java
│   │   │   │       │   └── WebConfig.java
│   │   │   │       ├── controller/                        # 控制器层
│   │   │   │       │   ├── AuthController.java
│   │   │   │       │   ├── UserController.java
│   │   │   │       │   ├── QualificationController.java
│   │   │   │       │   ├── CustomsController.java
│   │   │   │       │   ├── ContractController.java
│   │   │   │       │   ├── TradeController.java
│   │   │   │       │   ├── TraceabilityController.java
│   │   │   │       │   └── OrderController.java
│   │   │   │       ├── service/                           # 业务逻辑层
│   │   │   │       │   ├── impl/                          # 服务实现类
│   │   │   │       │   ├── AuthService.java
│   │   │   │       │   ├── UserService.java
│   │   │   │       │   ├── QualificationService.java
│   │   │   │       │   ├── CustomsService.java
│   │   │   │       │   ├── ContractService.java
│   │   │   │       │   ├── TradeService.java
│   │   │   │       │   ├── TraceabilityService.java
│   │   │   │       │   └── OrderService.java
│   │   │   │       ├── repository/                        # 数据访问层
│   │   │   │       │   ├── UserRepository.java
│   │   │   │       │   ├── QualificationRepository.java
│   │   │   │       │   ├── CustomsRepository.java
│   │   │   │       │   ├── ContractRepository.java
│   │   │   │       │   ├── TradeRepository.java
│   │   │   │       │   ├── TraceabilityRepository.java
│   │   │   │       │   └── OrderRepository.java
│   │   │   │       ├── entity/                            # 实体类
│   │   │   │       │   ├── User.java
│   │   │   │       │   ├── Qualification.java
│   │   │   │       │   ├── CustomsDeclaration.java
│   │   │   │       │   ├── Contract.java
│   │   │   │       │   ├── TradeRecord.java
│   │   │   │       │   ├── TraceabilityRecord.java
│   │   │   │       │   └── Order.java
│   │   │   │       ├── dto/                               # 数据传输对象
│   │   │   │       │   ├── request/                       # 请求DTO
│   │   │   │       │   └── response/                      # 响应DTO
│   │   │   │       ├── util/                              # 工具类
│   │   │   │       │   ├── JwtUtil.java
│   │   │   │       │   ├── RedisUtil.java
│   │   │   │       │   ├── FileUtil.java
│   │   │   │       │   └── HttpUtil.java
│   │   │   │       ├── exception/                         # 异常处理
│   │   │   │       │   ├── GlobalExceptionHandler.java
│   │   │   │       │   └── BusinessException.java
│   │   │   │       └── common/                            # 公共类
│   │   │   │           ├── Result.java                    # 统一返回结果
│   │   │   │           ├── Constants.java                 # 常量类
│   │   │   │           └── PageResult.java                # 分页结果
│   │   │   └── resources/
│   │   │       ├── application.yml                        # 主配置文件
│   │   │       ├── application-dev.yml                    # 开发环境配置
│   │   │       ├── application-prod.yml                   # 生产环境配置
│   │   │       ├── application-docker.yml                 # Docker环境配置
│   │   │       ├── mapper/                                # MyBatis映射文件
│   │   │       ├── static/                                # 静态资源
│   │   │       └── templates/                             # 模板文件
│   │   └── test/                                          # 测试代码
│   │       └── java/
│   │           └── com/agritrade/platform/
│   │               ├── controller/                        # 控制器测试
│   │               ├── service/                           # 服务测试
│   │               └── repository/                        # 数据访问测试
│   ├── pom.xml                                            # Maven配置文件
│   ├── Dockerfile                                         # Docker构建文件
│   └── mvnw, mvnw.cmd                                     # Maven包装器
│
├── frontend/                          # Vue3前端
│   ├── public/                                            # 公共静态资源
│   │   ├── index.html                                     # HTML模板
│   │   ├── favicon.ico                                    # 网站图标
│   │   └── logo.png                                       # Logo图片
│   ├── src/
│   │   ├── main.ts                                        # 应用入口文件
│   │   ├── App.vue                                        # 根组件
│   │   ├── components/                                    # 公共组件
│   │   │   ├── layout/                                    # 布局组件
│   │   │   │   ├── Header.vue
│   │   │   │   ├── Sidebar.vue
│   │   │   │   └── Footer.vue
│   │   │   ├── common/                                    # 通用组件
│   │   │   │   ├── Loading.vue
│   │   │   │   ├── Pagination.vue
│   │   │   │   └── Upload.vue
│   │   │   └── business/                                  # 业务组件
│   │   │       ├── UserForm.vue
│   │   │       ├── QualificationCard.vue
│   │   │       ├── CustomsForm.vue
│   │   │       ├── ContractEditor.vue
│   │   │       └── TraceabilityQR.vue
│   │   ├── views/                                         # 页面视图
│   │   │   ├── auth/                                      # 认证页面
│   │   │   │   ├── Login.vue
│   │   │   │   └── Register.vue
│   │   │   ├── dashboard/                                 # 仪表板
│   │   │   │   └── Dashboard.vue
│   │   │   ├── user/                                      # 用户管理
│   │   │   │   ├── UserList.vue
│   │   │   │   └── UserProfile.vue
│   │   │   ├── qualification/                             # 资质管理
│   │   │   │   ├── QualificationList.vue
│   │   │   │   └── QualificationDetail.vue
│   │   │   ├── customs/                                   # 报关核验
│   │   │   │   ├── CustomsList.vue
│   │   │   │   └── HSCodeMatch.vue
│   │   │   ├── contract/                                  # 合同管理
│   │   │   │   ├── ContractList.vue
│   │   │   │   └── ContractEditor.vue
│   │   │   ├── trade/                                     # 贸易数据
│   │   │   │   ├── TradeAnalysis.vue
│   │   │   │   └── PriceMonitor.vue
│   │   │   ├── traceability/                              # 溯源管理
│   │   │   │   ├── TraceabilityList.vue
│   │   │   │   └── TraceabilityDetail.vue
│   │   │   └── order/                                     # 订单管理
│   │   │       ├── OrderList.vue
│   │   │       └── OrderDetail.vue
│   │   ├── stores/                                        # Pinia状态管理
│   │   │   ├── index.ts                                   # Store入口
│   │   │   ├── auth.ts                                    # 认证状态
│   │   │   ├── user.ts                                    # 用户状态
│   │   │   └── app.ts                                     # 应用状态
│   │   ├── router/                                        # Vue Router路由
│   │   │   ├── index.ts                                   # 路由配置
│   │   │   └── guards.ts                                  # 路由守卫
│   │   ├── api/                                           # API接口
│   │   │   ├── index.ts                                   # API入口
│   │   │   ├── auth.ts                                    # 认证接口
│   │   │   ├── user.ts                                    # 用户接口
│   │   │   ├── qualification.ts                          # 资质接口
│   │   │   ├── customs.ts                                 # 报关接口
│   │   │   ├── contract.ts                                # 合同接口
│   │   │   ├── trade.ts                                   # 贸易接口
│   │   │   ├── traceability.ts                            # 溯源接口
│   │   │   └── order.ts                                   # 订单接口
│   │   ├── utils/                                         # 工具函数
│   │   │   ├── request.ts                                 # HTTP请求封装
│   │   │   ├── storage.ts                                 # 存储工具
│   │   │   ├── validate.ts                                # 验证工具
│   │   │   └── format.ts                                  # 格式化工具
│   │   ├── types/                                         # TypeScript类型定义
│   │   │   ├── api.ts                                     # API类型
│   │   │   ├── user.ts                                    # 用户类型
│   │   │   └── common.ts                                  # 通用类型
│   │   ├── styles/                                        # 样式文件
│   │   │   ├── index.css                                  # 主样式
│   │   │   ├── variables.css                              # CSS变量
│   │   │   └── components/                                # 组件样式
│   │   └── assets/                                        # 静态资源
│   │       ├── images/                                    # 图片资源
│   │       ├── icons/                                     # 图标资源
│   │       └── fonts/                                     # 字体资源
│   ├── package.json                                       # npm配置文件
│   ├── package-lock.json                                  # npm锁定文件
│   ├── vite.config.ts                                     # Vite配置
│   ├── tsconfig.json                                      # TypeScript配置
│   ├── .eslintrc.js                                       # ESLint配置
│   ├── .prettierrc                                        # Prettier配置
│   ├── Dockerfile                                         # Docker构建文件
│   └── nginx.conf                                         # Nginx配置
│
├── mobile/                            # 移动端应用（Flutter）
│   ├── lib/                                               # Dart源码
│   ├── android/                                           # Android配置
│   ├── ios/                                               # iOS配置
│   ├── pubspec.yaml                                       # Flutter配置
│   └── README.md                                          # 移动端说明
│
├── docs/                              # 项目文档
│   ├── api/                                               # API文档
│   │   ├── README.md                                      # API文档说明
│   │   ├── auth.md                                        # 认证接口文档
│   │   ├── user.md                                        # 用户接口文档
│   │   ├── qualification.md                               # 资质接口文档
│   │   ├── customs.md                                     # 报关接口文档
│   │   ├── contract.md                                    # 合同接口文档
│   │   ├── trade.md                                       # 贸易接口文档
│   │   ├── traceability.md                                # 溯源接口文档
│   │   └── order.md                                       # 订单接口文档
│   ├── deployment/                                        # 部署文档
│   │   ├── docker.md                                      # Docker部署
│   │   ├── kubernetes.md                                  # K8s部署
│   │   └── production.md                                  # 生产环境部署
│   ├── development/                                       # 开发文档
│   │   ├── setup.md                                       # 环境搭建
│   │   ├── coding-standards.md                            # 编码规范
│   │   └── testing.md                                     # 测试指南
│   └── architecture/                                      # 架构文档
│       ├── overview.md                                    # 架构概述
│       ├── database.md                                    # 数据库设计
│       └── security.md                                    # 安全设计
│
├── scripts/                           # 脚本文件
│   ├── deploy.sh                                          # 部署脚本
│   ├── backup.sh                                          # 备份脚本
│   ├── init-db.sql                                        # 数据库初始化
│   └── generate-keys.sh                                   # 密钥生成脚本
│
├── docker/                            # Docker相关文件
│   ├── mysql/                                             # MySQL配置
│   │   └── init.sql                                       # 数据库初始化脚本
│   ├── nginx/                                             # Nginx配置
│   │   └── nginx.conf                                     # Nginx配置文件
│   └── redis/                                             # Redis配置
│       └── redis.conf                                     # Redis配置文件
│
└── tests/                             # 端到端测试
    ├── e2e/                                               # E2E测试
    ├── performance/                                       # 性能测试
    └── integration/                                       # 集成测试
```

## 📝 文件说明

### 核心配置文件
- **README.md**: 项目主要说明文档
- **package.json**: 前端依赖和脚本配置
- **pom.xml**: 后端Maven依赖配置
- **docker-compose.yml**: Docker服务编排
- **env.example**: 环境变量配置模板

### 开发相关
- **.gitignore**: Git忽略文件配置
- **tsconfig.json**: TypeScript编译配置
- **vite.config.ts**: Vite构建工具配置
- **.eslintrc.js**: 代码质量检查配置

### 文档目录
- **docs/**: 包含API文档、部署指南、开发文档等
- **CONTRIBUTING.md**: 贡献者指南
- **CHANGELOG.md**: 版本更新记录

## 🚀 快速开始

1. 克隆项目后，参考README.md进行环境搭建
2. 复制env.example为.env并配置环境变量
3. 使用docker-compose.yml快速启动开发环境
4. 查看docs/目录下的详细文档

## 📞 联系方式

如有项目结构相关问题，请查看相关文档或提交Issue。
