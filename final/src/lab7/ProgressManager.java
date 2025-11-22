/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Joudy
 */
public class ProgressManager {
    
    public static float percentageCompletion(int cId, int lId) throws IOException{
        int completed = 0;
        ArrayList<Course> c = JsonDatabaseManager.loadCourses();
        ArrayList<Student> s= null;
        for(int i =0;i<c.size();++i){
            if(c.get(i).getCourseID() == cId)
                s = c.get(i).getStudents();
    }
    for(int i=0; i< s.size();++i){
       StudentManager m = new StudentManager(s.get(i));
       if(m.isComplete(cId, lId) == true)
           ++completed;
    }   
    float percent = (completed * 100f) / s.size();
   return percent ;      
}


}