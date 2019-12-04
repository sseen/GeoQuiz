package com.apple.droid.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.apple.android.answer_is_true";
    private static final String EXTRA_ANSWER_IS_SHOWN = "com.apple.android.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView tvAnswer;
    private Button btShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShwon(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        tvAnswer = findViewById(R.id.answer_text_view);
        btShowAnswer = findViewById(R.id.show_answer_button);
        btShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    tvAnswer.setText(R.string.true_bt);
                } else {
                    tvAnswer.setText(R.string.false_bt);
                }
                setAnswerShownResult(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = btShowAnswer.getWidth() / 2;
                    int cy = btShowAnswer.getHeight() / 2;
                    float radius = btShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(btShowAnswer,cx,cy,radius,0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btShowAnswer.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }else {
                    btShowAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
