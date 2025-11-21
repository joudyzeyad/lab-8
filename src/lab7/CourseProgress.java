package lab7;

public class CourseProgress {

    private int courseID;
    private int completedLessons;

    public CourseProgress(int courseID, int completedLessons) {
        this.courseID = courseID;
        this.completedLessons = completedLessons;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
    }

  
    public void incrementCompletedLessons() {
        this.completedLessons++;
    }

    public void reset() {
        this.completedLessons = 0;
    }
}
