/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.model;

/**
 *
 * @author Mitchell Cummins
 */
public class Creator {
    
    private int creatorId;
    private boolean canPost;

    public Creator(int creatorId, boolean canPost) {
        this.creatorId = creatorId;
        this.canPost = canPost;
    }

    public Creator() {
    }

    public int getCreatorId() {
        return creatorId;
    }

    public boolean getCanPost() {
        return canPost;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.creatorId;
        hash = 67 * hash + (this.canPost ? 1 : 0);
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
        final Creator other = (Creator) obj;
        if (this.creatorId != other.creatorId) {
            return false;
        }
        if (this.canPost != other.canPost) {
            return false;
        }
        return true;
    }

    
}
