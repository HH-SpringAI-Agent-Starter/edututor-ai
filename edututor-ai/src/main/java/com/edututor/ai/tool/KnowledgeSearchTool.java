package com.edututor.ai.tool;

import com.edututor.ai.rag.KnowledgeBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KnowledgeSearchTool {

    private static final Logger log = LoggerFactory.getLogger(KnowledgeSearchTool.class);
    private final KnowledgeBaseService knowledgeBaseService;

    public KnowledgeSearchTool(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @Tool(name = "searchKnowledgeBase", description = "Search education knowledge base for relevant textbook content")
    public String searchKnowledgeBase(
            @ToolParam(description = "Search query") String query,
            @ToolParam(description = "Subject filter: MATH, CHINESE, ENGLISH, etc.") String subject,
            @ToolParam(description = "Number of results") int topK) {
        log.info("KnowledgeSearchTool searching: query={}, subject={}", query, subject);
        List<Document> results;
        if (subject != null && !subject.isEmpty() && !"GENERAL".equals(subject)) {
            results = knowledgeBaseService.searchWithSubject(query, subject, topK);
        } else {
            results = knowledgeBaseService.search(query, topK);
        }
        if (results.isEmpty()) {
            return "未在教材知识库中找到相关内容。";
        }
        return results.stream()
                .map(doc -> "- " + doc.getText().substring(0, Math.min(doc.getText().length(), 500)))
                .collect(Collectors.joining("\n"));
    }
}
