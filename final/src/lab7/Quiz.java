/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;

/**
 *
 * @author NEXT STORE
 */
public class Quiz {
    private ArrayList<Question> questions;
    private int maxAttempts = 3;
    
    public Quiz(){
        this.questions = new ArrayList<>();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    
    public void addQuestion(Question q) {
        this.questions.add(q);
    }
    
    public int getTotalQuestions() {
        return this.questions.size();
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
    
    public void setMaxAttempts(int maxAttempts) {
        if (maxAttempts < 1) {
            maxAttempts = 1;
        }
        this.maxAttempts = maxAttempts;
    }
}