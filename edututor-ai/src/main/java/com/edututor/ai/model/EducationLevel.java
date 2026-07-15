package com.edututor.ai.model;

public enum EducationLevel {
    ELEMENTARY("小学"),
    MIDDLE_SCHOOL("初中"),
    HIGH_SCHOOL("高中"),
    UNDERGRADUATE("大学本科"),
    GENERAL("通用");

    private final String displayName;

    EducationLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}
