/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.service;

import javabrewers.bespokejewelerblog.model.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import javabrewers.bespokejewelerblog.dao.PostDao;

/**
 *
 * @author coleogden
 */
@SpringBootTest
public class BespokeJewelerBlogServiceImplTest {
    
    BespokeJewelerBlogService service;
    PostDao postDao;
    
    public BespokeJewelerBlogServiceImplTest() {
        postDao = new PostDaoDBImplStub();
        service = new BespokeJewelerBlogServiceImpl(postDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testAddNewPost() {
        Post testPost = new Post();
        testPost.setPostId(1);
        testPost.setCreatorId(1);
        testPost.setPostText("Hello, this is a test post.");
        testPost.setIsVisible(false);
        
        Post response = service.addPost(testPost);
        assertEquals(response.getPostId(), testPost.getPostId(), "Checking post Id.");
        assertEquals(response.getCreatorId(), testPost.getCreatorId(), "Checking post author.");
        assertEquals(response.getPostTitle(), testPost.getPostTitle(), "Checking post title.");
        assertEquals(response.getPostText(), testPost.getPostText(), "Checking post text.");
        assertEquals(response.getIsVisible(), testPost.getIsVisible(), "Checking visible.");
    }
    
    @Test
    public void testGetPost() {
        
    }
    
    @Test
    public void testEditPost() {
        
    }
    
    @Test
    public void testDeletePost() {
        
    }
    
    @Test
    public void testGetAllPosts() {
        
    }
    
}
