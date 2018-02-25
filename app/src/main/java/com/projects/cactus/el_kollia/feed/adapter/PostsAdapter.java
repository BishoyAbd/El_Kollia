package com.projects.cactus.el_kollia.feed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.model.Question;

import java.util.Arrays;
import java.util.List;

/**
 * Created by el on 9/4/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.QViewHolder> {


    List<Question> questionList;
    Context context;
    ButtonVoteListener buttonVoteListener;
    private String TAG = "RecyclerAdapter";
    String userId;
    List<String> likesId;


    public PostsAdapter(Context context, List<Question> questions, String userId,
                        ButtonVoteListener buttonVoteListener) {
        this.questionList = questions;
        this.context = context;
        this.buttonVoteListener = buttonVoteListener;

    }


    @Override
    public PostsAdapter.QViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new QViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final QViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.quTextView.setText(question.getQuestion());
        holder.quDateTextView.setText(question.getDate());
        holder.quOwnerTextView.setText(question.getUser_name());
        Glide.with(context).load(question.getUser_profile_photo()).centerCrop().into(holder.postOwnerImageView);
        (holder.upVote_btn).setText(question.getUp_votes() + "");
        Log.d(TAG, "q id --->" + question.getId());

        if (!holder.isPressed()){

            holder.upVote_btn.setBackground(context.getResources().getDrawable(R.drawable.like_btn));
        }

            if (question.getLikesIds() != null) {
                List<String> likesId = Arrays.asList(question.getLikesIds().split(","));
                String userId = question.getUser_id();
                if (likesId.contains(userId)) {
                    //green
                    Log.d(TAG, "user id --->" + question.getUser_id() + "  should be in ---> " + likesId.toString());
                    holder.itemView.setPressed(true);
                    holder.upVote_btn.setBackground(context.getResources().getDrawable(R.drawable.likegreen_btn));
                }

            }
        }



    public void clear() {
        int size = this.questionList.size();
        this.questionList.clear();
        notifyItemRangeRemoved(0, size);
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "QViewHolder";
        TextView quTextView, quDateTextView, quOwnerTextView, quUpVotesTextView, quDownVotesTextView, quNumOfCommentsTextView;
        ImageView postOwnerImageView;
       public   Button upVote_btn, downVote_btn, showCmnt_btn;
        private boolean pressed = false;

        public QViewHolder(View itemView) {
            super(itemView);
            quTextView = (TextView) itemView.findViewById(R.id.textView_post_content_id);
            quOwnerTextView = (TextView) itemView.findViewById(R.id.post_owner_name_tv_id);
            quDateTextView = (TextView) itemView.findViewById(R.id.post_date_tv_id);
            postOwnerImageView = (ImageView) itemView.findViewById(R.id.profile_post_id_imageView);
            showCmnt_btn = (Button) itemView.findViewById(R.id.button_show_comments_inPost_id);
            upVote_btn = (Button) itemView.findViewById(R.id.button_upVote_inPost_id);
            this.pressed = false;
            upVote_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonVoteListener.ButtonUpVoteOnClick(view, getAdapterPosition());

                }


            });

        }

        public boolean isPressed() {
            return pressed;
        }

        public void setPressed(boolean pressed) {
            this.pressed = pressed;
        }

        void checkUpVoted() {


        }

    }


}
