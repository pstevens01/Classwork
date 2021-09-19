/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javabrewers.bespokejewelerblog.model.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mitchell Cummins
 */
@Repository
public class CreatorDaoDBImpl implements CreatorDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    //adds a creator to the database.
    @Override
    public Creator addCreator(Creator creator) {
        final String INSERT_CREATOR = "INSERT INTO creator(canPost) VALUE (?)";
        jdbc.update(INSERT_CREATOR,
                creator.getCanPost());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        creator.setCreatorId(newId);
        return creator;
    }

    //returns a list of all creators.
    @Override
    public List<Creator> getAllCreators() {
        final String SELECT_ALL_CREATORS = "SELECT * FROM creator";
        return jdbc.query(SELECT_ALL_CREATORS,new CreatorMapper());
    }

    //returns a list of all creators that can post.
    @Override
    public List<Creator> getAllCanPostCreators() {
        final String SELECT_ALL_CAN_POST_CREATORS = "SELECT * FROM creator WHERE canPost = 1";
        return jdbc.query(SELECT_ALL_CAN_POST_CREATORS,new CreatorMapper());
    }

    //returns a specified creator by taking in a creator ID.
    @Override
    public Creator getCreator(int creatorId) {
        final String SELECT_ALL_CREATORS = "SELECT * FROM creator WHERE creatorId = ?";
        return jdbc.queryForObject(SELECT_ALL_CREATORS,new CreatorMapper(),creatorId);
    }
   
    public static final class CreatorMapper implements RowMapper<Creator> {
        
        @Override
        public Creator mapRow(ResultSet rs, int index) throws SQLException{
            Creator creator = new Creator();
            creator.setCreatorId(rs.getInt("creatorId"));
            creator.setCanPost(rs.getBoolean("canPost"));
            return creator;
        }
    }
}
