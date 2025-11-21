package lab7;

import java.io.IOException;
import java.util.ArrayList;

public class Student extends User {

    private ArrayList<Integer> enrolledCourses;
    private ArrayList<LessonProgress> progress;

    public Student(int userId, String username, String email, String password) {
        super(userId, username, email, password, "student");
        this.enrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
    }

    public Student(int userId, String username, String email, String passwordHash, boolean alreadyHashed) {
        super(userId, username, email, passwordHash, "student", alreadyHashed);
        this.enrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
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
}
