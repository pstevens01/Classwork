/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mitchell Cummins
 */
public class Post {
    
    private int postId;
    private int creatorId;
    private Date startDate;
    private Date endDate;
    private boolean isVisible = false;
    private String postText;
    private String postTitle;
    
    List<Tag> tags = new ArrayList<>();

    public Post(int postId, int creatorId, Date startDate, Date endDate, String postText, String postTittle) {
        this.postId = postId;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postText = postText;
        this.postTitle = postTittle;
    }
    
    public Post(){
    
    }

    public int getPostId() {
        return postId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTittle) {
        this.postTitle = postTittle;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
   public void addTag(Tag tag){
       tags.add(tag);
   }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.postId;
        hash = 23 * hash + this.creatorId;
        hash = 23 * hash + Objects.hashCode(this.startDate);
        hash = 23 * hash + Objects.hashCode(this.endDate);
        hash = 23 * hash + (this.isVisible ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.postText);
        hash = 23 * hash + Objects.hashCode(this.postTitle);
        hash = 23 * hash + Objects.hashCode(this.tags);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.creatorId != other.creatorId) {
            return false;
        }
        if (this.isVisible != other.isVisible) {
            return false;
        }
        if (!Objects.equals(this.postText, other.postText)) {
            return false;
        }
        if (!Objects.equals(this.postTitle, other.postTitle)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }
    
    

   
    
    
}
