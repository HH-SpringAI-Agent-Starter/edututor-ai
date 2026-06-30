# EduTutor AI Community

        课程资料 RAG 智能助教 Agent：基于 **Spring AI 2.0 + Agent Tool Calling + PGVector RAG + Ollama** 的教育培训助手项目模板。


        ## 场景定位

        基于课程资料、教材、题库和教学大纲进行 RAG 检索，提供 7x24 智能助教、课后答疑、测验生成和学习路径建议。

        ## 版本定位：开源版


- 单租户或轻量租户 Header 演示
- 本地 Ollama 模型，适合开发和 Demo
- 基础 RAG 知识库、基础工具调用、REST API
- Apache-2.0 友好的开源项目结构
- 可作为 Open Core 的免费获客漏斗

        ## 核心能力

        - Spring AI `ChatClient` Agent 编排
        - `@Tool` 工具调用
        - PGVector 私有知识库 RAG
        - Ollama 本地模型默认配置
        - Docker Compose 一键启动基础设施
        - Flyway 数据库初始化
        - Prometheus / Actuator 可观测性

        ## 工具清单

        - `course_material_search`
- `quiz_generator`
- `homework_hint`
- `learning_path_plan`
- `progress_report`
- `teacher_review_queue`

        ## 连接器方向

        - 教材/课件 OSS
- LMS/ Moodle
- 题库系统
- 教务系统

        ## API

        | Method | Path | Description |
        |---|---|---|
        | POST | `/api/agent/ask` | 学生问答和讲解 |
| POST | `/api/quizzes/generate` | 生成测验 |
| POST | `/api/learning-path/plan` | 生成学习路径 |
        | POST | `/api/kb/sync` | 同步知识库 |
        | GET | `/api/kb/search?q=` | 检索知识库 |

        ## 本地运行

        ```bash
        cp .env.example .env
        docker compose up -d
        ollama pull qwen2.5:7b
        ollama pull mxbai-embed-large
        mvn spring-boot:run
        ```

        ## 示例调用

        ```bash
        curl -s -X POST http://localhost:8080/api/agent/ask \
          -H 'Content-Type: application/json' \
          -H 'X-Tenant-Id: demo' \
          -d '{
            "question": "请用高中生能理解的方式解释向量数据库，并给我 5 道自测题。",
            "userId": "u_1001",
            "sessionId": "s_demo"
          }' | jq
        ```

        ## 目录结构

        ```text
        src/main/java/.../agent        Agent 编排
        src/main/java/.../tools        工具调用
        src/main/java/.../rag          RAG 服务
        src/main/java/.../tenant       多租户上下文
        src/main/resources/kb          示例知识库
        src/main/resources/db          Flyway 初始化 SQL
        docs/                          架构、API、部署、定价、演示脚本
        ```

        ## GitHub 上传

        ```bash
        git init
        git add .
        git commit -m "Initial commit: EduTutor AI Community"
        gh repo create edututor-ai --public --source=. --remote=origin --push
        ```
