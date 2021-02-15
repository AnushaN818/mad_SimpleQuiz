package com.example.lab_2;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab_2.controller.NextQuestion;
import com.example.lab_2.controller.Score;
import com.example.lab_2.model.AllQuestions;
import com.example.lab_2.model.Question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_INDEX = "GAME_MAIN_ACTIVITY";
    private static final String N_TAG = "IN_ONCLICK";
    private TextView questionView = null;
    private TextView scoreView = null;
    private Button trueButton =null;
    private Button falseButton = null;
    private Button nextButton = null;
    private Button summaryButton = null;

    AllQuestions allQuestions = new AllQuestions();
    NextQuestion nextQuestion = new NextQuestion();
    Score score = new Score();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionView = findViewById(R.id.questionView);
        scoreView = findViewById(R.id.scoreView);

        questionView.setText(R.string.q_start);
        scoreView.setText(R.string.initial_score);


        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        summaryButton = findViewById(R.id.done_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(N_TAG, "Clicked True");
                questionGenerator(view, true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(N_TAG, "Clicked False");
                questionGenerator(view, false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(N_TAG, "Clicked Next");
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }
                score.skipQuestion();
                index = nextQuestion.getNextQuestionIndex();
                scoreView.setText(String.valueOf(score.getScore()));
                questionView.setText(allQuestions.getQuestion(index).getQuestionID());
            }
        });



        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(N_TAG, "Clicked Summary");
                SummaryActivity(view);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SummaryActivity(View view){
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra("score", score.getScore());
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void questionGenerator(View view, Boolean pickTrue){
        int index = nextQuestion.getCurrentQuestion();
        Question question = null;
        try {
            question = allQuestions.getQuestion(index);
        } catch (Exception e) {
            Log.d(TAG_INDEX, "index out of bounds");
        }
        if (pickTrue == false){
                if (!question.isQuestionTrue()){
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                }
            }
        else{
                if (question.isQuestionTrue()){
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                }
            }

        index = nextQuestion.getNextQuestionIndex();
        questionView.setText(allQuestions.getQuestion(index).getQuestionID());
    }
}