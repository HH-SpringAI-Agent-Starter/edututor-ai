# Changelog

All notable changes to EduTutor AI Community.

---

## [v1.1.0] — 2026-08-03

### Fixed
- 移除 `com.agentstack.edututor` 模板占位包（6 个 stub 工具、重复的 KnowledgeBaseService bean 与重复 Application 主类），统一为 `com.edututor.ai` 完整实现，消除 Spring 启动时的 bean 冲突与主类歧义
- pom.xml groupId 由 `com.agentstack` 修正为 `com.edututor`，与包结构一致

### Documentation
- README 工具清单与 API 表格同步为真实实现（solveMathProblem / analyzeEssay / searchKnowledgeBase / generateWeeklySchedule，端点 `/api/v1/*`）
- docs/api.md 请求/响应格式与 ChatRequest / ChatResponse 对齐

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
- 三层 Agent：Tutor Agent（学科答疑）/ Grader Agent（作文批改）/ Study Planner Agent（学习规划）
- 四大智能工具：数学求解、作文分析、知识检索、学习计划生成
- PGVector 私有知识库 RAG，支持按学科过滤检索
- Docker Compose 一键启动（PostgreSQL + Redis + Minio）
- Flyway 数据库迁移初始化
- Prometheus + Actuator 可观测性集成
- Ollama 本地模型默认配置（qwen2.5:7b + nomic-embed-text）
- REST API + SSE 流式响应
- 示例知识库（教材知识点）
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


## v0.2.1 (2026-08-12)

### 每周轮转维护
- 例行代码与文档完整性检查（README / CONTRIBUTING / CHANGELOG / .gitignore / requirements / LICENSE 齐全）
- 技术栈核对：Spring AI 2.0.0 + Spring Boot 4.0.0 + JDK 21 + PGVector RAG
- 文档结构确认：根目录 + edututor-ai/ 双层文档同步
