package com.edututor.ai.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class StudyPlanTool {

    private static final Logger log = LoggerFactory.getLogger(StudyPlanTool.class);

    @Tool(name = "generateWeeklySchedule", description = "Generate a weekly study schedule based on student profile")
    public Map<String, Object> generateWeeklySchedule(
            @ToolParam(description = "Grade level") String grade,
            @ToolParam(description = "Weak subjects, comma separated") String weakSubjects,
            @ToolParam(description = "Available study hours per day") int hoursPerDay,
            @ToolParam(description = "Upcoming exam date (YYYY-MM-DD)") String examDate) {
        log.info("StudyPlanTool generating schedule: grade={}, weak={}, hours={}", grade, weakSubjects, hoursPerDay);

        Map<String, Object> schedule = new LinkedHashMap<>();
        schedule.put("grade", grade);
        schedule.put("weakSubjects", weakSubjects);
        schedule.put("hoursPerDay", hoursPerDay);
        schedule.put("examDate", examDate);
        schedule.put("startDate", LocalDate.now().toString());
        schedule.put("recommendation", "根据学情数据生成每日学习计划中...");

        return schedule;
    }
}
