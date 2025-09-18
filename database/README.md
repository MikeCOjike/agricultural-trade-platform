# 数据库安装和初始化指南

## 📋 MySQL 8.0 安装

### Windows 安装
1. 访问 [MySQL官网](https://dev.mysql.com/downloads/mysql/) 下载MySQL 8.0
2. 选择 "MySQL Installer for Windows"
3. 运行安装程序，选择 "Developer Default" 安装类型
4. 设置root用户密码（建议设置为简单密码如：123456）
5. 完成安装

### macOS 安装
```bash
# 使用Homebrew安装
brew install mysql

# 启动MySQL服务
brew services start mysql

# 设置root密码
mysql_secure_installation
```

### Linux (Ubuntu) 安装
```bash
# 更新包管理器
sudo apt update

# 安装MySQL
sudo apt install mysql-server

# 启动MySQL服务
sudo systemctl start mysql

# 设置root密码
sudo mysql_secure_installation
```

## 🗄️ 数据库初始化

### 方法一：直接导入（推荐）
```bash
# 在项目根目录执行
mysql -u root -p < database/init.sql
```
执行后会自动：
- 创建数据库 `agri_trade`
- 创建所有必需的表
- 插入初始测试数据

### 方法二：手动执行
```bash
# 1. 登录MySQL
mysql -u root -p

# 2. 在MySQL命令行中执行
source /path/to/project/database/init.sql

# 或者
\. /path/to/project/database/init.sql
```

### 方法三：使用MySQL Workbench
1. 打开MySQL Workbench
2. 连接到本地MySQL服务器
3. 选择 "File" → "Open SQL Script"
4. 选择 `database/init.sql` 文件
5. 点击执行按钮 ⚡

## ✅ 验证安装

### 检查数据库是否创建成功
```sql
-- 登录MySQL
mysql -u root -p

-- 查看数据库
SHOW DATABASES;

-- 应该看到 agri_trade 数据库

-- 使用数据库
USE agri_trade;

-- 查看表
SHOW TABLES;

-- 应该看到以下表：
-- companies
-- contracts  
-- customs_declarations
-- hs_codes
-- orders
-- qualifications
-- traceability_records
-- users
```

### 检查测试数据
```sql
-- 查看用户数据
SELECT * FROM users;

-- 应该看到3个测试用户：
-- admin (管理员)
-- test_export (出口企业)
-- test_import (进口企业)

-- 查看HS编码数据
SELECT * FROM hs_codes;

-- 应该看到6条HS编码记录
```

## 🔧 常见问题解决

### 问题1：连接被拒绝
```
ERROR 2002 (HY000): Can't connect to local MySQL server through socket
```
**解决方案**：
```bash
# 启动MySQL服务
# Windows: 在服务管理器中启动MySQL80服务
# macOS: brew services start mysql
# Linux: sudo systemctl start mysql
```

### 问题2：密码错误
```
ERROR 1045 (28000): Access denied for user 'root'@'localhost'
```
**解决方案**：
```bash
# 重置root密码
# 1. 停止MySQL服务
# 2. 以安全模式启动
mysqld --skip-grant-tables

# 3. 在新终端中重置密码
mysql -u root
ALTER USER 'root'@'localhost' IDENTIFIED BY '新密码';
FLUSH PRIVILEGES;
```

### 问题3：字符编码问题
```
ERROR 1366: Incorrect string value
```
**解决方案**：
确保MySQL配置文件中设置了正确的字符集：
```ini
[mysqld]
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci

[mysql]
default-character-set=utf8mb4
```

### 问题4：导入脚本失败
**解决方案**：
```bash
# 检查文件路径是否正确
# 确保在项目根目录执行命令
# 使用绝对路径
mysql -u root -p < "C:\完整路径\database\init.sql"
```

## 📊 数据库配置信息

### 连接参数
- **主机**: localhost
- **端口**: 3306
- **数据库**: agri_trade
- **用户名**: root
- **密码**: 你设置的MySQL root密码

### Spring Boot配置示例
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/agri_trade?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 你的密码
    driver-class-name: com.mysql.cj.jdbc.Driver
```

## 🔄 重新初始化数据库

如果需要重新初始化数据库：
```bash
# 删除并重新创建（会丢失所有数据）
mysql -u root -p < database/init.sql
```

脚本会自动：
1. 删除现有的 `agri_trade` 数据库
2. 重新创建数据库
3. 创建所有表
4. 插入初始数据

## 📞 获取帮助

如果遇到其他问题：
1. 检查MySQL服务是否正在运行
2. 确认MySQL版本是8.0以上
3. 检查防火墙设置
4. 查看MySQL错误日志

---

**最后更新**: 2024年1月
