package com.freestand.ranu.fsmark2.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.FirebaseDatabaseHelper;
import com.freestand.ranu.fsmark2.data.model.checkqr.Question;
import com.freestand.ranu.fsmark2.fragment.Coupon;
import com.freestand.ranu.fsmark2.fragment.QRScanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackScreen extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.radio_group)
    RadioGroup answerOptionParent;
    @BindView(R.id.checkBox_group)
    LinearLayout checkBoxGroup;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.tv_question)
    TextView questionText;

    @BindView(R.id.option1)
    RadioButton option1;
    @BindView(R.id.option2)
    RadioButton option2;
    @BindView(R.id.option3)
    RadioButton option3;
    @BindView(R.id.option4)
    RadioButton option4;
    @BindView(R.id.option5)
    RadioButton option5;
    @BindView(R.id.option6)
    RadioButton option6;
    @BindView(R.id.option7)
    RadioButton option7;
    @BindView(R.id.option8)
    RadioButton option8;

    @BindView(R.id.option_c1)
    CheckBox optionc1;
    @BindView(R.id.option_c2)
    CheckBox optionc2;
    @BindView(R.id.option_c3)
    CheckBox optionc3;
    @BindView(R.id.option_c4)
    CheckBox optionc4;
    @BindView(R.id.option_c5)
    CheckBox optionc5;
    @BindView(R.id.option_c6)
    CheckBox optionc6;
    @BindView(R.id.option_c7)
    CheckBox optionc7;
    @BindView(R.id.option_c8)
    CheckBox optionc8;


    @BindView(R.id.tv_progress)
    TextView progressText;


    //    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.group_view)
    LinearLayout groupView;
    private Intent intent;
    int currentPage = 1;
    private RadioButton radioButton;
    int totalNoOfQues = 0;
    private List<Question> questionList = new ArrayList<>();
    private String directionClass;
    private String surveyId;
    private List<List<String>> answers = new ArrayList<List<String>>();
    private String brandName;
    private String sender;

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
        surveyId = intent.getStringExtra("survey_id");
        brandName = intent.getStringExtra("brand");
        sender = intent.getStringExtra("sender");
    }

    private void attachClickListener() {
        next.setOnClickListener(this);
    }


    private void updateData(int currentPage) {
        progressText.setText(currentPage + "/" + totalNoOfQues);
//        progressBar.setProgress(currentPage);
        questionText.setText(questionList.get(currentPage - 1).getQuestion());
        updateOptions();
    }

    private void updateOptions() {
        answerOptionParent.setVisibility(View.GONE);
        checkBoxGroup.setVisibility(View.GONE);
        Question tempQues = questionList.get(currentPage - 1);
        if (tempQues.getType().equals("single_choice")) {
            answerOptionParent.setVisibility(View.VISIBLE);
            showRadioView(tempQues);
        } else if (tempQues.getType().equals("multiple_choice")) {
            checkBoxGroup.setVisibility(View.VISIBLE);
            showCheckBoxView(tempQues);
        }

    }

    @Override
    public void onClick(View view) {
        int clickedViewId = view.getId();
        if (clickedViewId == next.getId()) {
            if(recordAnswer() > 0) {
                if (currentPage < totalNoOfQues) {
                    currentPage++;
                    clearMarkedAnswers();
                    flipit(groupView);
                    updateData(currentPage);
                    if (currentPage == totalNoOfQues) {
                        next.setText("Submit");
                    }
                } else if (currentPage == totalNoOfQues) {
                    Log.e("current page", "checking ");
                    sendAnswer();
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(directionClass);
                        Intent i = new Intent(getBaseContext(), clazz);
                        startActivity(i);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        finish();
                    }
                }
            }

        }
    }

    private void sendAnswer() {
        Log.d("asda", answers + "");
            if(sender.equals("qr_scanner") || sender.equals("pre_sampling"))  {
                FirebaseDatabaseHelper.user.child("lastSurvey").setValue(surveyId);
                Map<String, Object> temp = new HashMap<>();
                temp.put(surveyId,answers);
                FirebaseDatabaseHelper.user.child("surveys").child("pre_sampling").updateChildren(temp);
            } else if (sender.equals("coupon")) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("answers",answers);
                FirebaseDatabaseHelper.user.child("feedback").child("brands").child(brandName).child(surveyId).updateChildren(temp);
            } else if(sender.equals("post_sampling")) {
                FirebaseDatabaseHelper.user.child("lastSurvey").setValue(surveyId);
                Map<String, Object> temp = new HashMap<>();
                temp.put(surveyId,answers);
                FirebaseDatabaseHelper.user.child("surveys").child("post_sampling").updateChildren(temp);
            }

    }

    private int recordAnswer() {
        if (questionList.get(currentPage - 1) != null) {
            Question question = questionList.get(currentPage - 1);
            if (question.getType().contentEquals("single_choice")) {
                int selectedId = answerOptionParent.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                int idx = answerOptionParent.indexOfChild(radioButton) + 1;
                if(idx == 0) {
                    return 0;
                } else {
                    List<String> temp = new ArrayList<>();
                    temp.add(Integer.toString(idx));
                    answers.add(temp);
                    return 1;
                }
            } else {
                return getCheckedBoxes();
            }

        }
        return 0;
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
        if (tempQues.getOption1() != null) {
            option1.setVisibility(View.VISIBLE);
            option1.setText(tempQues.getOption1());
        } else {
            option1.setVisibility(View.GONE);
        }
        if (tempQues.getOption2() != null) {
            option2.setVisibility(View.VISIBLE);
            option2.setText(tempQues.getOption2());
        } else {
            option2.setVisibility(View.GONE);
        }
        if (tempQues.getOption3() != null) {
            option3.setVisibility(View.VISIBLE);
            option3.setText(tempQues.getOption3());
        } else {
            option3.setVisibility(View.GONE);
        }
        if (tempQues.getOption4() != null) {
            option4.setVisibility(View.VISIBLE);
            option4.setText(tempQues.getOption4());
        } else {
            option4.setVisibility(View.GONE);
        }
        if (tempQues.getOption5() != null) {
            option5.setVisibility(View.VISIBLE);
            option5.setText(tempQues.getOption5());
        } else {
            option5.setVisibility(View.GONE);
        }
        if (tempQues.getOption6() != null) {
            option6.setVisibility(View.VISIBLE);
            option6.setText(tempQues.getOption6());
        } else {
            option6.setVisibility(View.GONE);
        }
        if (tempQues.getOption7() != null) {
            option7.setVisibility(View.VISIBLE);
            option7.setText(tempQues.getOption7());
        } else {
            option7.setVisibility(View.GONE);
        }
        if (tempQues.getOption8() != null) {
            option8.setVisibility(View.VISIBLE);
            option8.setText(tempQues.getOption8());
        } else {
            option8.setVisibility(View.GONE);
        }

    }

    void showCheckBoxView(Question tempQues) {
        if (tempQues.getOption1() != null) {
            optionc1.setVisibility(View.VISIBLE);
            optionc1.setText(tempQues.getOption1());
        } else {
            optionc1.setVisibility(View.GONE);
        }
        if (tempQues.getOption2() != null) {
            optionc2.setVisibility(View.VISIBLE);
            optionc2.setText(tempQues.getOption2());
        } else {
            optionc2.setVisibility(View.GONE);
        }
        if (tempQues.getOption3() != null) {
            optionc3.setVisibility(View.VISIBLE);
            optionc3.setText(tempQues.getOption3());
        } else {
            optionc3.setVisibility(View.GONE);
        }
        if (tempQues.getOption4() != null) {
            optionc4.setVisibility(View.VISIBLE);
            optionc4.setText(tempQues.getOption4());
        } else {
            optionc4.setVisibility(View.GONE);
        }
        if (tempQues.getOption5() != null) {
            optionc5.setVisibility(View.VISIBLE);
            optionc5.setText(tempQues.getOption5());
        } else {
            optionc5.setVisibility(View.GONE);
        }
        if (tempQues.getOption6() != null) {
            optionc6.setVisibility(View.VISIBLE);
            optionc6.setText(tempQues.getOption6());
        } else {
            optionc6.setVisibility(View.GONE);
        }
        if (tempQues.getOption7() != null) {
            optionc7.setVisibility(View.VISIBLE);
            optionc7.setText(tempQues.getOption7());
        } else {
            optionc7.setVisibility(View.GONE);
        }
        if (tempQues.getOption8() != null) {
            optionc8.setVisibility(View.VISIBLE);
            optionc8.setText(tempQues.getOption8());
        } else {
            optionc8.setVisibility(View.GONE);
        }
    }

    private int getCheckedBoxes() {
        int count = checkBoxGroup.getChildCount();
        ArrayList<String> checkedBoxes = new ArrayList<>();
        CheckBox v;
        for (int i = 0; i < count; i++) {
            v = (CheckBox) checkBoxGroup.getChildAt(i);
            if (v.isChecked()) {
                int idx = i + 1;
                checkedBoxes.add(Integer.toString(idx));
            }
        }
        if(checkedBoxes.size() > 0) {
            answers.add(checkedBoxes);
        }
        return checkedBoxes.size();
    }

    private void clearAllCheckBoxes() {
        int count = checkBoxGroup.getChildCount();
        CheckBox v = null;
        for (int i = 0; i < count; i++) {
            v = (CheckBox) checkBoxGroup.getChildAt(i);
            v.setChecked(false);
        }
    }


}
