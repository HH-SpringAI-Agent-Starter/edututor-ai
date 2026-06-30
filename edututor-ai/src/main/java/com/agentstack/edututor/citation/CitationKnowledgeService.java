package com.agentstack.edututor.citation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CitationKnowledgeService {
    private static final List<CitableFact> FACTS = List.of(
        new CitableFact("智能助教应如何回答学生问题？", "应依据课程资料，按学生水平解释，必要时给提示而不是直接给完整答案。", "应依据课程资料，按学生水平解释，必要时给提示而不是直接给完整答案。", "课程资料示例", "education_source", "0.86", List.of("教育AI", "智能助教", "课程知识库", "题库")),
        new CitableFact("教育知识库为什么需要可引用？", "教师和学生需要知道答案来自哪一章、哪份课件或哪条教学大纲。", "教师和学生需要知道答案来自哪一章、哪份课件或哪条教学大纲。", "课程资料示例", "education_source", "0.86", List.of("教育AI", "智能助教", "课程知识库", "题库")),
        new CitableFact("EduTutor 的 知识源价值是什么？", "把课程资料、题库、评分标准和 FAQ 结构化为可组合教学知识源。", "把课程资料、题库、评分标准和 FAQ 结构化为可组合教学知识源。", "课程资料示例", "education_source", "0.86", List.of("教育AI", "智能助教", "课程知识库", "题库"))
    );

    private static final List<String> FAQ = List.of(
        "智能助教应如何回答学生问题？\n应依据课程资料，按学生水平解释，必要时给提示而不是直接给完整答案。",
        "教育知识库为什么需要可引用？\n教师和学生需要知道答案来自哪一章、哪份课件或哪条教学大纲。",
        "EduTutor 的 知识源价值是什么？\n把课程资料、题库、评分标准和 FAQ 结构化为可组合教学知识源。"
    );

    private static final List<String> RELATIONS = List.of(
        "Course --contains--> Lesson",
        "Lesson --teaches--> Concept",
        "Quiz --assesses--> Concept"
    );

    private static final List<String> BENCHMARK = List.of(
        "支持课程 RAG",
        "支持测验生成",
        "支持学习路径建议",
        "支持教材来源引用",
        "企业版支持班级/租户隔离"
    );

    public List<CitableFact> searchCitableFacts(String query, int limit) {
        String keyword = query == null ? "" : query.toLowerCase(Locale.ROOT);
        return FACTS.stream()
                .filter(fact -> keyword.isBlank()
                        || fact.title().toLowerCase(Locale.ROOT).contains(keyword)
                        || fact.summary().toLowerCase(Locale.ROOT).contains(keyword)
                        || fact.content().toLowerCase(Locale.ROOT).contains(keyword)
                        || fact.keywords().stream().anyMatch(k -> k.toLowerCase(Locale.ROOT).contains(keyword)))
                .limit(Math.max(1, Math.min(limit, 20)))
                .toList();
    }

    public List<String> faq() {
        return FAQ;
    }

    public List<String> relations() {
        return RELATIONS;
    }

    public List<String> benchmark() {
        return BENCHMARK;
    }
}
