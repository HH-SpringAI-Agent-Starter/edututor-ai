package com.edututor.ai.agent;

import com.edututor.ai.tool.EssayGraderTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GraderAgent {

    private static final Logger log = LoggerFactory.getLogger(GraderAgent.class);
    private final ChatClient chatClient;

    public GraderAgent(ChatClient.Builder chatClientBuilder, EssayGraderTool essayGraderTool) {
        ToolCallbackProvider tools = MethodToolCallbackProvider.builder()
                .toolObjects(essayGraderTool)
                .build();
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是一个专业的作文批改Agent。根据教育部课程标准进行批改。
                        批改维度：内容完整度(30%)、语言表达(25%)、结构逻辑(20%)、创意创新(15%)、书写规范(10%)
                        每个维度给出评分和具体改进建议。
                        """)
                .defaultTools(tools)
                .build();
    }

    public Map<String, Object> grade(String essay, String subject, String level) {
        log.info("GraderAgent grading essay: subject={}, level={}, length={}", subject, level, essay.length());

        String response = chatClient.prompt()
                .user(u -> u.text("""
                        请批改以下作文(学科: {subject}, 年级: {level})：
                        
                        {essay}
                        
                        请按以下格式输出：
                        1. 总评(100分制)
                        2. 各维度评分及理由（内容/语言/结构/创意/规范）
                        3. 具体修改建议
                        4. 范文参考选段
                        """)
                        .param("essay", essay)
                        .param("subject", subject)
                        .param("level", level))
                .call()
                .content();

        return Map.of(
                "feedback", response != null ? response : "批改失败，请检查作文内容",
                "subject", subject,
                "level", level
        );
    }
}
