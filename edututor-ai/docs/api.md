# API 文档

## 端点总览

| Method | Path | 说明 |
|---|---|---|
| POST | `/api/v1/chat` | 学科问答（Tutor Agent） |
| POST | `/api/v1/chat/stream` | SSE 流式问答 |
| POST | `/api/v1/tutor/question` | 学科提问（Tutor Agent） |
| POST | `/api/v1/tutor/essay-grade` | 作文批改（Grader Agent） |
| POST | `/api/v1/tutor/study-plan` | 学习规划（Study Planner Agent） |
| GET | `/api/health` | 健康检查 |

## 学科问答（POST /api/v1/chat）

请求体：

```json
{
  "message": "请用初中生能理解的方式解释勾股定理",
  "sessionId": "s_2026_demo",
  "subject": "MATH",
  "level": "MIDDLE_SCHOOL",
  "enableRag": true
}
```

字段说明：

- `message`（必填）：用户问题
- `subject`（必填）：`MATH` / `CHINESE` / `ENGLISH` / `PHYSICS` / `CHEMISTRY`
- `level`：`ELEMENTARY` / `MIDDLE_SCHOOL` / `HIGH_SCHOOL`，默认 `MIDDLE_SCHOOL`
- `enableRag`：是否启用 RAG 教材知识库检索，默认 `true`
- `sessionId`：会话标识，用于后续上下文扩展

响应：

```json
{
  "answer": "Agent 回答内容",
  "agentType": "TUTOR",
  "sources": null,
  "metadata": null,
  "processingTimeMs": 1234
}
```

## 作文批改（POST /api/v1/tutor/essay-grade）

Query 参数：

- `essay`（必填）：作文正文
- `subject`：`CHINESE` / `ENGLISH`，默认 `CHINESE`
- `level`：默认 `MIDDLE_SCHOOL`

响应包含 `metadata`：批改维度评分与建议。

## 学习规划（POST /api/v1/tutor/study-plan）

请求体：

```json
{
  "studentName": "小明",
  "grade": "8",
  "weakSubjects": "数学,物理",
  "hoursPerDay": 2,
  "examDate": "2026-12-01"
}
```

## 健康检查（GET /api/health）

```json
{
  "status": "UP",
  "service": "EduTutor AI",
  "version": "0.1.0",
  "timestamp": "2026-08-03T12:00:00Z"
}
```
