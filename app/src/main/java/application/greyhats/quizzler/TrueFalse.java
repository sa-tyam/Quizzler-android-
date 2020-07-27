package application.greyhats.quizzler;

public class TrueFalse {

    private int mQuestionId;
    private boolean mAnswer;



    public TrueFalse(int questionResourceId , boolean trueOrFalse){
        mQuestionId = questionResourceId;
        mAnswer = trueOrFalse;
    }

    public int getmQuestionId() {
        return mQuestionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

}
