package sample;

import java.util.ArrayList;

public class CommentList {
    private ArrayList<Comment> commentList;

    public CommentList(){
        this.commentList = new ArrayList<>();
    }

    public ArrayList<Comment> getCommentList() {
        return this.commentList;
    }

    public Comment get(int i){
        return this.commentList.get(i);
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public int size(){
        return this.commentList.size();
    }

}
