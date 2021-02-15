package com.example.lab_2.model;

import com.example.lab_2.R;

public class AllQuestions {
    private int currentQuestion = 0;

    public Question[] allQuestions = {
            new Question(R.string.q_start,true),
            new Question(R.string.q_continent, true),
            new Question(R.string.q_oceans, false),
            new Question(R.string.q_add3_4, true)
    };

    public AllQuestions() {currentQuestion = 0;}

    public Question  getQuestion(int index){
        index = index % allQuestions.length;
        return allQuestions[index];
    }
}
