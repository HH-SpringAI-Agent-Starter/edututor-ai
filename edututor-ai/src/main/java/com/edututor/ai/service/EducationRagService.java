package com.edututor.ai.service;

import com.edututor.ai.rag.KnowledgeBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationRagService {

    private static final Logger log = LoggerFactory.getLogger(EducationRagService.class);
    private final KnowledgeBaseService knowledgeBaseService;
    private final ChatClient chatClient;

    public EducationRagService(KnowledgeBaseService knowledgeBaseService, ChatClient.Builder chatClientBuilder) {
        this.knowledgeBaseService = knowledgeBaseService;
        this.chatClient = chatClientBuilder.build();
    }

    public String answerWithContext(String question, String subject) {
        List<Document> docs = knowledgeBaseService.searchWithSubject(question, subject, 5);
        String context = docs.stream()
                .map(d -> d.getText() + " [来源: " + d.getMetadata().getOrDefault("source", "知识库") + "]")
                .collect(Collectors.joining("\n\n"));

        if (context.isEmpty()) {
            return "教材知识库中未找到相关参考资料，请尝试其他提问方式。";
        }

        return chatClient.prompt()
                .system("你是一个基于教材知识库的教育助手。请根据提供的参考资料回答学生问题。" +
                        "如果参考资料不足以回答，请明确说明。必须标注引用来源。")
                .user(u -> u.text("""
                        参考资料：
                        {context}
                        
                        学生问题：{question}
                        
                        请基于参考资料回答，并在引用处标注[来源]：
                        """)
                        .param("context", context)
                        .param("question", question))
                .call()
                .content();
    }
}
