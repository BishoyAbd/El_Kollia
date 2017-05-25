package com.projects.cactus.el_kollia.state;

/**
 * Created by el on 5/11/2017.
 */

public class DownVoteState implements State {

   private static String currentState="unpressed";


    @Override
    public boolean pressed() {
        return false;
    }

    @Override
    public boolean unPressed() {
        return false;
    }
}
