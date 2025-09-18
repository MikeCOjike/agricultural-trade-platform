# 贡献指南

感谢您对农产品跨境贸易云平台的关注和贡献！

## 🚀 如何贡献

### 1. 提交Issue
- 发现Bug或有功能建议，请先搜索现有Issues
- 使用Issue模板描述问题
- 提供详细的复现步骤和环境信息

### 2. 代码贡献流程
1. **Fork仓库**到你的GitHub账户
2. **克隆**你Fork的仓库到本地
3. **创建分支**：`git checkout -b feature/your-feature-name`
4. **开发**：按照代码规范进行开发
5. **测试**：确保所有测试通过
6. **提交**：`git commit -m "feat: add your feature"`
7. **推送**：`git push origin feature/your-feature-name`
8. **创建PR**：在GitHub上创建Pull Request

### 3. 分支命名规范
- `feature/功能名称` - 新功能开发
- `bugfix/问题描述` - Bug修复
- `hotfix/紧急修复` - 紧急修复
- `docs/文档更新` - 文档更新

## 📝 代码规范

### Java代码规范
- 遵循Google Java Style Guide
- 使用4个空格缩进
- 类名使用PascalCase，方法名使用camelCase
- 添加必要的注释和JavaDoc

### Vue3/TypeScript规范
- 使用ESLint + Prettier
- 使用2个空格缩进
- 组件名使用PascalCase
- 变量名使用camelCase

### 提交信息规范
使用Conventional Commits规范：
```
<type>(<scope>): <description>

[optional body]

[optional footer(s)]
```

类型说明：
- `feat`: 新功能
- `fix`: Bug修复
- `docs`: 文档更新
- `style`: 代码格式调整
- `refactor`: 代码重构
- `test`: 测试相关
- `chore`: 构建或辅助工具的变动

示例：
```
feat(user): add user authentication module
fix(customs): resolve HS code matching issue
docs(readme): update installation guide
```

## 🧪 测试要求

### 后端测试
```bash
cd backend
mvn test
mvn test jacoco:report  # 生成覆盖率报告
```

### 前端测试
```bash
cd frontend
npm run test
npm run test:coverage  # 生成覆盖率报告
```

### 测试覆盖率要求
- 新增代码测试覆盖率应达到80%以上
- 核心业务逻辑测试覆盖率应达到90%以上

## 📋 PR检查清单

提交PR前请确认：

- [ ] 代码遵循项目规范
- [ ] 所有测试通过
- [ ] 添加了必要的测试用例
- [ ] 更新了相关文档
- [ ] 提交信息符合规范
- [ ] 没有合并冲突

## 🔍 代码审查

- 所有PR都需要至少一位维护者的审查
- 审查重点：代码质量、安全性、性能、可维护性
- 请耐心等待审查，积极响应反馈

## 📞 联系方式

如有任何问题，欢迎通过以下方式联系：

- 提交Issue讨论
- 发送邮件到：[your.email@example.com](mailto:your.email@example.com)

再次感谢您的贡献！🎉
