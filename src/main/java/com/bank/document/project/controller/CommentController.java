package com.bank.document.project.controller;

import com.bank.document.project.exception.CustomException;
import com.bank.document.project.model.CommentModel;
import com.bank.document.project.model.PostModel;
import com.bank.document.project.repository.CommentRepository;
import com.bank.document.project.repository.PostRepository;
import com.bank.document.project.service.DocumentServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DocumentServiceClient documentServiceClient;

    @PostMapping(value = "/createComment")
    public ResponseEntity<CommentModel> createComment(@RequestParam("postId") long postId, @RequestParam("commentDescription") String commentDescription) {
        PostModel postModel=postRepository.getReferenceById(postId);
        CommentModel commentModel= new CommentModel(commentDescription);
        if(null!=postModel) {
            if (postModel.getPostId() != 0) {
                commentModel.setDocId(postModel.getDocId());
                commentModel.setPostId(postId);
                commentModel = commentRepository.save(commentModel);
            }
        }
        else{
            throw new CustomException(" post is not found with given Id "+postId);
        }
        return ResponseEntity.ok().body(commentModel);
    }

    @GetMapping("/getCommentsByDocId/{docId}")
    public ResponseEntity<List<CommentModel>> getCommentsByDocId(@PathVariable String docId){
       return ResponseEntity.ok().body(commentRepository.findByDocId(docId));
    }

}
