package com.bank.document.project.controller;

import com.bank.document.project.model.CommentModel;
import com.bank.document.project.model.PostModel;
import com.bank.document.project.repository.CommentRepository;
import com.bank.document.project.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
    @InjectMocks
    CommentController commentController;
    @MockBean
    CommentModel commentModel;
    @MockBean
    CommentRepository commentRepository;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PostModel postModel;
    @MockBean
    PostRepository postRepository;
    @Test
    public void testCreateComment() throws Exception{
        String uri = "/comments/createComment?postId=1&commentDescription=commentDescription";
        CommentModel model = new CommentModel("test Description");
        model.setDocId("448df40a-7ca6-4baf-abe2-5a394d441c58");
        model.setPostId(1);
        Mockito.when(postRepository.getReferenceById(Long.valueOf("1"))).thenReturn(postModel);
        Mockito.when(commentRepository.save(model))
                .thenReturn(commentModel);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post(uri).
                requestAttr("postId","1")
                .requestAttr("commentDescription","test comment description")
        ).andReturn();
        Assert.state((mvcResult.getResponse().getStatus()==200),"test");
    }

    @Test
    public void testCreateCommentWithoutId() throws Exception{
        String uri = "/comments/createComment?postId=&commentDescription=commentDescription";
        CommentModel model = new CommentModel("test Description");
        model.setDocId("448df40a-7ca6-4baf-abe2-5a394d441c58");
        model.setPostId(1);
        //PostModel pModel = new PostModel("448df40a-7ca6-4baf-abe2-5a394d441c58","postDescription");
        //pModel.setPostId(1);
        Mockito.when(postRepository.getReferenceById(Long.valueOf("1"))).thenReturn(postModel);
        Mockito.when(commentRepository.save(model))
                .thenReturn(commentModel);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post(uri).
                requestAttr("postId","")
                .requestAttr("commentDescription","test comment description")
        ).andReturn();
        Assert.state((mvcResult.getResponse().getStatus()==400),"test");
    }

    @Test
    public void testGetCommentByDocId() throws Exception{
        String uri = "/comments/getCommentsByDocId/448df40a-7ca6-4baf-abe2-5a394d441c58";
        Mockito.when(commentRepository.findByDocId("448df40a-7ca6-4baf-abe2-5a394d441c58"))
                .thenReturn(new ArrayList<>());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get(uri)).andReturn();
        Assert.state((result.getResponse().getStatus()==200),"test");
    }

    @Test
    public void testGetCommentWithoutDocId() throws Exception{
        String uri = "/comments/getCommentsByDocId/";
        Mockito.when(commentRepository.findByDocId("448df40a-7ca6-4baf-abe2-5a394d441c58"))
                .thenReturn(new ArrayList<>());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get(uri)).andReturn();
        Assert.state((result.getResponse().getStatus()==404),"test");
    }

}
