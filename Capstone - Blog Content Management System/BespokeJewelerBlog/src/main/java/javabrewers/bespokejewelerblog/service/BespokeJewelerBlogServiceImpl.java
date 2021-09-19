package javabrewers.bespokejewelerblog.service;

import java.util.List;
import javabrewers.bespokejewelerblog.dao.CreatorDao;
import javabrewers.bespokejewelerblog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javabrewers.bespokejewelerblog.dao.PostDao;
import javabrewers.bespokejewelerblog.model.Creator;
import javabrewers.bespokejewelerblog.model.Post;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author coleogden
 */
@Component
public class BespokeJewelerBlogServiceImpl implements BespokeJewelerBlogService {
    
    @Autowired
    PostDao postDao;
    
    @Autowired
    CreatorDao creatorDao;

    public BespokeJewelerBlogServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Post addPost(Post post) {
        return postDao.addPost(post);
    }

    public void publishPost(Post post) {
        Post publishedPost = post;
        publishedPost.setIsVisible(true);
        postDao.updatePost(publishedPost);
    }
    
    @Override
    public Post getPost(int id) {
        return postDao.getPost(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public void editPost(int id, Post post) {
        postDao.updatePost(post);
    }
    
    @Override
    public Creator getCreator(int id){
         return creatorDao.getCreator(id);
    }
    
    @Override
    public Post newPost(){
        return new Post();
    }
}
