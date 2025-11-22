/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author NEXT STORE
 */
public class QuizAttempt {
    private int lessonID;
    private int score; //number of correct answers
    private int totalQuestions; //total number of questions in this quiz
    private boolean passed;
    private int attemptNumber; //starts from 1

    public QuizAttempt(int lessonID, int score, int totalQuestions, boolean passed, int attemptNumber) {
        this.lessonID = lessonID;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.passed = passed;
        this.attemptNumber = attemptNumber;
    }

    public int getLessonID() {
        return lessonID;
    }

    public int getScore() {
        return score;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }
}
