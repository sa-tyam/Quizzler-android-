package application.greyhats.quizzler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;

    int mIndex;
    int mScore;
    int mQuestion;

    Toast mToastMessage;

    @NonNull
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    final int progress_bar_increment = (int) Math.ceil(100.0/mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mScoreTextView = findViewById(R.id.score);
        mQuestionTextView = findViewById(R.id.question_textview);
        mProgressBar = findViewById(R.id.progress_Bar);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        } else {
            mScore = 0;
            mIndex = 0;
        }

        mQuestion = mQuestionBank[mIndex].getmQuestionId();
        mQuestionTextView.setText(mQuestion);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        mIndex = (mIndex+1)% mQuestionBank.length;

        if(mIndex == 0 ){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("GAME OVER");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + " points!");
            alert.setPositiveButton("close application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getmQuestionId();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(progress_bar_increment);
        mScoreTextView.setText("score "+mScore+"/"+mQuestionBank.length);
    };

    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        if (mToastMessage != null){
            mToastMessage.cancel();
        }

        if (userSelection == correctAnswer){
            mScore = mScore + 1 ;
            mToastMessage = Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT);
        } else {
            mToastMessage = Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_LONG);
        }

        mToastMessage.show();
    };

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }

}
