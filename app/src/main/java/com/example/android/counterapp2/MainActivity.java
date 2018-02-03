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
     * Display scores
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

    /** love/0, 15, 30, 40
     * Scoring functionality - BUTTONS
     */
    public void aScored(View v) {
        aCount += 1;

        if ((aCount == bCount) &&  (aCount == 3) || ((aCount == bCount) && (aCount > 3))) {
            displayDeuce("DEUCE", "DEUCE");
            return;
        }

        if (aCount == 1) {
            aCurrentScore = 15;
            displayScoresA(aCurrentScore, aSetScore);
        } else if (aCount == 2) {
            aCurrentScore = 30;
            displayScoresA(aCurrentScore, aSetScore);
        } else if (aCount == 3) {
            aCurrentScore = 40;
            displayScoresA(aCurrentScore, aSetScore);
        } else if ( ((aCount > 3) && (aCount - bCount == 2)) ||
                ((aCount > 3) && (bCount < 3)) ){
            isDeuce = false;
            aCount = 0;
            aCurrentScore = 0;
            aSetScore += 1;

            bCount = 0;
            bCurrentScore = 0;
            displayScoresA(aCurrentScore, aSetScore);
            displayScoresB(bCurrentScore, bSetScore);
        } else if ((aCount > bCount) && (aCount > 3)) {
            displayDeuce("ADV", "DEUCE");
        } else if ((aCount < bCount) && (aCount > 3)) {
            displayDeuce("DEUCE", " ADV");
        }
    }

    public void bScored(View v) {
        bCount += 1;

        if ((aCount == bCount) && (bCount == 3) ||
                ((aCount == bCount) && (bCount > 3))) {
            displayDeuce("DEUCE", "DEUCE");
            return;
        }

        if (bCount == 1) {
            bCurrentScore = 15;
            displayScoresB(bCurrentScore, bSetScore);
        } else if (bCount == 2) {
            bCurrentScore = 30;
            displayScoresB(bCurrentScore, bSetScore);
        } else if (bCount == 3) {
            bCurrentScore = 40;
            displayScoresB(bCurrentScore, bSetScore);
        } else if ( (bCount > 3) && (bCount - aCount == 2) ||
                ((bCount > 3) && (aCount < 3)) ) {
            isDeuce = false;
            bCount = 0;
            bCurrentScore = 0;
            bSetScore += 1;

            aCount = 0;
            aCurrentScore = 0;
            displayScoresA(aCurrentScore, aSetScore);
            displayScoresB(bCurrentScore, bSetScore);
        } else if ((bCount > aCount) && (bCount > 3)) {
            displayDeuce("DEUCE", "ADV");
        } else if ((bCount < aCount) && (bCount > 3)) {
            displayDeuce("ADV", "DEUCE");
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
