package com.projects.cactus.el_kollia.feed.adapter;

import android.support.v7.util.DiffUtil;

import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

import javax.annotation.Nullable;


/**
 * Created by Bishoy Abd on 3/25/2018.
 */

public class QuestionDiffCallback extends DiffUtil.Callback {

    List<Question> oldQuestions;
    List<Question> newQuestions;

    public QuestionDiffCallback(List<Question> oldQuestions, List<Question> newQuestions) {
        this.oldQuestions = oldQuestions;
        this.newQuestions = newQuestions;
    }

    @Override
    public int getOldListSize() {
        return oldQuestions.size();
    }

    @Override
    public int getNewListSize() {
        return newQuestions.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newQuestions.get(newItemPosition).getId().equals(oldQuestions.get(oldItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newQuestions.get(newItemPosition).equals(oldQuestions.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
