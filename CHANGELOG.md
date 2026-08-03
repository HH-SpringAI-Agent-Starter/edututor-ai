# Changelog

> 完整变更日志请参阅 [edututor-ai/CHANGELOG.md](edututor-ai/CHANGELOG.md)

## v0.2.0 (2026-08-03)

### 🔄 每周轮转维护
- 移除模板占位代码包 `com.agentstack.edututor`（stub 工具与重复 bean），统一为 `com.edututor.ai` 完整实现
- 修复 pom.xml groupId 与包结构不一致的问题
- README / docs/api.md 与真实 API（`/api/v1/*`）同步
- 技术栈核对：Spring AI 2.0.0 + Spring Boot 4.0.0 + JDK 21

## v0.1.1 (2026-07-22)

### 🔄 每周轮转维护
- 例行代码检视与版本同步
- 文档完整性确认（README / CONTRIBUTING / CHANGELOG / .gitignore / requirements 完整）
- 技术栈核对：Spring AI + Agent Tool Calling + PGVector RAG

## v0.1.0 (2026-07-08)

### ✨ 新特性
- 初始开源发布
- Spring AI Agent + RAG 多学科问答
- 作文AI批改功能
- 个性化学习规划
- PGVector 向量知识库

### 🏗️ 架构
- 三层 Agent 架构：Tutor / Grader / Study Planner
- Tool Calling 工具调用层
- RAG 知识库检索增强生成

### 📦 依赖
- Spring AI 2.0.0
- Spring Boot 4.0.0
- PGVector (pgvector)
- Redis
- Minio (S3)
- Ollama
