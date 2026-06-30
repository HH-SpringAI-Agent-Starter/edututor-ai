package com.agentstack.edututor.tools;

import com.agentstack.edututor.rag.KnowledgeBaseService;
import com.agentstack.edututor.tenant.TenantContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class DomainTools {
    private final KnowledgeBaseService knowledgeBaseService;

    public DomainTools(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @Tool(description = "Search tenant scoped private knowledge base")
    public String knowledge_search(@ToolParam(description = "search query") String query) {
        return String.join("\n", knowledgeBaseService.search(query));
    }

    @Tool(description = "course material search for 教育培训助手")
    public String course_material_search(@ToolParam(description = "business query") String query) {
        return "[course_material_search] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "quiz generator for 教育培训助手")
    public String quiz_generator(@ToolParam(description = "business query") String query) {
        return "[quiz_generator] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "homework hint for 教育培训助手")
    public String homework_hint(@ToolParam(description = "business query") String query) {
        return "[homework_hint] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "learning path plan for 教育培训助手")
    public String learning_path_plan(@ToolParam(description = "business query") String query) {
        return "[learning_path_plan] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "progress report for 教育培训助手")
    public String progress_report(@ToolParam(description = "business query") String query) {
        return "[progress_report] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

    @Tool(description = "teacher review queue for 教育培训助手")
    public String teacher_review_queue(@ToolParam(description = "business query") String query) {
        return "[teacher_review_queue] tenant=" + TenantContext.getTenantId() + "; result for: " + query + "; demo stub, connect real system in production.";
    }

}
