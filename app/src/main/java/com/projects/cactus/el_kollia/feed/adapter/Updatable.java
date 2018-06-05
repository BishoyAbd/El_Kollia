package com.projects.cactus.el_kollia.feed.adapter;

import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by Bishoy Abd on 3/25/2018.
 */

public interface Updatable<T extends List> {

    void update(T list);

    void update();
}
