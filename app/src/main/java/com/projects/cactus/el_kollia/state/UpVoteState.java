package com.projects.cactus.el_kollia.state;

/**
 * Created by el on 5/11/2017.
 */

public class UpVoteState implements State {


    private static boolean pressed=false;
    private static UpVoteState upVoteState=new UpVoteState();

    private UpVoteState(){

    }

   public static UpVoteState getUpVoteState(){

       if (upVoteState==null){
           upVoteState=new UpVoteState();
       }

       return upVoteState;
   }

    @Override
    public boolean pressed() {
        pressed=true;
        return pressed;
    }

    @Override
    public boolean unPressed() {
        pressed=false;
        return pressed;
    }

}
