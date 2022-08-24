package com.bank.document.project.controller;

import com.bank.document.project.model.PostModel;
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
public class PostControllerTest {
    @InjectMocks
    PostController postController;
    @MockBean
    PostModel postModel;
    @MockBean
    PostRepository postRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetPostsByDocId() throws Exception {
        String uri = "/posts/getPostsByDocId/448df40a-7ca6-4baf-abe2-5a394d441c58";
        Mockito.when(postRepository.findByDocId("448df40a-7ca6-4baf-abe2-5a394d441c58"))
                .thenReturn(new ArrayList());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get(uri)).andReturn();
        Assert.state((result.getResponse().getStatus()==200),"test");
    }

    @Test
    public void testGetPostsWithoutDocId() throws Exception{
        String uri = "/posts/getPostsByDocId/";
        Mockito.when(postRepository.findByDocId("448df40a-7ca6-4baf-abe2-5a394d441c58"))
                .thenReturn(new ArrayList());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get(uri)).andReturn();
        Assert.state((result.getResponse().getStatus()==404),"test");
    }

    @Test
    public void testGetPostsByPostId() throws Exception{
        String uri = "/posts/getPostsByPostId/1";
        Mockito.when(postRepository.findById(Long.getLong("1")))
                .thenReturn(java.util.Optional.ofNullable(postModel));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get(uri)).andReturn();
        Assert.state((result.getResponse().getStatus()==200),"test");
    }

    @Test
    public void testGetPostsWithoutPostId() throws Exception{
        String uri = "/posts/getPostsByPostId/";
        Mockito.when(postRepository.findById(Long.getLong("1")))
                .thenReturn(java.util.Optional.ofNullable(postModel));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get(uri)).andReturn();
        Assert.state((result.getResponse().getStatus()==404),"test");
    }
}
