package com.edututor.ai.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChatRequest {

    @NotBlank(message = "消息内容不能为空")
    private String message;

    private String sessionId;

    @NotNull(message = "学科不能为空")
    private Subject subject;

    private EducationLevel level = EducationLevel.MIDDLE_SCHOOL;

    private String knowledgeBaseId;

    private boolean enableRag = true;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public EducationLevel getLevel() { return level; }
    public void setLevel(EducationLevel level) { this.level = level; }
    public String getKnowledgeBaseId() { return knowledgeBaseId; }
    public void setKnowledgeBaseId(String knowledgeBaseId) { this.knowledgeBaseId = knowledgeBaseId; }
    public boolean isEnableRag() { return enableRag; }
    public void setEnableRag(boolean enableRag) { this.enableRag = enableRag; }
}
