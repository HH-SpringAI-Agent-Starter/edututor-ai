# EduTutor AI Community

智能教育 AI Agent：基于 **Spring AI 2.0 + Agent Tool Calling + PGVector RAG + Ollama** 的 K12 智能助教系统，覆盖学科答疑、作文批改与个性化学习规划。

## 场景定位

面向教育机构与学生的智能教育助手，基于课程资料、教材、题库和教学大纲进行 RAG 检索，提供 7x24 智能助教、课后答疑、作文批改和学习路径建议。

## 版本定位：开源版

- 单租户轻量部署，本地 Ollama 模型，适合开发和 Demo
- 三层 Agent 架构（Tutor / Grader / Study Planner）
- 基础 RAG 知识库、工具调用、REST API + SSE 流式
- Apache-2.0 友好的开源项目结构
- 可作为 Open Core 的免费获客漏斗

## 核心能力

- Spring AI `ChatClient` Agent 编排（TutorAgent / GraderAgent / StudyPlannerAgent）
- `@Tool` 工具调用（数学求解 / 作文分析 / 知识检索 / 学习计划）
- PGVector 私有知识库 RAG（按学科过滤检索）
- Ollama 本地模型默认配置（qwen2.5:7b + nomic-embed-text）
- Docker Compose 一键启动基础设施（PostgreSQL / Redis / Minio）
- Flyway 数据库初始化
- Prometheus / Actuator 可观测性

## 工具清单

| 工具 | 说明 |
|---|---|
| `solveMathProblem` | 数学问题逐步求解（算术/代数/几何/微积分/统计） |
| `analyzeEssay` | 作文结构与语言质量分析（字数/段落/评分辅助） |
| `searchKnowledgeBase` | 教材知识库 RAG 检索（支持学科过滤） |
| `generateWeeklySchedule` | 基于学情生成每周学习计划 |

## 连接器方向

- 教材/课件 OSS
- LMS / Moodle
- 题库系统
- 教务系统

## API

| Method | Path | Description |
|---|---|---|
| POST | `/api/v1/chat` | 学科问答 |
| POST | `/api/v1/chat/stream` | SSE 流式问答 |
| POST | `/api/v1/tutor/question` | 学科提问（Tutor Agent） |
| POST | `/api/v1/tutor/essay-grade` | 作文批改（Grader Agent） |
| POST | `/api/v1/tutor/study-plan` | 学习规划（Study Planner Agent） |
| GET | `/api/health` | 健康检查 |

## 本地运行

```bash
cp .env.example .env
docker compose up -d postgres redis minio
ollama pull qwen2.5:7b
ollama pull nomic-embed-text
mvn spring-boot:run
```

## 示例调用

```bash
curl -s -X POST http://localhost:8080/api/v1/chat \
  -H 'Content-Type: application/json' \
  -d '{
    "message": "请用高中生能理解的方式解释向量数据库",
    "subject": "MATH",
    "level": "HIGH_SCHOOL"
  }' | jq
```

作文批改：

```bash
curl -s -X POST "http://localhost:8080/api/v1/tutor/essay-grade?essay=%E6%88%91%E7%9A%84%E7%88%B6%E4%BA%B2&subject=CHINESE&level=MIDDLE_SCHOOL" | jq
```

## 目录结构

```text
src/main/java/com/edututor/ai/
├── agent/          Tutor / Grader / Study Planner 三层 Agent
├── tool/           工具调用（MathSolver / EssayGrader / KnowledgeSearch / StudyPlan）
├── rag/            RAG 服务 + PGVector 向量库配置
├── service/        TutorService / EducationRagService
├── controller/     ChatController / HealthController
├── model/          请求响应模型与枚举
└── config/         AppConfig / WebConfig
src/main/resources/kb           示例知识库
src/main/resources/db            Flyway 初始化 SQL
docs/                          架构、API、部署、定价、演示脚本
```

## GitHub 上传

```bash
git init
git add .
git commit -m "Initial commit: EduTutor AI Community"
gh repo create edututor-ai --public --source=. --remote=origin --push
```
