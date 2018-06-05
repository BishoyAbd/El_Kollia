package com.projects.cactus.el_kollia.application;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Bishoy Abd on 3/30/2018.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface Url {
}
