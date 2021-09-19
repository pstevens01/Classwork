/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.service;

import java.util.List;
import javabrewers.bespokejewelerblog.model.Creator;
import javabrewers.bespokejewelerblog.model.Post;

/**
 *
 * @author coleogden
 */
public interface BespokeJewelerBlogService {
    public Post addPost(Post post);
    
    public void publishPost(Post post);

    public Post getPost(int id);

    public List<Post> getAllPosts();

    public void editPost(int id, Post newPost);
    
    public Creator getCreator(int id);
    
    public Post newPost();
    
}
