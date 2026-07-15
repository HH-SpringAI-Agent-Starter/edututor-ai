package com.edututor.ai.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MathSolverTool {

    private static final Logger log = LoggerFactory.getLogger(MathSolverTool.class);

    @Tool(name = "solveMathProblem", description = "Solve math problems with step-by-step solutions")
    public String solveMathProblem(
            @ToolParam(description = "Math problem expression or description") String problem,
            @ToolParam(description = "Math domain: arithmetic, algebra, geometry, calculus, statistics") String domain) {
        log.info("MathSolverTool solving: domain={}, problem={}", domain, problem);
        // Basic arithmetic evaluation
        if ("arithmetic".equals(domain)) {
            return evaluateArithmetic(problem);
        }
        return "解题思路：\n1. 理解问题：分析题目条件\n2. 确定方法：选择合适公式/定理\n3. 逐步推导：展示每一步计算\n4. 验证答案：检查结果合理性";
    }

    private String evaluateArithmetic(String expression) {
        try {
            // Simple arithmetic evaluator (POC)
            String sanitized = expression.replaceAll("[^0-9+\\-*/()%. ]", "");
            return "表达式: " + expression + "\n计算结果需要精确计算引擎支持。\n步骤：\n1. 先算括号内\n2. 再算乘除\n3. 最后加减";
        } catch (Exception e) {
            return "计算错误: " + e.getMessage() + "\n请检查表达式格式是否正确。";
        }
    }
}
