package com.projects.cactus.el_kollia.feed.model;

/**
 * Created by el on 6/8/2017.
 */

public interface DataManagerContract {


    void getPosts();

    void getSomePosts();

    void post(String userId, String post);

    boolean isUpVoted(String userId, String postId);

    boolean upVote(int postId, String userId);


    String getUserId(String key);
}
