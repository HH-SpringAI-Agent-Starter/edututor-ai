
-- 初始化PGVector扩展
CREATE EXTENSION IF NOT EXISTS vector;

-- 教材知识库向量表
CREATE TABLE IF NOT EXISTS knowledge_vectors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT NOT NULL,
    metadata JSONB DEFAULT '{}'::jsonb,
    embedding vector(1536),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 学科分类索引
CREATE INDEX IF NOT EXISTS idx_knowledge_subject
    ON knowledge_vectors USING GIN ((metadata -> 'subject'));

-- 会话历史表
CREATE TABLE IF NOT EXISTS chat_sessions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    session_id VARCHAR(128) NOT NULL,
    user_message TEXT NOT NULL,
    agent_response TEXT NOT NULL,
    agent_type VARCHAR(32),
    subject VARCHAR(32),
    level VARCHAR(32),
    metadata JSONB DEFAULT '{}'::jsonb,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_chat_sessions_session_id
    ON chat_sessions (session_id);
