package lab7;

public class LessonProgress {
    private int cID;
    private int lID;
    private boolean isComplete;

    public LessonProgress(int cID, int lID, boolean isComplete) {
        this.cID = cID;
        this.lID = lID;
        this.isComplete = isComplete;
        
    }
    public LessonProgress(int cID, int lID) {
        this.cID = cID;
        this.lID = lID;
        isComplete = false;
    }
    

    public int getlID() {
        return lID;
    }

    public void setlID(int lID) {
        this.lID = lID;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }   

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }
    
}
