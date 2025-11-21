package lab7;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joudy
 */
public class Lesson {
    private int lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;
    private boolean isComplete;

    public Lesson(int lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
          this.resources = new ArrayList<>();
          this.isComplete=false;
          
    }
    public Lesson() {
    this.resources = new ArrayList<>();
}

    public void addResource(String r){
          resources.add(r);
    }
    public boolean isComplete()
    {
        return isComplete;
    }
    

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    
    
}
