package lab7;

import java.io.IOException;
import java.util.ArrayList;
import lab7.*;

public class Student extends User {

    private ArrayList<Integer> enrolledCourses;
    private ArrayList<LessonProgress> progress;
    private ArrayList<QuizAttempt> quizAttempts;
    private ArrayList<Certificate> certificates;

    
    
    public Student(int userId, String username, String email, String password) {
        super(userId, username, email, password, "student");
        this.enrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
        this.quizAttempts = new ArrayList<>();
    }

    public Student(int userId, String username, String email, String passwordHash, boolean alreadyHashed) {
        super(userId, username, email, passwordHash, "student", alreadyHashed);
        this.enrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
        this.quizAttempts = new ArrayList<>();
    }

    public ArrayList<Integer> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Integer> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public ArrayList<LessonProgress> getProgress() {
        return progress;
    }

    public void setProgress(ArrayList<LessonProgress> progress) {
        this.progress = progress;
    }

   
    public ArrayList<Course> availableCourses() throws IOException {
        return JsonDatabaseManager.loadCourses();
    }

    public ArrayList<QuizAttempt> getQuizAttempts() {
        return quizAttempts;
    }

    public void setQuizAttempts(ArrayList<QuizAttempt> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }
    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(ArrayList<Certificate> certificates) {
        this.certificates = certificates;
    }

     public void addCertificate(Certificate c) {
        certificates.add(c);
    }
     
     public boolean hasPassedLesson(int lessonId) {
    for (QuizAttempt qa : quizAttempts) {
        if (qa.getLessonID() == lessonId && qa.isPassed()) {
            return true;
        }
    }
    return false;
}



}
