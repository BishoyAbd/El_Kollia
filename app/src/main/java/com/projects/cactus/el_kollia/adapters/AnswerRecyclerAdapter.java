package com.projects.cactus.el_kollia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.model.Answer;

import java.util.Collections;
import java.util.List;

/**
 * Created by el on 5/14/2017.
 */

public class AnswerRecyclerAdapter extends RecyclerView.Adapter<AnswerRecyclerAdapter.AnsViewHolder> {

    private static final String TAG = "AnswerRecyclerAdapter";
    List<Answer> answerList= Collections.EMPTY_LIST;;
    Context context;


    public AnswerRecyclerAdapter(List<Answer> answerList, Context context) {
        this.answerList = answerList;
        this.context = context;
    }

    @Override
    public AnsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_layout, parent, false);
        return new AnsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AnsViewHolder holder, int position) {
        Answer answer = answerList.get(position);
        Log.d(TAG,"answer insid onBind --->answer(0).answer---->"+answerList.get(position).getAnswer());
         //Glide.with(context).load(answer.getProfile_url()).centerCrop().into( holder.answerOwnerImageView);
         //holder.answerOwnerTv.setText(answer.getUser_id());
         holder.dateTv.setText(answer.getDate()+"");
         holder.answerTv.setText(answer.getAnswer()+"");
    }




    @Override
    public int getItemCount() {
        return answerList.size();
    }


    static class AnsViewHolder extends RecyclerView.ViewHolder {
        TextView answerOwnerTv, dateTv, answerTv;
        ImageView answerOwnerImageView;

        public AnsViewHolder(View itemView) {
            super(itemView);
            answerOwnerImageView = (ImageView) itemView.findViewById(R.id.profile_answer_id_imageView);
            answerOwnerTv = (TextView) itemView.findViewById(R.id.answer_owner_name_tv_id);
            answerTv = (TextView) itemView.findViewById(R.id.textView_answer_content_id);
            dateTv = (TextView) itemView.findViewById(R.id.answer_date_tv_id);


        }
    }
}
