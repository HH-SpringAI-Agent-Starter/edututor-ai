package com.agentstack.edututor.controller;

import com.agentstack.edututor.agent.AgentService;
import com.agentstack.edututor.dto.AgentRequest;
import com.agentstack.edututor.dto.AgentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/ask")
    public AgentResponse ask(@RequestBody AgentRequest request) {
        return agentService.ask(request);
    }
}
