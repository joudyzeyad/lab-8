package lab7;

import java.io.IOException;
import java.util.ArrayList;

public class StudentManager {

    private Student s;

    public StudentManager(Student s) {
        this.s = s;
    }

    public ArrayList<Course> availableCourses() throws IOException {
        return JsonDatabaseManager.loadCourses();
    }

    public boolean enroll(int courseID) throws IOException {
        if (s.getEnrolledCourses().contains(courseID)) {
            return false;
        } else {
            s.getEnrolledCourses().add(courseID);
            updateStudentInJson();
            updateCourseEnrollment(courseID);
            return true;
        }
    }

    public ArrayList<Course> viewEnrolled() throws IOException {
        ArrayList<Course> all = JsonDatabaseManager.loadCourses();
        ArrayList<Course> result = new ArrayList<>();

        for (Course c : all) {
            if (s.getEnrolledCourses().contains(c.getCourseID())) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<Lesson> lessonList(int cID) throws IOException {
        for (Course c : viewEnrolled()) {
            if (c.getCourseID() == cID) {
                return c.getLessons();
            }
        }
        return new ArrayList<>();
    }

    public CourseProgress getProgressForCourse(int courseId) {
        for (CourseProgress cp : s.getProgress()) {
            if (cp.getCourseID() == courseId) {
                return cp;
            }
        }
        return null;
    }

    public boolean markLessonCompleted(int courseId, int lessonId) throws IOException {

        int totalLessons = lessonList(courseId).size();
        if (totalLessons == 0) return false;

        CourseProgress courseProgress = null;

        for (CourseProgress p : s.getProgress()) {
            if (p.getCourseID() == courseId) {
                courseProgress = p;
                break;
            }
        }

        if (courseProgress == null) {
            courseProgress = new CourseProgress(courseId, 0);
            s.getProgress().add(courseProgress);
        }

        int completed = courseProgress.getCompletedLessons();

        if (completed >= totalLessons) {
            return false; 
        }

        courseProgress.setCompletedLessons(completed + 1);

        ArrayList<Course> courses = JsonDatabaseManager.loadCourses();

        for (Course course : courses) {
            if (course.getCourseID() == courseId) {
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getLessonId() == lessonId) {
                        lesson.setIsComplete(true);
                        break;
                    }
                }
                break;
            }
        }

        JsonDatabaseManager.saveCourse(courses);

        updateStudentInJson();

        return true;
    }

    
    private void updateStudentInJson() throws IOException {
        ArrayList<User> users = JsonDatabaseManager.loadUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == s.getUserId()) {
                users.set(i, s);
                break;
            }
        }
        JsonDatabaseManager.saveUser(users);
    }

    private void updateCourseEnrollment(int courseId) throws IOException {
        ArrayList<Course> courses = JsonDatabaseManager.loadCourses();

        for (Course c : courses) {
            if (c.getCourseID() == courseId) {
                c.addStudent(s);
                break;
            }
        }
        JsonDatabaseManager.saveCourse(courses);
    }
}
