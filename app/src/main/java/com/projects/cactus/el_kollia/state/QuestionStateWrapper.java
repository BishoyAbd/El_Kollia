package com.projects.cactus.el_kollia.state;

import java.util.ArrayList;

/**
 * Created by el on 5/11/2017.
 */

public class QuestionStateWrapper  {

  //  State[] states={new DownVoteState(),new UpVoteState()};


    void press(State state){
        state.pressed();
    }


    void unPress(State state){
        state.unPressed();
    }


}
