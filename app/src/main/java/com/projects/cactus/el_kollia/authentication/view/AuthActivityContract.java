package com.projects.cactus.el_kollia.authentication.view;

/**
 * Created by el on 6/8/2017.
 */

public interface AuthActivityContract {


    interface View {

        void openProfileActivity(String userId);

        void replaceLoginFragment();
    }

    interface Presenter {

        void checkLogedIn();

    }


}
