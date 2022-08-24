package com.bank.document.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter@Setter@ToString
@Table(name="comments")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private String commentDescription;
    private String docId;
    private long postId;

    public CommentModel(){}

    public CommentModel(String commentDescription){
        this.commentDescription=commentDescription;
    }
}
