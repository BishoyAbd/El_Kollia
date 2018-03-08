package com.projects.cactus.el_kollia.authentication.presenter;

import com.projects.cactus.el_kollia.model.ServerResponse;
import com.projects.cactus.el_kollia.model.User;

/**
 * Created by el on 10/31/2017.
 */

public class AuthServerResponse extends ServerResponse {

    private User user;

    public AuthServerResponse() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
