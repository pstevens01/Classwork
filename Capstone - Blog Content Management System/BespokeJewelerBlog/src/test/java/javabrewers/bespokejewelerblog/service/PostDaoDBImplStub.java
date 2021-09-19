/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javabrewers.bespokejewelerblog.model.Post;
import javabrewers.bespokejewelerblog.dao.PostDao;

/**
 *
 * @author coleogden
 */
public class PostDaoDBImplStub implements PostDao {
    
    Map<Integer, Post> postMap = new HashMap<>();

    @Override
    public Post addPost(Post post) {
        postMap.put(post.getPostId(), post);
        return postMap.get(post.getPostId());
    }
    
    @Override
    public Post updatePost(Post post){
        postMap.put(post.getPostId(), post);
        return postMap.get(post.getPostId());
    }

    @Override
    public Post getPost(int postId) {
        return postMap.get(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList(postMap.values());
    }
    
    @Override
    public List<Post> getAllPosts(int tagId){
        return new ArrayList();
    }

    @Override
    public void deletePost(int postId) {
        postMap.remove(postId);
    }

    
}
