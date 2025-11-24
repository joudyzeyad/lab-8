/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author NEXT STORE
 */
public class Instructor extends User{
    private ArrayList<Integer> createdCourses;
    
    public Instructor(int userId, String username, String email, String password) {
        super(userId, username, email, password, "instructor");
        createdCourses = new ArrayList<>();
    }
    
    public Instructor(int userId, String username, String email, String passwordHash, boolean alreadyHashed) {
       super(userId, username, email, passwordHash, "instructor", alreadyHashed);
       createdCourses = new ArrayList<>();
    }

    public ArrayList<Integer> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(ArrayList<Integer> createdCourses) {
        this.createdCourses = createdCourses;
    }
    
    public static ArrayList<Student> viewEnrolledStudents()
    {
       ArrayList<User> users =new ArrayList<>();
       ArrayList<Student>s=new ArrayList<>();
        try {
            users=JsonDatabaseManager.loadUsers();
        } catch (IOException ex) {
            Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
        }
       for(int i=0;i<users.size();i++)
       {
           if(users.get(i).getRole().equalsIgnoreCase("student"))
               s.add((Student) users.get(i));
               
       }
       return s;
        
    }
    public static void courseCreation(Course c) {
        try {
            // Load existing courses
            ArrayList<Course> courses = JsonDatabaseManager.loadCourses();
            
            if (courses == null) {
                courses = new ArrayList<>();
            }
            // Safe to add (because ID is unique)
            courses.add(c);
            // Save updated list
            JsonDatabaseManager.saveCourse(courses);
        } catch (IOException ex) {
            Logger.getLogger(Instructor.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    public static void deleteCourse(int id) throws IOException
    {
        ArrayList<Course> courses=JsonDatabaseManager.loadCourses();
        int i;
        for(i=0;i<courses.size();++i)
            if(courses.get(i).getCourseID()== id)
                courses.remove(i);
       
         JsonDatabaseManager.saveCourse(courses);
   }
    public static void updateCourses(Course c) throws IOException
    {
      ArrayList<Course> courses=JsonDatabaseManager.loadCourses();

        for(int i=0;i<courses.size();i++){
            if(c.getCourseID()==courses.get(i).getCourseID())
            {
            int index =i;
            courses.set(index, c);
               JsonDatabaseManager.saveCourse(courses);
               return;
            }
            
        }

    }
    public static void addLesson(Course c,Lesson l) throws IOException
    {
       ArrayList<Lesson> lessons=c.getLessons();
       ArrayList<Course> course=new ArrayList<>();
       lessons.add(l);
       c.setLessons(lessons);
       course.add(c);
       JsonDatabaseManager.saveCourse(course);
    }
    


public void deleteLesson(Course c,Lesson l) throws IOException
{
  ArrayList<Lesson>lessons = c.getLessons();
   ArrayList<Course> course=new ArrayList<>();
  lessons.remove(l);
   c.setLessons(lessons);
      course.add(c);
       JsonDatabaseManager.saveCourse(course);
}
}
