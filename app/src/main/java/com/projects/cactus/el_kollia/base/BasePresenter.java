package com.projects.cactus.el_kollia.base;

/**
 * Created by el on 10/8/2017.
 */

public interface BasePresenter<T> {

    void subscribe(T View);

    void unsubscribe();

}
