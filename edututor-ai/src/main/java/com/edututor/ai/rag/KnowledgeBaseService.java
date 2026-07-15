package com.edututor.ai.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KnowledgeBaseService {

    private static final Logger log = LoggerFactory.getLogger(KnowledgeBaseService.class);
    private static final int DEFAULT_TOP_K = 5;

    private final VectorStore vectorStore;

    public KnowledgeBaseService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query, int topK) {
        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(topK > 0 ? topK : DEFAULT_TOP_K)
                .similarityThreshold(0.5)
                .build();
        return vectorStore.similaritySearch(request);
    }

    public List<Document> searchWithSubject(String query, String subject, int topK) {
        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(topK > 0 ? topK : DEFAULT_TOP_K)
                .filterExpression("subject == '" + subject + "'")
                .similarityThreshold(0.5)
                .build();
        return vectorStore.similaritySearch(request);
    }

    public void ingestDocument(String content, Map<String, Object> metadata) {
        Document doc = new Document(content, metadata);
        vectorStore.add(List.of(doc));
        log.info("Ingested document with metadata: {}", metadata);
    }

    public void ingestFile(MultipartFile file, String subject, String level) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String content = reader.lines().collect(Collectors.joining("\n"));
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("subject", subject);
            metadata.put("level", level);
            metadata.put("source", file.getOriginalFilename());
            metadata.put("type", "textbook");
            ingestDocument(content, metadata);
        } catch (Exception e) {
            log.error("Failed to ingest file: {}", file.getOriginalFilename(), e);
            throw new RuntimeException("File ingestion failed", e);
        }
    }
}
