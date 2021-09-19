/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.util.List;
import javabrewers.bespokejewelerblog.model.Post;

/**
 *
 * @author Mitchell Cummins
 */
public interface PostDao {

    Post addPost(Post post);
    
    Post updatePost(Post post);
    
    List<Post> getAllPosts();
    
    List<Post> getAllPosts(int tagId);
    
    Post getPost(int postId);
    
    public void deletePost(int postId);
}
