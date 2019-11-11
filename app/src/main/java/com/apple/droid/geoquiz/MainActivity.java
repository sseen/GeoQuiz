package com.apple.droid.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btTrue;
    private Button btFalse;
    private Button btNext;
    private TextView tvQuestion;

    private static final String TAG = "MainActivity";

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,true),
            new Question(R.string.question_africa,true),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        tvQuestion = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();

        btFalse =  findViewById(R.id.false_bt);
        btTrue =  findViewById(R.id.true_bt);
        btNext = findViewById(R.id.next_button);

        btTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        btFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        tvQuestion.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.true_toast;
        } else {
            messageResId = R.string.false_toast;
        }

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();
    }

}
