package com.edututor.ai.service;

import com.edututor.ai.agent.TutorAgent;
import com.edututor.ai.agent.GraderAgent;
import com.edututor.ai.agent.StudyPlannerAgent;
import com.edututor.ai.model.ChatRequest;
import com.edututor.ai.model.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;

@Service
public class TutorService {

    private static final Logger log = LoggerFactory.getLogger(TutorService.class);

    private final TutorAgent tutorAgent;
    private final GraderAgent graderAgent;
    private final StudyPlannerAgent studyPlannerAgent;

    public TutorService(TutorAgent tutorAgent, GraderAgent graderAgent,
                        StudyPlannerAgent studyPlannerAgent) {
        this.tutorAgent = tutorAgent;
        this.graderAgent = graderAgent;
        this.studyPlannerAgent = studyPlannerAgent;
    }

    public ChatResponse processMessage(ChatRequest request) {
        long start = System.currentTimeMillis();
        String answer = tutorAgent.answer(
                request.getMessage(),
                request.getSubject(),
                request.getLevel(),
                Collections.emptyList()
        );
        ChatResponse response = new ChatResponse(answer, "TUTOR");
        response.setProcessingTimeMs(System.currentTimeMillis() - start);
        return response;
    }

    public Flux<String> processMessageStream(ChatRequest request) {
        return Flux.just(processMessage(request).getAnswer());
    }

    public ChatResponse handleSubjectQuestion(ChatRequest request) {
        long start = System.currentTimeMillis();
        String answer = tutorAgent.answer(
                request.getMessage(),
                request.getSubject(),
                request.getLevel(),
                Collections.emptyList()
        );
        ChatResponse response = new ChatResponse(answer, "TUTOR");
        response.setProcessingTimeMs(System.currentTimeMillis() - start);
        return response;
    }

    public ChatResponse gradeEssay(String essay, String subject, String level) {
        long start = System.currentTimeMillis();
        Map<String, Object> result = graderAgent.grade(essay, subject, level);
        ChatResponse response = new ChatResponse(
                (String) result.getOrDefault("feedback", "批改完成"),
                "GRADER"
        );
        response.setMetadata(result);
        response.setProcessingTimeMs(System.currentTimeMillis() - start);
        return response;
    }

    public ChatResponse createStudyPlan(Map<String, Object> request) {
        long start = System.currentTimeMillis();
        Map<String, Object> plan = studyPlannerAgent.createPlan(request);
        ChatResponse response = new ChatResponse(
                (String) plan.getOrDefault("studyPlan", "计划生成完成"),
                "STUDY_PLANNER"
        );
        response.setMetadata(plan);
        response.setProcessingTimeMs(System.currentTimeMillis() - start);
        return response;
    }
}
