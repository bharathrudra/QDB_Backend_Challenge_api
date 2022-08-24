package com.bank.document.project.payload;

public class CommentResponse {
    private long commentId;
    private long postId;
    private String docId;
    private String commentDescription;

    public CommentResponse(){};

    public CommentResponse(long commentId, long postId, String docId, String commentDescription){
        this.commentId=commentId;
        this.postId=postId;
        this.docId=docId;
        this.commentDescription=commentDescription;
    }
}
