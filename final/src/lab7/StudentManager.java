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
            ArrayList<LessonProgress> progress = s.getProgress();
            ArrayList<Lesson> temp = this.lessonList(courseID);
            int i;
            for(i=0 ; i< temp.size();++i){
                progress.add(new LessonProgress(courseID,temp.get(i).getLessonId()));
            }
            s.setProgress(progress);
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


    public boolean markLessonCompleted(int courseId, int lessonId) throws IOException {
      ArrayList<LessonProgress> temp = s.getProgress();
      int i;
      boolean flag = false;
      for(i=0;i<temp.size();++i){
         if(temp.get(i).getcID() == courseId && temp.get(i).getlID() == lessonId){
               if(!temp.get(i).isIsComplete()){
                    temp.get(i).setIsComplete(true);
                    flag = true;
               }
         }
      }
      this.updateStudentInJson();
       return flag; 
    }
    public float getProgressForCourse(int cId){
       int completed=0;
       int totalSize = 0;
       ArrayList<LessonProgress> temp = s.getProgress();
       int i;
       for(i=0;i<temp.size();++i){
         if(temp.get(i).getcID() == cId){
             ++totalSize;
             if(temp.get(i).isIsComplete() == true)
                 ++completed;
         }
    }
    return (completed/totalSize)*100;
  }
    public ArrayList<LessonProgress> getLessonProgressbyId(int cId){
          ArrayList<LessonProgress> main = s.getProgress();
          ArrayList<LessonProgress> temp = new ArrayList<>();
          int i;
          for(i=0;i<main.size();++i){
              if(main.get(i).getcID()== cId)
                  temp.add(main.get(i));
          }
          return temp;
    }
    public boolean isComplete(int cId, int lId){
        ArrayList<LessonProgress> temp = this.getLessonProgressbyId(cId);
        int i;
        boolean flag = false;
        for(i=0;i<temp.size();++i)
            if(temp.get(i).getlID()== lId)
               flag =  temp.get(i).isIsComplete();
        return flag;
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
