package com.bank.document.project.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Getter@Setter@ToString
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @NotNull
    private String docId;
    private String postDescription;

    public PostModel(){}

    public PostModel(String docId,String postDescription){
        this.docId=docId;
        this.postDescription=postDescription;
    }

}
