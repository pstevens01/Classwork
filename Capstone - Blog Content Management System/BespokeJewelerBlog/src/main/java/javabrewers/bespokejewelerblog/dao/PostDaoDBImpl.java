/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javabrewers.bespokejewelerblog.model.Post;
import javabrewers.bespokejewelerblog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mitchell Cummins
 */
@Repository
public class PostDaoDBImpl implements PostDao {

    @Autowired
    JdbcTemplate jdbc;
    
    //adds a post to the database assums everything but ID is already in post.
    @Override
    public Post addPost(Post post) {
        final String INSERT_POST  = "INSERT INTO post(creatorId,postTitle,postText,startDate,endDate,isVisible) VALUE (?,?,?,?,?,?)";
         jdbc.update(INSERT_POST,
                 post.getCreatorId(),
                 post.getPostTitle(),
                 post.getPostText(),
                 post.getStartDate(),
                 post.getEndDate(),
                 post.getIsVisible());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setPostId(newId);
        return post;
    }
    
    //updates a post in the data base currently not implemented but will need everything when passed in EVEN ID.
    @Override
    public Post updatePost(Post post) {
        final String UPDATE_POST  = "UPDATE post set creatorId = ?, postTitle = ?,postText = ?,startDate = ?,endDate = ?,isVisible= ? where postId = ?" ;
        jdbc.update(UPDATE_POST,
                 post.getCreatorId(),
                 post.getPostTitle(),
                 post.getPostText(),
                 post.getStartDate(),
                 post.getEndDate(),
                 post.getIsVisible(),
                 post.getPostId());
        return post;
    }

    //gets all posts that are valid, this means current date is withing their date range and they are visible.
    @Override
    public List<Post> getAllPosts() {
        final String SELECT_ALL_POSTS = "SELECT * FROM post WHERE CURDATE() >= startDate AND CURDATE() <= endDate AND isVisible = 1";
        return jdbc.query(SELECT_ALL_POSTS, new PostMapper());
    }
    
    //gets all valid visible posts with a specific tag, takes in that tags ID.
    @Override
    public List<Post> getAllPosts(int tagId) {
        final String SELECT_ALL_POSTS = "SELECT * FROM post p"
                + "inner join posttag pt ON p.postId = pt.postId"
                + "WHERE CURDATE() >= startDate AND CURDATE() <= endDate AND isVisible = true AND pt.tagId = ?";
        return jdbc.query(SELECT_ALL_POSTS, new PostMapper(),tagId);
    }
    
    //returns a specific post, takes in the selected posts ID.
    @Override
    public Post getPost(int postId) {
        final String SELECT_POST = "SELECT * FROM post WHERE postId = ?";
        Post post = jdbc.queryForObject(SELECT_POST, new PostMapper(),postId);
        return post;
    }
   
    @Override
    public void deletePost(int postId){
	final String DELETE_POSTTAG = "DELETE FROM posttag WHERE postId = ?";
	jdbc.update(DELETE_POSTTAG, postId);
	    
	final String DELETE_POST = "DELETE FROM post WHERE postId = ?";
	jdbc.update(DELETE_POST, postId);
    }
    
    public static final class PostMapper implements RowMapper<Post> {
        
        @Override
        public Post mapRow(ResultSet rs, int index) throws SQLException{
            Post post = new Post();
            post.setPostId(rs.getInt("postId"));
            post.setCreatorId(rs.getInt("creatorId"));
            post.setPostTitle(rs.getString("postTitle"));
            post.setPostText(rs.getString("postText"));
            post.setStartDate(rs.getDate("startDate"));
            post.setEndDate(rs.getDate("endDate"));
            post.setIsVisible(rs.getBoolean("isVisible"));
            return post;
        }
    }
}
