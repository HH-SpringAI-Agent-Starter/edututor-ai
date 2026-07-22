# Changelog

All notable changes to EduTutor AI Community.

---

## [v1.0.1] — 2026-07-22

### Maintenance
- 每周轮转维护：文档完整性确认，代码库健康度检查
- README / CONTRIBUTING / CHANGELOG / .gitignore / requirements 全部完整
- docs 子目录（architecture / api / deployment / security / demo-script / open-core / pricing）完整
- 技术栈核验通过：Spring AI + Agent Tool Calling + PGVector RAG

---

## [v1.0.0] — 2026-07-07

### Added
- Spring AI 2.0 ChatClient Agent 编排核心框架
- 六大智能工具：课程资料检索、测验生成、作业提示、学习路径、进度报告、教师审核队列
- PGVector 私有知识库 RAG，支持 PPT/PDF/DOCX/MD 多格式
- 基于 Tenant Header 的多租户数据隔离
- Docker Compose 一键启动（PostgreSQL + PGVector + Application）
- Flyway 数据库迁移初始化
- Prometheus + Actuator 可观测性集成
- Ollama 本地模型默认配置（qwen2.5:7b + mxbai-embed-large）
- REST API：问答、测验、学习路径、知识库同步/检索
- 示例知识库（数据结构课程资料）
- 完整文档：架构、API、部署、安全、演示脚本

### Documentation
- README.md — 项目概述、快速开始、API 参考
- CONTRIBUTING.md — 贡献指南与开发规范
- requirements.md — 完整功能需求与架构设计
- docs/architecture.md — 系统架构详解
- docs/api.md — API 接口文档
- docs/deployment.md — 生产部署指南
- docs/security.md — 安全设计文档
- docs/demo-script.md — 演示脚本
- docs/pricing.md — Open Core 定价模型
- docs/open-core.md — 开源版与企业版差异