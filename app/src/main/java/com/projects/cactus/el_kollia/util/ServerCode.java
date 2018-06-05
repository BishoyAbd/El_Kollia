package com.projects.cactus.el_kollia.util;

/**
 * Created by Bishoy Abd on 3/26/2018.
 */

public enum ServerCode {

    CODE_SUCCESS(200),CODE_ERROR(404);

    int value;

    ServerCode(int i) {
        value = i;
    }

    public int value() {
        return value;
    }
}
