package com.freestand.ranu.fsmark2.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.model.checkqr.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackScreen extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.radiogroup) RadioGroup answerOptionParent;
    @BindView(R.id.next) Button next;
    @BindView(R.id.tv_question) TextView questionText;
    @BindView(R.id.option1) RadioButton option1;
    @BindView(R.id.option2) RadioButton option2;
    @BindView(R.id.option3) RadioButton option3;
    @BindView(R.id.option4) RadioButton option4;
    @BindView(R.id.option5) RadioButton option5;
    @BindView(R.id.option6) RadioButton option6;
    @BindView(R.id.option7) RadioButton option7;
    @BindView(R.id.option8) RadioButton option8;
    @BindView(R.id.tv_progress) TextView progressText;
//    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.group_view) LinearLayout groupView;
    private Intent intent;
    int currentPage = 1 ;
    int totalNoOfQues = 0;
    private List<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_screen);
        ButterKnife.bind(this);
        attachClickListener();
        intent = getIntent();
        questionList.addAll((List<Question>) intent.getSerializableExtra("question_list"));
        totalNoOfQues = questionList.size();
//        progressBar.setMax(totalNoOfQues);
        updateData(currentPage);


    }

    private void attachClickListener() {
        next.setOnClickListener(this);
    }


    private void updateData(int currentPage){
        progressText.setText(currentPage+"/"+ totalNoOfQues);
//        progressBar.setProgress(currentPage);
        questionText.setText(questionList.get(currentPage-1).getQuestion());
        updateOptions();
    }

    private void updateOptions() {
        Question tempQues = questionList.get(currentPage-1);
        if(tempQues.getOption1()!=null) {
            option1.setVisibility(View.VISIBLE);
            option1.setText(tempQues.getOption1());
        } else {
            option1.setVisibility(View.GONE);
        }if(tempQues.getOption2()!=null) {
            option2.setVisibility(View.VISIBLE);
            option2.setText(tempQues.getOption2());
        } else {
            option2.setVisibility(View.GONE);
        }if(tempQues.getOption3()!=null) {
            option3.setVisibility(View.VISIBLE);
            option3.setText(tempQues.getOption3());
        } else {
            option3.setVisibility(View.GONE);
        }if(tempQues.getOption4()!=null) {
            option4.setVisibility(View.VISIBLE);
            option4.setText(tempQues.getOption4());
        } else {
            option4.setVisibility(View.GONE);
        }if(tempQues.getOption5()!=null) {
            option5.setVisibility(View.VISIBLE);
            option5.setText(tempQues.getOption5());
        } else {
            option5.setVisibility(View.GONE);
        }if(tempQues.getOption6()!=null) {
            option6.setVisibility(View.VISIBLE);
            option6.setText(tempQues.getOption6());
        } else {
            option6.setVisibility(View.GONE);
        }if(tempQues.getOption7()!=null) {
            option7.setVisibility(View.VISIBLE);
            option7.setText(tempQues.getOption7());
        } else {
            option7.setVisibility(View.GONE);
        }if(tempQues.getOption8()!=null) {
            option8.setVisibility(View.VISIBLE);
            option8.setText(tempQues.getOption8());
        } else {
            option8.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        int clickedViewId = view.getId();
        if(clickedViewId == next.getId()) {
            if(currentPage < totalNoOfQues) {
                currentPage++;
                answerOptionParent.clearCheck();
                flipit(groupView);
                updateData(currentPage);
            } else if(currentPage == totalNoOfQues) {
            }
        }
    }
    private void flipit(final View viewToFlip) {

        ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationY", 180f, 0f);

        flip.setDuration(300);

        viewToFlip.setCameraDistance(100 * viewToFlip.getWidth());
        flip.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                answerOptionParent.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                answerOptionParent.setVisibility(View.VISIBLE);

            }
        });

        flip.start();
   }

}
