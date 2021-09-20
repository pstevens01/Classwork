/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.dao;

import com.sg.superperson.dto.Location;
import com.sg.superperson.dto.Organization;
import com.sg.superperson.dto.Sighting;
import com.sg.superperson.dto.Superperson;
import com.sg.superperson.dto.Superpower;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cosmos
 */
@Repository
public interface SuperpersonDao {
    Superperson addSuperperson(Superperson superperson);

    void addSuperpersonOrg(int superId, int orgId);
    
    Organization addOrganization(Organization organization);
    
    void addOrgLocation(int orgId, int locId);
    
    Sighting addSighting(Sighting sighting);
    
    Superpower addSuperpower(Superpower superpower);

    Location addLocation(Location location);
    
    List<Superperson> getAllSuperpersons();
    
    List<Organization> getAllOrganizations();
    
    List<Superpower> getAllSuperpowers();
    
    List<Location> getAllLocations();

    List<Sighting> getAllSightings();
    
    Superperson getSuperpersonById(int id);
    
    List<Organization> getOrgBySuperpersonId(int superId);
    
    Location getLocationByOrgId(int id);
    
    Superperson getSuperpersonByName(String name);

    Organization getOrganizationByName(String name);

    Location getLocationByName(String name);
    
    Superpower getSuperpowerByName(String name);
        
    Organization getOrganizationById(int id);

    Superpower getSuperpowerById(int id);

    Location getLocationById(int id);
    
    Sighting getSightingById(int id);
    
    Sighting getSighting(String[] orgParams);
    
    List<Sighting> getRecentSightings();
    
    List<Superperson> getOrganizationMembers(int id);
    
    List<Sighting> getSightingsByDate(LocalDate date);
    
    void updateSuperperson(Superperson superperson);

    void updateOrganization(Organization organization);

    void updateOrgLocation(int orgId, int locId);
    
    void updateSighting(Sighting sighting);
    
    void updateSuperpower(Superpower superpower);
    
    void updateLocation(Location location);
    
    void updateOrganizationSuperpersonById(int orgId);
            
    void deleteSuperpersonOrgById(int superId);
        
    boolean deleteSuperpersonById(int id);
    
    boolean deleteSuperpersonOrg(int superId);
    
    boolean deleteSightingById(int id);

    boolean deleteOrganizationById(int id);
        
    boolean deleteOrgLocationById(int id);
    
    boolean deleteSightingBySuperpersonId(int id);

    boolean deleteLocOrganizationById(int id);
    
    boolean deleteSightingByLocId(int id);
    
    boolean deleteSuperpowerById(int id);
    
    boolean deleteLocationById(int id);
}
