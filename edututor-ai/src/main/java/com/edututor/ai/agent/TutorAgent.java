package com.edututor.ai.agent;

import com.edututor.ai.model.Subject;
import com.edututor.ai.model.EducationLevel;
import com.edututor.ai.tool.KnowledgeSearchTool;
import com.edututor.ai.tool.MathSolverTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.model.Media;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

@Service
public class TutorAgent {

    private static final Logger log = LoggerFactory.getLogger(TutorAgent.class);
    private final ChatClient chatClient;

    public TutorAgent(ChatClient.Builder chatClientBuilder,
                      KnowledgeSearchTool knowledgeSearchTool,
                      MathSolverTool mathSolverTool) {
        ToolCallbackProvider tools = MethodToolCallbackProvider.builder()
                .toolObjects(knowledgeSearchTool, mathSolverTool)
                .build();
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是一个专业的K12教育辅导Agent。你的职责包括：
                        1. 解答学生提出的学科问题（数学、语文、英语、物理、化学等）
                        2. 提供详细的解题步骤和思路分析
                        3. 使用教学方法和技巧帮助学生理解知识点
                        4. 根据学生的年级和水平调整讲解难度
                        5. 鼓励式教学，激发学习兴趣
                        
                        回答原则：
                        - 不要直接给出答案，引导学生思考
                        - 对数学问题使用 MathSolverTool 进行准确计算
                        - 对知识点问题使用 KnowledgeSearchTool 检索教材知识库
                        - 回答需要引用的内容时，注明知识点来源
                        """)
                .defaultTools(tools)
                .build();
    }

    public String answer(String question, Subject subject, EducationLevel level, List<com.edututor.ai.model.ChatResponse> history) {
        log.info("TutorAgent answering: subject={}, level={}, question={}", subject, level, question.length() > 50 ? question.substring(0, 50) + "..." : question);

        String levelPrompt = switch (level) {
            case ELEMENTARY -> "学生是小学水平，请用简单易懂的语言和生动的例子解释。";
            case MIDDLE_SCHOOL -> "学生是初中水平，请用清晰的结构化讲解，配合公式和图示。";
            case HIGH_SCHOOL -> "学生是高中水平，可以使用专业术语，注重知识体系的完整性。";
            default -> "请根据问题难度自适应调整讲解方式。";
        };
        String subjectPrompt = switch (subject) {
            case MATH -> "这是一个数学问题。请重点展示解题思路和计算过程。";
            case CHINESE -> "这是一个语文问题。请注重语言分析和文化背景。";
            case ENGLISH -> "这是一个英语问题。请提供语法解析、例句和用法对比。";
            case PHYSICS -> "这是一个物理问题。请结合物理定律和公式推导。";
            case CHEMISTRY -> "这是一个化学问题。请注重化学反应原理和实验现象。";
            default -> "";
        };

        String response = chatClient.prompt()
                .system(s -> s.text(levelPrompt + "\n" + subjectPrompt))
                .user(question)
                .call()
                .content();
        return response != null ? response : "抱歉，暂时无法回答这个问题，请稍后再试。";
    }
}
