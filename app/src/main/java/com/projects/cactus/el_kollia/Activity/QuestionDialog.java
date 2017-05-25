package com.projects.cactus.el_kollia.Activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.projects.cactus.el_kollia.model.Question;
import com.projects.cactus.el_kollia.R;

/**
 * Created by el on 4/18/2017.
 */

public class QuestionDialog extends DialogFragment implements View.OnClickListener {




    public interface OnDialogButtonClick{

       void onClickPost(String content);
       void onClickCancel();

   }

    EditText quesEditText;
    Button post,cancel;
    OnDialogButtonClick onDialogButtonClick;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.question_dialog,container,false);

        quesEditText= (EditText) view.findViewById(R.id.question_dialog_et_id);
        post= (Button) view.findViewById(R.id.post_quest_dialogBtn_id);
        cancel= (Button) view.findViewById(R.id.cancel_quest_dialogBtn_id);
        cancel.setOnClickListener(this);
        post.setOnClickListener(this);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         setTargetFragment(new FeedFragment(),0);
        return dialog;
    }


    @Override
    public void onClick(View view) {

switch (view.getId()){
    case R.id.post_quest_dialogBtn_id:
//        Question question=new Question();
//        question.setQuestion();
        ((OnDialogButtonClick)getTargetFragment()).onClickPost(quesEditText.getText().toString());
        dismiss();
        break;
    case R.id.cancel_quest_dialogBtn_id:
        ((OnDialogButtonClick)getTargetFragment()).onClickCancel();
        dismiss();
        break;


}
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //attach the listener
            onDialogButtonClick = (OnDialogButtonClick) getTargetFragment();
        }catch (ClassCastException e ){
            throw new ClassCastException("activity or fragment --> "+" does not implement the onDialogButtonClick ");
        }
    }



}
