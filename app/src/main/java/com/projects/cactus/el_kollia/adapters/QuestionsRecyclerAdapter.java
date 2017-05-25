package com.projects.cactus.el_kollia.adapters;

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
import com.projects.cactus.el_kollia.ApiServices.ServiceGenerator;
import com.projects.cactus.el_kollia.ApiServices.VoteApi;
import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.model.Respond;
import com.projects.cactus.el_kollia.model.VoteRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by el on 4/15/2017.
 */
public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.QViewHolder> {

    List<com.projects.cactus.el_kollia.model.Question> questionList;
    Context context;
    ButtonVoteListener buttonVoteListener;
    private String TAG = "RecyclerAdapter";
    String userId;


    public QuestionsRecyclerAdapter(Context context, List<Question> questions,String userId,
                                    ButtonVoteListener buttonVoteListener ) {
        this.questionList = questions;
        this.context = context;
        this.buttonVoteListener = buttonVoteListener;
        this.userId=userId;
    }


    @Override
    public QViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new QViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final QViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.quTextView.setText(question.getQuestion());
        holder.quDateTextView.setText(question.getDate());
        holder.quOwnerTextView.setText(question.getUser_name());
        //holder.quUpVotesTextView.setText(question.getUp_votes());
        //holder.quDownVotesTextView.setText(question.getDown_votes());
        // holder.quNumOfCommentsTextView.setText(question.getNum_of_comments());
        Glide.with(context).load(question.getUser_profile_photo()).centerCrop().into(holder.postOwnerImageView);
        (holder.upVote_btn).setText(question.getUp_votes()+"");
        //get question ID
        //check if user had already liked it o not
        //if liked ----->green
        //else------->default
        VoteApi questionPost = ServiceGenerator.createService(VoteApi.class);
        VoteRequest voteRequest = new VoteRequest();
        question.setUnique_id(userId);
        voteRequest.setQuestion(question);
        Log.d(TAG,"user id---> "+question.getUnique_id());
        Call<Respond> call = questionPost.alreadyUpVoted(voteRequest);
        call.enqueue(new Callback<Respond>() {
            @Override
            public void onResponse(Call<Respond> call, Response<Respond> response) {
                Log.d(TAG, "message--> " + response.body().getMessage());

                if (response.body().getError()) {
                    Log.d(TAG, "button is set to green because message from server is ----> up_voted");
                    holder.itemView.setPressed(true);
                    holder.upVote_btn.setBackground(context.getResources().getDrawable(R.drawable.likegreen_btn));
                }
            }


            @Override
            public void onFailure(Call<Respond> call, Throwable t) {
                Log.d(TAG, "on failure is called");
            }
        });


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
        Button upVote_btn, downVote_btn, showCmnt_btn;
        private boolean pressed = false;




        public QViewHolder(View itemView) {
            super(itemView);
            quTextView = (TextView) itemView.findViewById(R.id.textView_post_content_id);
            quOwnerTextView = (TextView) itemView.findViewById(R.id.post_owner_name_tv_id);
            quDateTextView = (TextView) itemView.findViewById(R.id.post_date_tv_id);
            postOwnerImageView = (ImageView) itemView.findViewById(R.id.profile_post_id_imageView);
//            quUpVotesTextView= (TextView) itemView.findViewById(R.id.textView_upVotes_of_inPost_id);
//            quDownVotesTextView= (TextView) itemView.findViewById(R.id.textView_downVotes_of_inPost_id);
//            quNumOfCommentsTextView= (TextView) itemView.findViewById(R.id.textView_num_of_comments_inPost_id);
//
//

            showCmnt_btn = (Button) itemView.findViewById(R.id.button_show_comments_inPost_id);

            upVote_btn = (Button) itemView.findViewById(R.id.button_upVote_inPost_id);
            downVote_btn = (Button) itemView.findViewById(R.id.button_downVote_inPost_id);

            setPressed(false);

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

        ;

    }


}
