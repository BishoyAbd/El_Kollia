package com.projects.cactus.el_kollia.model;

/**
 * Created by Bishoy Abd on 5/5/2018.
 */
public enum FilterType {

    TYPE_QUESTION("question"), TYPE_ANSWER("answer"), TYPE_POLL("poll");

    private final String type;

    FilterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
