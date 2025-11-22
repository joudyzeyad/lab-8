/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Malak Mokhtar
 */
public class Certificate {
    
  private String certificateId;
  private int studentId;
  private int courseId;
  private String issueDate;
 
 public Certificate(String certificateId, int studentId, int courseId, String issueDate) {
        this.certificateId = certificateId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = issueDate;
    }


    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    
public static boolean generateCertificate(Student s, Course c) throws IOException {
    
   ArrayList<User> users=JsonDatabaseManager.loadUsers();
    ArrayList<Lesson> lessons=c.getLessons();
    for (int i =0;i<lessons.size();i++) {
        boolean passed = s.hasPassedLesson(lessons.get(i).getLessonId());
        if (!passed) {
            return false;  //at least one lesson is NOT passed
        }
    }
    
    for (Certificate cert :s.getCertificates()) {
        if (cert.getCourseId()==c.getCourseID()) {
            return false; // already has certificate
        }
    }
    Certificate newCert= new Certificate(
            "CERT-" + c.getCourseID() + "-" + s.getUserId(),
            s.getUserId(),
            c.getCourseID(),
            java.time.LocalDate.now().toString()
    );
    s.addCertificate(newCert);
    JsonDatabaseManager.saveUser(users);
    return true;
}
   
}
