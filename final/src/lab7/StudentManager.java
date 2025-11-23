package lab7;

import java.io.IOException;
import java.util.ArrayList;

public class StudentManager {

    private Student s;
    private static final double PASSING_POINT = 0.5;

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
               return  temp.get(i).isIsComplete();
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
    
    public boolean alreadyPassed(int lessonID) {
        for (int i = 0 ; i < s.getQuizAttempts().size() ; i++) {
            if (s.getQuizAttempts().get(i).getLessonID() == lessonID && s.getQuizAttempts().get(i).isPassed()) {
                return true;
            }
        }
        return false;
    }
    
    public QuizAttempt saveAttempt (int courseID, int lessonID, ArrayList<Integer> submittedAns) throws IOException {
        ArrayList<Course> courses = JsonDatabaseManager.loadCourses();
        Course c = null;
        Lesson l = null;
        
        for (int i = 0 ; i < courses.size() ; i++) {
            if (courses.get(i).getCourseID() == courseID) {
                c = courses.get(i);
                for (int j = 0 ; j < c.getLessons().size() ; j++) {
                    if (c.getLessons().get(j).getLessonId() == lessonID) {
                        l = c.getLessons().get(j);
                        break;
                    }
                }
                break;
            }
        }
        
        if (l == null) {
            throw new IOException("Lesson not found !");
        }
        
        Quiz quiz = l.getQuiz();
        
        if (quiz == null || quiz.getQuestions().isEmpty()) {
            throw new IOException("No quiz is created for this lesson");
        }
        
        int total = quiz.getTotalQuestions();
        int score = 0;
        
        for (int i = 0 ; i < total && i < submittedAns.size() ; i++) {
            if (submittedAns.get(i) == quiz.getQuestions().get(i).getCorrectAns()) {
                score++;
            }
        }
        
        boolean passed;
        
        if (score >= (int) Math.ceil(PASSING_POINT * total)) {
            passed = true;
        }
        else {
            passed = false;
        }
        
        int attemptNumber = 1;
        for (int i = 0 ; i < s.getQuizAttempts().size() ; i++) {
            if (s.getQuizAttempts().get(i).getLessonID() == lessonID) {
                attemptNumber = Math.max(attemptNumber, s.getQuizAttempts().get(i).getAttemptNumber() + 1);
            }
        }
        
        if (attemptNumber > quiz.getMaxAttempts()) {
            throw new IOException("Maximum attempts exceeded for this quiz");
        }
        else {
            QuizAttempt a = new QuizAttempt(lessonID, score, total, passed, attemptNumber);
            s.getQuizAttempts().add(a);
            
            if (passed) {
                boolean found = false;
                
                for (int i = 0 ; i < s.getProgress().size() ; i++) {
                    if (s.getProgress().get(i).getcID() == courseID && s.getProgress().get(i).getlID() == lessonID) {
                        s.getProgress().get(i).setIsComplete(true);
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    s.getProgress().add(new LessonProgress(courseID, lessonID, true));
                }
            }
            
            updateStudentInJson();
            return a;
        }
    }
    
    public boolean canAccessLesson(int courseID,int lessonID) throws IOException {
        ArrayList<Course> courses = JsonDatabaseManager.loadCourses();
        
        for (int i = 0 ; i < courses.size() ; i++) {
            if (courses.get(i).getCourseID() == courseID) {
                Course c = courses.get(i);
                for (int j = 0 ; j < c.getLessons().size() ; j++) {
                    if (c.getLessons().get(j).getLessonId() == lessonID) {
                        if (j == 0) { //first lesson assuming that lessons' order is the order in the c.getLessons() list
                            return true;
                        }
                        else { //must check that previous lesson is passed
                            Lesson previous = c.getLessons().get(j - 1);
                            return isComplete(courseID, previous.getLessonId());
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canAttemptQuiz(int lessonID) {
        int attempts = 0;
        
        for (QuizAttempt a : s.getQuizAttempts()) {
            if (a.getLessonID() == lessonID) {
                attempts = Math.max(attempts, a.getAttemptNumber());
            }
        }
        
        try {
            for (Course course : viewEnrolled()) {
                for (Lesson l : course.getLessons()) {
                    if (l.getLessonId() == lessonID) {
                        int maxAttempts = l.getQuiz().getMaxAttempts();
                        return attempts < maxAttempts;  
                    }
                }
            }           
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
}
