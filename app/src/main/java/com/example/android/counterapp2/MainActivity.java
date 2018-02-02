package com.example.android.counterapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int aCount = 0;
    int bCount = 0;
    int aCurrentScore = 0;
    int aSetScore = 0;
    int bCurrentScore = 0;
    int bSetScore = 0;
    boolean isDeuce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Updates current scores and current set score for team a and b
     */

    public void displayScoresA(int currentScore, int setScore) {
        TextView aOverallView = (TextView) findViewById(R.id.a_overall_score_textView);
        TextView aCurrentView = (TextView) findViewById(R.id.a_current_score_textView);

        aOverallView.setText(String.valueOf(setScore));
        aCurrentView.setText("current score: " + currentScore);
    }

    public void displayScoresB(int currentScore, int setScore) {
        TextView bOverallView = (TextView) findViewById(R.id.b_overall_score_textView);
        TextView bCurrentView = (TextView) findViewById(R.id.b_current_score_textView);

        bOverallView.setText(String.valueOf(setScore));
        bCurrentView.setText("current score: " + currentScore);

    }

    public void displayDeuce(String inputA, String inputB) {
        TextView aCurrentView = (TextView) findViewById(R.id.a_current_score_textView);
        TextView bCurrentView = (TextView) findViewById(R.id.b_current_score_textView);

        aCurrentView.setText("current score: " + inputA);
        bCurrentView.setText("current score: " + inputB);
    }

    public void updateAscores(int currentScore, int setScore) {
        if ((currentScore == 40) && (aCurrentScore != bCurrentScore)) {
            displayScoresA(currentScore, setScore);
        } else if ((aCurrentScore == bCurrentScore) && (aCurrentScore == 40)) {
            isDeuce = true;
            displayDeuce("DEUCE", "DEUCE");
        } else {
            displayScoresA(currentScore, aSetScore);
        }
    }

    public void updateBscores(int currentScore, int setScore) {
        if ((currentScore == 40) && (aCurrentScore != bCurrentScore)) {
            displayScoresB(currentScore, aSetScore);
        } else if ((aCurrentScore == bCurrentScore) && (aCurrentScore == 40)) {
            isDeuce = true;
            displayDeuce("DEUCE", "DEUCE");
        } else {
            displayScoresB(currentScore, setScore);
        }
    }

    /** love/0, 15, 30, 40
     * Scoring functionality - BUTTONS
     */
    public void aScored(View v) {
        aCount += 1;

        if (isDeuce) {
            deuceMatch();
        }

        if (aCount == 1) {
            aCurrentScore = 1;
        } else if (aCount == 2) {
            aCurrentScore = 15;
        } else if (aCount == 3) {
            aCurrentScore = 30;
        } else if (aCount == 4) {
            aCurrentScore = 40;
        } else if ((aCount > 4) && !isDeuce) {
            aCount = 0;
            aCurrentScore = 0;
            aSetScore += 1;
        }
        updateAscores(aCurrentScore, aSetScore);
    }

    public void bScored(View v) {
        bCount += 1;

        if (isDeuce) {
            deuceMatch();
        }

        if (bCount == 1) {
            bCurrentScore = 1;
        } else if (bCount == 2) {
            bCurrentScore = 15;
        } else if (bCount == 3) {
            bCurrentScore = 30;
        } else if (bCount == 4) {
            bCurrentScore = 40;
        } else if ((bCount > 4) && !isDeuce) {
            bCount = 0;
            bCurrentScore = 0;
            bSetScore += 1;
        }

        updateBscores(bCurrentScore, bSetScore);
    }

    public void deuceMatch() {
        if ((bCount > aCount) && (bCount - aCount == 1)) {
            bCount += 1;
            displayDeuce("DEUCE", "DEUCE - ADV");
        } else if ((aCount > bCount) && (aCount - bCount == 1)) {
            aCount += 1;
            displayDeuce("DEUCE - ADV", "DEUCE");
        } else if (aCount - bCount == 2) {
            isDeuce = false;
            aCount = 0;
            aCurrentScore = 0;
            aSetScore += 1;
        } else if (bCount - aCount == 2) {
            isDeuce = false;
            bCount = 0;
            bCurrentScore = 0;
            bSetScore += 1;
        }
    }

    /**
     * Reset game
     */
    public void reset(View v) {
        aCount = 0;
        bCount = 0;
        aCurrentScore = 0;
        aSetScore = 0;
        bCurrentScore = 0;
        bSetScore = 0;
        displayScoresA(aCurrentScore, aSetScore);
        displayScoresB(bCurrentScore, bSetScore);
    }
}
