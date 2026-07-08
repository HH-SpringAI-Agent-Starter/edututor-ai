# Changelog

> 完整变更日志请参阅 [edututor-ai/CHANGELOG.md](edututor-ai/CHANGELOG.md)

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
- 多租户 Tenant 隔离 (企业版)

### 📦 依赖
- Spring AI 1.0.0-M6
- Spring Boot 3.4.x
- PGVector (pgvector)
- Redis
- Minio (S3)
- Ollama
