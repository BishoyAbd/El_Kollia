package com.projects.cactus.el_kollia.answers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.cactus.el_kollia.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Bishoy Abd on 4/4/2018.
 */

public class AnswerFragment extends Fragment {

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_answer_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    //dependencies
    /**
     * question interactor
     * answer interactor
     * likeInteractor
     * view holder recyclerview adapter
     *
     */

}
