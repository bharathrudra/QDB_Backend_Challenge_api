package com.bank.document.project.payload;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PostResponse {
    private long postId;
    private String postDescription;
    private String docId;

    public PostResponse(long postId,String postDescription,String docId){
        this.postId=postId;
        this.postDescription=postDescription;
        this.docId=docId;
    }
}
