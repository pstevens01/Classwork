/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Cosmos
 */
public class Sighting {
    private int sightingId;
    private int superpersonId;
    private int locationId;
    private LocalDateTime sightingDate;

    public Sighting() {
    }

    public Sighting(int sightingId, int superpersonId, int locationId, LocalDateTime sightingTime) {
        this.sightingId = sightingId;
        this.superpersonId = superpersonId;
        this.locationId = locationId;
        this.sightingDate = sightingTime;
    }

    public Sighting(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getSuperpersonId() {
        return superpersonId;
    }

    public void setSuperpersonId(int superpersonId) {
        this.superpersonId = superpersonId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDateTime sightingDate) {
        this.sightingDate = sightingDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.sightingId;
        hash = 89 * hash + this.superpersonId;
        hash = 89 * hash + this.locationId;
        hash = 89 * hash + Objects.hashCode(this.sightingDate);
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
        final Sighting other = (Sighting) obj;
        if (this.superpersonId != other.superpersonId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        return Objects.equals(this.sightingDate, other.sightingDate);
    }
        
}
