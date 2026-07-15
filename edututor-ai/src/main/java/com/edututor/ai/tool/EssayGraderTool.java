package com.edututor.ai.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EssayGraderTool {

    private static final Logger log = LoggerFactory.getLogger(EssayGraderTool.class);

    @Tool(name = "analyzeEssay", description = "Analyze essay structure, content, and language quality")
    public Map<String, Object> analyzeEssay(
            @ToolParam(description = "Full essay text") String essay,
            @ToolParam(description = "Subject: CHINESE, ENGLISH") String subject,
            @ToolParam(description = "Grade level") String level) {
        log.info("EssayGraderTool analyzing: subject={}, level={}, length={}", subject, level, essay.length());

        int wordCount = essay.length();
        int paragraphCount = essay.split("\\n\\s*\\n|\\r\\n\\s*\\r\\n").length;

        return Map.of(
                "wordCount", wordCount,
                "paragraphCount", paragraphCount,
                "subject", subject,
                "level", level,
                "analysis", "字数统计完成，等待AI评分..."
        );
    }
}
