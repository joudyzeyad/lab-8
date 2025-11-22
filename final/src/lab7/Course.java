package lab7;


import java.io.IOException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joudy
 */
public class Course {
    private int courseID;
    private int instructorID;
    private String title;
    private String description;
    private String status;
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private ArrayList<Student> students= new ArrayList<>();

    public Course(int courseID, int instructorID, String title, String description,String status) {
        this.courseID = courseID;
        this.instructorID = instructorID;
        this.title = title;
        this.description = description;
        this.status=status;
    }

    public Course() {
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void addLesson(Lesson l){
          lessons.add(l);
    }
    public void addStudent(Student s){
          students.add(s);
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    public static Course getCourse(int cID) throws IOException
    {
      ArrayList<Course> courses = JsonDatabaseManager.loadCourses();
      for(int i=0;i<courses.size();i++)
      { if(courses.get(i).getCourseID()==cID)
              return courses.get(i);
      }
      return null;
    }
    
}
