# 农产品跨境贸易云平台

## 📖 项目简介

农产品跨境贸易云平台是一个面向全球农产品贸易的综合性数字化服务平台，致力于为出口企业、进口企业、物流服务商和监管机构提供全流程的贸易服务解决方案。平台通过数字化技术优化贸易流程，降低贸易成本，提高通关效率，促进农产品国际贸易的健康发展。

### 🎯 核心价值
- **智能化报关**：自动匹配HS编码，生成标准化报关单据
- **全程溯源**：建立完整的农产品质量追溯体系
- **多语言支持**：提供中英文对照的标准化合同模板
- **数据驱动**：提供贸易数据分析和市场预测服务
- **合规保障**：确保贸易流程符合国际标准和法规要求

## 🏗️ 技术架构

### 技术栈
- **后端**: Spring Boot 3.x + MySQL数据库
- **前端**: Vue.js 3.x + TypeScript + Vite
- **开发工具**: IntelliJ IDEA + VS Code
- **数据库**: MySQL 8.0
- **版本控制**: Git + GitHub

## 🚀 核心功能模块

### 1. 用户管理模块

**功能概述**：支持出口企业、进口企业、物流服务商和监管机构的多角色用户管理

**主要功能**：
- 用户注册登录、身份认证
- 基于角色的权限控制
- 企业资料和联系人管理
- 资质证书上传维护

---

### 2. 企业资质管理模块

**功能概述**：企业资质证书的全生命周期管理

**主要功能**：
- 资质证书上传和维护（食品经营许可证、出口备案证、有机认证等）
- 智能到期提醒（30/15/7天前自动通知）
- 在线审核流程和进度跟踪
- 证书真伪验证

---

### 3. 智能报关核验模块

**功能概述**：平台核心功能，提供智能化报关服务

**主要功能**：
- HS编码智能匹配（基于商品描述自动匹配，支持模糊匹配）
- 标准化报关单据自动生成
- 关税计算引擎（实时税率查询、优惠协定计算）
- 禁限出口商品识别和风险预警

---

### 4. 多语言合同库模块

**功能概述**：提供标准化的国际贸易合同模板和电子签名服务

**主要功能**：
- 合同模板库（FOB、CIF、EXW等贸易条款，中英文对照）
- 在线合同编辑器（富文本编辑、实时翻译）
- 电子签名系统和签署流程管理
- 合同版本管理和历史记录

---

### 5. 贸易数据管理模块

**功能概述**：贸易信息管理和数据分析服务

**主要功能**：
- 贸易伙伴管理（客户、供应商信息和信用评级）
- 交易记录管理和统计分析
- 市场价格监控和趋势分析
- 智能分析（汇率提醒、需求预测、市场机会识别）

---

### 6. 溯源信息公示模块

**功能概述**：建立完整的农产品质量追溯体系

**主要功能**：
- 生产过程记录（种植/养殖、投入品使用、病虫害防治）
- 加工包装追踪（工艺记录、质量检测、储运条件）
- 溯源码生成（唯一批次码、二维码、区块链存证）
- 消费者查询（扫码查询、溯源信息展示）

## 🔧 辅助功能模块

### 订单管理模块
- 订单生命周期管理（下单、协商、跟踪、修改）
- 多货币支付集成和汇率转换
- 电子发票生成和税务处理

### 系统管理模块
- 基础配置管理（参数配置、数据字典、汇率更新）
- 运维监控（性能监控、操作日志、安全审计）

## 🛠️ 开发指南

### 环境要求
- **Java**: JDK 17+ 
- **Node.js**: 18.x+
- **Maven**: 3.8+
- **数据库**: MySQL 8.0+
- **开发工具**: IntelliJ IDEA / Eclipse + VS Code

### 快速开始
```bash
# 1. 克隆项目
git clone https://github.com/your-org/agricultural-trade-platform.git
cd agricultural-trade-platform

# 2. 数据库准备
# 安装MySQL 8.0，然后导入数据库脚本（会自动创建数据库）
mysql -u root -p < database/init.sql

# 3. 后端环境 (Spring Boot)
cd backend
# 修改 application.yml 中的数据库连接配置
mvn clean install
mvn spring-boot:run

# 4. 前端环境 (Vue3 + Vite)
cd frontend
npm install
npm run dev
```

### 项目结构
```
├── backend/                    # Spring Boot后端
│   ├── src/main/java/         # Java源码
│   │   ├── controller/        # 控制器层
│   │   ├── service/           # 业务逻辑层
│   │   ├── repository/        # 数据访问层
│   │   └── entity/            # 实体类
│   ├── src/main/resources/    # 配置文件
│   └── pom.xml                # Maven配置
├── frontend/                  # Vue3前端
│   ├── src/
│   │   ├── components/        # Vue组件
│   │   ├── views/             # 页面视图
│   │   ├── stores/            # Pinia状态管理
│   │   └── utils/             # 工具函数
│   ├── package.json           # npm配置
│   └── vite.config.ts         # Vite配置
├── database/                  # 数据库脚本
├── docs/                      # 项目文档
└── scripts/                   # 工具脚本
```

## 📚 API文档

### 认证方式
```
Authorization: Bearer <jwt-token>
```

### 响应格式
```json
{
  "code": 200,
  "message": "Success", 
  "data": {...}
}
```

详细API文档：[./docs/api/](./docs/api/)

## 🚀 本地部署指南

### 开发环境要求
- CPU: 双核心以上，内存: 4GB以上
- 存储: 10GB+ 可用空间
- 操作系统: Windows 10/11, macOS, Linux

### 部署步骤
```bash
# 1. 环境准备
# 安装 JDK 17, Node.js 18, MySQL 8.0

# 2. 数据库初始化
mysql -u root -p < database/init.sql

# 3. 启动后端
cd backend
mvn spring-boot:run

# 4. 启动前端
cd frontend  
npm run dev
```

### 访问地址
- 前端应用: http://localhost:3000
- 后端API: http://localhost:8080

## 🧪 测试

```bash
# 后端测试 (Spring Boot)
cd backend && mvn test

# 前端测试 (Vue3 + Vitest)
cd frontend && npm run test

# 覆盖率报告
mvn test jacoco:report  # Java覆盖率
npm run test:coverage   # Vue覆盖率
```

## 🤝 贡献指南

1. Fork项目 → 创建分支 → 提交代码 → 创建PR
2. 代码规范：Java遵循Google Style，Vue3使用ESLint + Prettier
3. 所有变更需要通过Code Review

## 📄 许可证

本项目采用 [MIT License](./LICENSE) 开源许可证。

## 📞 联系我们

- 项目维护者: [Your Name](mailto:your.email@example.com)
- 技术支持: [Support Team](mailto:support@example.com)

## 🗺️ 版本路线图

**v1.0 (当前)**: 用户管理、资质管理、智能报关、合同库、基础溯源

**v1.1 (计划)**: 区块链溯源、AI客服、移动端APP、物流集成

**v2.0 (规划)**: 多语言支持、供应链金融、碳足迹追踪

---

**最后更新**: 2024年1月 | 欢迎提交Issue和建议
