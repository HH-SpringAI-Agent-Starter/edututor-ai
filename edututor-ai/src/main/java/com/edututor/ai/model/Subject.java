package com.edututor.ai.model;

public enum Subject {
    MATH("数学"),
    CHINESE("语文"),
    ENGLISH("英语"),
    PHYSICS("物理"),
    CHEMISTRY("化学"),
    BIOLOGY("生物"),
    HISTORY("历史"),
    GEOGRAPHY("地理"),
    POLITICS("政治"),
    GENERAL("综合");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}
