package com.freestand.ranu.fsmark2.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.model.checkqr.Question;
import com.google.android.gms.vision.text.Line;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackScreen extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.radio_group) RadioGroup answerOptionParent;
    @BindView(R.id.checkBox_group) LinearLayout checkBoxGroup;
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

    @BindView(R.id.option_c1) CheckBox optionc1;
    @BindView(R.id.option_c2) CheckBox optionc2;
    @BindView(R.id.option_c3) CheckBox optionc3;
    @BindView(R.id.option_c4) CheckBox optionc4;
    @BindView(R.id.option_c5) CheckBox optionc5;
    @BindView(R.id.option_c6) CheckBox optionc6;
    @BindView(R.id.option_c7) CheckBox optionc7;
    @BindView(R.id.option_c8) CheckBox optionc8;


    @BindView(R.id.tv_progress) TextView progressText;




//    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.group_view) LinearLayout groupView;
    private Intent intent;
    int currentPage = 1 ;
    int totalNoOfQues = 0;
    private List<Question> questionList = new ArrayList<>();
    private String directionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_screen);
        ButterKnife.bind(this);
        attachClickListener();
        getIntentData();
        updateData(currentPage);
    }

    private void getIntentData() {
        intent = getIntent();
        questionList.addAll((List<Question>) intent.getSerializableExtra("question_list"));
        totalNoOfQues = questionList.size();
        directionClass = intent.getStringExtra("direction");
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
        answerOptionParent.setVisibility(View.GONE);
        checkBoxGroup.setVisibility(View.GONE);
        Question tempQues = questionList.get(currentPage-1);
        if(tempQues.getType().equals("single_choice")) {
            answerOptionParent.setVisibility(View.VISIBLE);
            showRadioView(tempQues);
        } else if(tempQues.getType().equals("multiple_choice")){
            checkBoxGroup.setVisibility(View.VISIBLE);
            showCheckBoxView(tempQues);
        }

    }

    @Override
    public void onClick(View view) {
        int clickedViewId = view.getId();
        if(clickedViewId == next.getId()) {
            recordAnswer();
            if(currentPage < totalNoOfQues) {
                currentPage++;
                clearMarkedAnswers();
                flipit(groupView);
                updateData(currentPage);
                if(currentPage == totalNoOfQues) {
                    next.setText("Submit");
                }
            } else if(currentPage == totalNoOfQues) {
                Log.e("current page", "checking ");
                sendAnswer();
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(directionClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getBaseContext(), clazz);
                startActivity(i);
            }
        }
    }

    private void sendAnswer() {

    }

    private void recordAnswer() {

    }

    private void clearMarkedAnswers() {
        answerOptionParent.clearCheck();
        clearAllCheckBoxes();
    }

    private void flipit(final View viewToFlip) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationY", 180f, 0f);
        flip.setDuration(300);
        viewToFlip.setCameraDistance(100 * viewToFlip.getWidth());
        flip.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
//                answerOptionParent.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
//                answerOptionParent.setVisibility(View.VISIBLE);
            }
        });
        flip.start();
   }

   void showRadioView(Question tempQues) {
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

   void showCheckBoxView(Question tempQues) {
       if(tempQues.getOption1()!=null) {
           optionc1.setVisibility(View.VISIBLE);
           optionc1.setText(tempQues.getOption1());
       } else {
           optionc1.setVisibility(View.GONE);
       }if(tempQues.getOption2()!=null) {
           optionc2.setVisibility(View.VISIBLE);
           optionc2.setText(tempQues.getOption2());
       } else {
           optionc2.setVisibility(View.GONE);
       }if(tempQues.getOption3()!=null) {
           optionc3.setVisibility(View.VISIBLE);
           optionc3.setText(tempQues.getOption3());
       } else {
           optionc3.setVisibility(View.GONE);
       }if(tempQues.getOption4()!=null) {
           optionc4.setVisibility(View.VISIBLE);
           optionc4.setText(tempQues.getOption4());
       } else {
           optionc4.setVisibility(View.GONE);
       }if(tempQues.getOption5()!=null) {
           optionc5.setVisibility(View.VISIBLE);
           optionc5.setText(tempQues.getOption5());
       } else {
           optionc5.setVisibility(View.GONE);
       }if(tempQues.getOption6()!=null) {
           optionc6.setVisibility(View.VISIBLE);
           optionc6.setText(tempQues.getOption6());
       } else {
           optionc6.setVisibility(View.GONE);
       }if(tempQues.getOption7()!=null) {
           optionc7.setVisibility(View.VISIBLE);
           optionc7.setText(tempQues.getOption7());
       } else {
           optionc7.setVisibility(View.GONE);
       }if(tempQues.getOption8()!=null) {
           optionc8.setVisibility(View.VISIBLE);
           optionc8.setText(tempQues.getOption8());
       } else {
           optionc8.setVisibility(View.GONE);
       }
   }

   private List<Integer> getCheckedBoxes() {
       List<Integer> integerList = new ArrayList<>();
       int count = checkBoxGroup.getChildCount();
       CheckBox v = null;
       for(int i=0; i<count; i++) {
           v = (CheckBox) checkBoxGroup.getChildAt(i);
           if(v.isChecked()) {
               integerList.add(i);
           }
       }
       return integerList;
   }

   private void clearAllCheckBoxes(){
       int count = checkBoxGroup.getChildCount();
       CheckBox v = null;
       for(int i=0; i<count; i++) {
           v = (CheckBox) checkBoxGroup.getChildAt(i);
           v.setChecked(false);
       }
   }



}
