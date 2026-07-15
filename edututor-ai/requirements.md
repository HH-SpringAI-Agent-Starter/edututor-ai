# EduTutor AI - 功能需求与架构设计

## 🎯 项目定位

面向教育机构的智能教育 AI Agent 系统，提供：
- **习题自动解答**：支持K12数学/语文/英语/物理/化学
- **作文AI批改**：基于教育部课程标准的自动化评估
- **知识点讲解**：结合教材知识库的 RAG 问答
- **个性化学习规划**：根据学情生成定制方案
- **学情分析**：学习进度追踪、薄弱环节识别

## 🏗️ 技术架构

| 层次 | 技术选型 |
|------|---------|
| 框架 | Spring AI 2.0.0, Spring Boot 4.0.0 |
| 向量库 | PostgreSQL + PGVector |
| 缓存 | Redis |
| 存储 | Minio (S3 兼容) |
| AI模型 | Ollama (qwen2.5:7b, nomic-embed-text) |
| Agent框架 | Spring AI Tool Calling + MCP |
| 前端 | REST API + SSE 流式响应 |
| CI/CD | GitHub Actions |
| 数据库迁移 | Flyway |

## 📐 系统设计

### Agent 三层架构
1. **Tutor Agent** - 学科问答、习题解答（调用 MathSolverTool + KnowledgeSearchTool）
2. **Grader Agent** - 作文批改、评分（调用 EssayGraderTool）
3. **Study Planner Agent** - 学习规划、学情分析（调用 StudyPlanTool）

### 数据流
```
用户请求 → API Gateway → Agent 路由 → Tool Calling → RAG 检索(教材库) → LLM 推理 → 格式化响应
```

### Agent 调用链
```
ChatController
  └─ TutorService
       ├─ TutorAgent (学科问答)
       │    ├─ MathSolverTool (数学计算)
       │    └─ KnowledgeSearchTool (知识检索)
       ├─ GraderAgent (作文批改)
       │    └─ EssayGraderTool (文本分析)
       └─ StudyPlannerAgent (学习规划)
             └─ StudyPlanTool (排课生成)
```

### 知识库设计
- 教材知识点向量化存储 (PGVector)
- 支持按学科、年级、知识点分类检索
- 文档导入接口支持 PDF/TXT/Markdown 格式
- 自动维护知识点关联图谱

## 🔒 安全与合规
- 数据本地化部署
- 开源可审计
- 企业版：审计日志 + 敏感拦截 + RBAC

## 📊 性能指标（目标）
- 单次问答响应时间 < 3s
- RAG 检索命中率 > 85%
- 作文批改一致性 > 90%
- 支持 100+ 并发会话

---

> Apache-2.0 License · [HH-SpringAI-Agent-Starter](https://github.com/HH-SpringAI-Agent-Starter)
