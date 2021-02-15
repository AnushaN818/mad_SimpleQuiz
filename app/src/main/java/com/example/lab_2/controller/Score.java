 package com.example.lab_2.controller;

import com.example.lab_2.model.AllQuestions;

public class Score {
    public final int CORRECT_ANS = 10;
    public final int SKIP_QUESTION = -5;
    public int score;

    public Score() {score = 0; }

    AllQuestions allQs = new AllQuestions();

    public void correctAnswer() {score+=CORRECT_ANS;}

    public void skipQuestion() {score += SKIP_QUESTION;}

    public int getScore() {return score;}
}
