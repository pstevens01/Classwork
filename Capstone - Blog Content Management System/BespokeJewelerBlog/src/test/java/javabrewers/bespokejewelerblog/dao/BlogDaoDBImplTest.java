/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javabrewers.bespokejewelerblog.dao.PostDaoDBImpl;
import javabrewers.bespokejewelerblog.model.Post;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;
import javabrewers.bespokejewelerblog.dao.PostDao;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author coleogden
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogDaoDBImplTest {
    PostDao postDao;
    
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    
    public BlogDaoDBImplTest() {
        postDao = new PostDaoDBImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Post> posts = postDao.getAllPosts();
        for(Post post : posts) {
            postDao.deletePost(post.getPostId());
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testAddPost() throws Exception {
        Date startDate = dateFormatter.parse("2021-09-16");
        Date endDate = dateFormatter.parse("2022-09-16");
        Post testPost = new Post(1, 1, startDate, endDate, "This is a test post.", "Test post");
        Post response = postDao.addPost(testPost);
        
        assertEquals(response.getPostId(), testPost.getPostId(), "Checking post Id.");
        assertEquals(response.getPostTitle(), testPost.getPostTitle(), "Checking post title.");
        assertEquals(response.getCreatorId(), testPost.getCreatorId(), "Checking post creator.");
        assertEquals(response.getPostText(), testPost.getPostText(), "Checking post text.");
        assertEquals(response.getIsVisible(), testPost.getIsVisible(), "Checking if is visible.");
    }
    
    @Test
    public void testGetPost() throws Exception {
        Date startDate = dateFormatter.parse("2021-09-16");
        Date endDate = dateFormatter.parse("2022-09-16");
        Post testPost = new Post(1, 1, startDate, endDate, "Test post", "This is a test post.");
        postDao.addPost(testPost);
        
        Post response = postDao.getPost(1);
        
        assertEquals(response.getPostId(), testPost.getPostId(), "Checking post Id.");
        assertEquals(response.getPostTitle(), testPost.getPostTitle(), "Checking post title.");
        assertEquals(response.getCreatorId(), testPost.getCreatorId(), "Checking post creator.");
        assertEquals(response.getPostText(), testPost.getPostText(), "Checking post text.");
        assertEquals(response.getIsVisible(), testPost.getIsVisible(), "Checking if is visible.");
    }
    
    @Test
    public void testGetAllPosts() throws Exception {
        Date startDate = dateFormatter.parse("2021-09-16");
        Date endDate = dateFormatter.parse("2022-09-16");
        Post testPost = new Post(1, 1, startDate, endDate, "This is a test post.", "Test post");
        
        
        Date startDate2 = dateFormatter.parse("2021-09-16");
        Date endDate2 = dateFormatter.parse("2022-09-16");
        Post testPost2 = new Post(2, 2, startDate2, endDate2, "This is another test post.", "Test post 2");
        
        postDao.addPost(testPost);
        postDao.addPost(testPost2);
        
        List<Post> response = postDao.getAllPosts();
        assertEquals(response.size(),"2", "Size of post list should be 2.");
        assertTrue(response.contains(testPost));
        assertTrue(response.contains(testPost2));
    }
    
    @Test
    public void testUpdatePost() throws Exception {
        Date startDate = dateFormatter.parse("2021-09-16");
        Date endDate = dateFormatter.parse("2022-09-16");
        Post testPost = new Post(1, 1, startDate, endDate, "This is a test post.", "Test post");
        
        postDao.addPost(testPost);
        Post response = postDao.getPost(1);
        
        assertEquals(testPost, response);
        
        Date startDate2 = dateFormatter.parse("2021-09-16");
        Date endDate2 = dateFormatter.parse("2022-09-16");
        Post newTestPost = new Post(2, 2, startDate2, endDate2, "This is another test post.", "Test post 2");
        
        postDao.updatePost(newTestPost);
        
        response = postDao.getPost(1);
        assertEquals(newTestPost, response);
    }
}
