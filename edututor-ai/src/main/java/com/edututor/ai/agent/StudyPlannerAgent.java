package com.edututor.ai.agent;

import com.edututor.ai.tool.StudyPlanTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudyPlannerAgent {

    private static final Logger log = LoggerFactory.getLogger(StudyPlannerAgent.class);
    private final ChatClient chatClient;

    public StudyPlannerAgent(ChatClient.Builder chatClientBuilder, StudyPlanTool studyPlanTool) {
        ToolCallbackProvider tools = MethodToolCallbackProvider.builder()
                .toolObjects(studyPlanTool)
                .build();
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是一个专业的个性化学习规划Agent。
                        根据学生的学情数据、目标院校和弱项科目生成定制化学习计划。
                        计划包含：每日任务、每周目标、复习策略、进度追踪方法。
                        """)
                .defaultTools(tools)
                .build();
    }

    public Map<String, Object> createPlan(Map<String, Object> studentInfo) {
        log.info("StudyPlannerAgent creating plan for student: {}", studentInfo.get("studentName"));

        String response = chatClient.prompt()
                .user(u -> u.text("""
                        请为以下学生生成个性化学习计划：
                        
                        学生信息：{info}
                        
                        请输出包含：学习目标、每日时间表、周计划、薄弱科目强化方案、定期评估方法
                        """)
                        .param("info", studentInfo.toString()))
                .call()
                .content();

        return Map.of(
                "studyPlan", response != null ? response : "计划生成失败",
                "studentInfo", studentInfo
        );
    }
}
