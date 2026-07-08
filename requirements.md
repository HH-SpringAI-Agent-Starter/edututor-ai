# EduTutor AI - 功能需求与架构设计

> 完整文档请参阅 [edututor-ai/requirements.md](edututor-ai/requirements.md)

## 🎯 项目定位

面向教育机构的智能教育 AI Agent 系统，提供：
- **习题自动解答**：支持K12数学/语文/英语
- **作文AI批改**：基于评分标准的自动化评估
- **知识点讲解**：结合教材知识库的 RAG 问答
- **个性化学习规划**：根据学情生成定制方案

## 🏗️ 技术架构

| 层次 | 技术选型 |
|------|---------|
| 框架 | Spring AI 1.0.0-M6, Spring Boot 3.4.x |
| 向量库 | PostgreSQL + PGVector |
| 缓存 | Redis |
| 存储 | Minio (S3 兼容) |
| AI模型 | Ollama (qwen2.5:7b) |
| 前端 | REST API + SSE 流式响应 |
| CI/CD | GitHub Actions |

## 📐 系统设计

### Agent 三层架构
1. **Tutor Agent** - 学科问答、习题解答
2. **Grader Agent** - 作文批改、评分
3. **Study Planner Agent** - 学习规划、学情分析

### 数据流
```
用户请求 → Agent 路由 → Tool Calling → RAG 检索 → LLM 推理 → 格式化响应
```

### 多租户设计
- TenantContext 线程级隔离
- TenantFilter 请求拦截
- 企业版支持完整 RBAC

## 🔒 安全与合规
- 数据本地化部署
- 开源可审计
- 企业版：审计日志 + 敏感拦截

---

> Apache-2.0 License · [HH-SpringAI-Agent-Starter](https://github.com/HH-SpringAI-Agent-Starter)
