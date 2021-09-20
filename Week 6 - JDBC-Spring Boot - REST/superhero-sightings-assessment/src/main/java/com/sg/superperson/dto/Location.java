/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.dto;

import java.util.Objects;

/**
 *
 * @author Cosmos
 */
public class Location {
    private int locationId;
    private String locationName;
    private String locationDesc;
    private String locationAddr;
    private String locationCoords;

    public Location(int locationId, String locationName, String locationDesc, String locationAddr, String locationCoords) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationDesc = locationDesc;
        this.locationAddr = locationAddr;
        this.locationCoords = locationCoords;
    }

    public Location() {
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getLocationAddr() {
        return locationAddr;
    }

    public void setLocationAddr(String locationAddr) {
        this.locationAddr = locationAddr;
    }

    public String getLocationCoords() {
        return locationCoords;
    }

    public void setLocationCoords(String locationCoords) {
        this.locationCoords = locationCoords;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.locationId;
        hash = 59 * hash + Objects.hashCode(this.locationName);
        hash = 59 * hash + Objects.hashCode(this.locationDesc);
        hash = 59 * hash + Objects.hashCode(this.locationAddr);
        hash = 59 * hash + Objects.hashCode(this.locationCoords);
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
        final Location other = (Location) obj;
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDesc, other.locationDesc)) {
            return false;
        }
        if (!Objects.equals(this.locationAddr, other.locationAddr)) {
            return false;
        }
        return Objects.equals(this.locationCoords, other.locationCoords);
    }    
}
