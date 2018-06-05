package com.projects.cactus.el_kollia.feed.adapter;

import com.projects.cactus.el_kollia.model.Question;

import java.util.List;

/**
 * Created by Bishoy Abd on 5/5/2018.
 */
public class FilterManager {

    public interface FilterResult<T> {
        void onFilteredResultReady(List<T> filteredList);
    }


    public FilterManager() {
    }

    public void filter(List<Question> unFilteredList, String query, FilterResult<Question> filterResult) {

        new FilterByQuestion(unFilteredList, new FilterResult<Question>() {
            @Override
            public void onFilteredResultReady(List<Question> filteredList) {
                filterResult.onFilteredResultReady(filteredList);
            }
        }).getFilter().filter(query);

    }

}
