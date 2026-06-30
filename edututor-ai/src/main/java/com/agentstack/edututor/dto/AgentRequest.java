package com.agentstack.edututor.dto;

import java.util.Map;

public record AgentRequest(
        String question,
        String userId,
        String sessionId,
        String tenantId,
        Map<String, Object> context
) {}
