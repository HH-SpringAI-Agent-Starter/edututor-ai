package com.edututor.ai.model;

import java.util.List;
import java.util.Map;

public class ChatResponse {

    private String answer;
    private List<String> sources;
    private String agentType;
    private Map<String, Object> metadata;
    private long processingTimeMs;

    public ChatResponse() {}

    public ChatResponse(String answer, String agentType) {
        this.answer = answer;
        this.agentType = agentType;
    }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public List<String> getSources() { return sources; }
    public void setSources(List<String> sources) { this.sources = sources; }
    public String getAgentType() { return agentType; }
    public void setAgentType(String agentType) { this.agentType = agentType; }
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    public long getProcessingTimeMs() { return processingTimeMs; }
    public void setProcessingTimeMs(long processingTimeMs) { this.processingTimeMs = processingTimeMs; }
}
