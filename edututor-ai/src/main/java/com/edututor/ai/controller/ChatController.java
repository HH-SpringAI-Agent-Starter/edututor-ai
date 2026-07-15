package com.edututor.ai.controller;

import com.edututor.ai.model.ChatRequest;
import com.edututor.ai.model.ChatResponse;
import com.edututor.ai.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    private final TutorService tutorService;

    public ChatController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        ChatResponse response = tutorService.processMessage(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@Valid @RequestBody ChatRequest request) {
        return tutorService.processMessageStream(request);
    }

    @PostMapping("/tutor/question")
    public ResponseEntity<ChatResponse> askQuestion(@Valid @RequestBody ChatRequest request) {
        ChatResponse response = tutorService.handleSubjectQuestion(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/tutor/essay-grade")
    public ResponseEntity<ChatResponse> gradeEssay(
            @RequestParam String essay,
            @RequestParam(defaultValue = "CHINESE") String subject,
            @RequestParam(defaultValue = "MIDDLE_SCHOOL") String level) {
        ChatResponse response = tutorService.gradeEssay(essay, subject, level);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/tutor/study-plan")
    public ResponseEntity<ChatResponse> createStudyPlan(
            @RequestBody java.util.Map<String, Object> request) {
        ChatResponse response = tutorService.createStudyPlan(request);
        return ResponseEntity.ok(response);
    }
}
