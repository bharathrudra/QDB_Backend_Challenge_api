package com.bank.document.project.controller;

import com.bank.document.project.model.PostModel;
import com.bank.document.project.payload.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.document.project.repository.PostRepository;
import com.bank.document.project.service.DocumentServiceClient;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


/**
 * A. As a User, I want to create a Post and associate it with an existing Document that I have uploaded in the Bank's system, so that other Users can later engage and comment
 * B. As a User, I want to be able to view the Posts that are associated with a Document, so that I can read them
 *
 * ***/


@RestController
@RequestMapping("/posts/")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DocumentServiceClient documentServiceClient;

    /*To create a Post and associate it with an existing Document*/
    @PostMapping(value = "/createPost")
    public PostResponse createPost(@RequestParam("docId") String docId, @RequestParam("postDescription") String postDescription) throws FileNotFoundException {
        // call document model to check if the doc is available or not
        String documentId=documentServiceClient.getDocumentId(docId);
        PostModel postModel= new PostModel(docId,postDescription);
        if(null!=documentId) {
            postModel = postRepository.save(postModel);
            //System.out.println(postModel);
        }else{
            throw new FileNotFoundException(" Document not Found with Given Id "+docId);
        }
        return new PostResponse(postModel.getPostId(), postModel.getPostDescription(),documentId);
    }

    /*To be able to view the Posts that are associated with a Document*/
    @GetMapping("/getPostsByDocId/{docId}")
    public ResponseEntity<List<PostModel>> getPostDataByDocId(@PathVariable String docId){
        return ResponseEntity.ok().body(postRepository.findByDocId(docId));
    }
    /*To be able to view the Posts with the help of postId*/
    @GetMapping("/getPostsByPostId/{postId}")
    public ResponseEntity<Optional<PostModel>> getPostDataById(@PathVariable long postId){
        return ResponseEntity.ok().body(postRepository.findById(postId));
    }


}
