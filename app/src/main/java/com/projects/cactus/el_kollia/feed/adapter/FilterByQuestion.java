package com.projects.cactus.el_kollia.feed.adapter;

import android.widget.Filter;
import android.widget.Filterable;

import com.projects.cactus.el_kollia.model.Question;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Bishoy Abd on 5/5/2018.
 */

/**
 *
 */
public class FilterByQuestion implements Filterable {


    private List<Question> originalItems;
    private List<Question> filteredItems;
    private FilterManager.FilterResult<Question> filterResult;

    public FilterByQuestion(List<Question> originalItems, FilterManager.FilterResult<Question> filterResult) {
        this.originalItems = originalItems;
        this.filteredItems = originalItems;
        this.filterResult = filterResult;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String string = charSequence.toString();
                if (string.isEmpty())
                    filteredItems = originalItems;
                else {
                    List<Question> filteredList = new ArrayList<>();
                    for (Question item : originalItems) {
                        if (item.getQuestion().contains(string))
                            filteredList.add(item);
                    }
                    filteredItems = filteredList;
                }

                FilterResults results = new FilterResults();
                results.values = filteredItems;
                return results;


            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredItems = (ArrayList<Question>) filterResults.values;
                Timber.d("filtered items --> " + filteredItems.toString());
                filterResult.onFilteredResultReady(filteredItems);

            }
        };


    }
}
