/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.service;

import com.sg.superperson.dto.Location;
import com.sg.superperson.dto.Organization;
import com.sg.superperson.dto.Sighting;
import com.sg.superperson.dto.Superperson;
import com.sg.superperson.dto.Superpower;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cosmos
 */
@Service
public interface SuperpersonService {
    Superperson addSuperperson(Superperson superperson);
    
    void addSuperpersonOrg(int superId, int orgId);
    
    Organization addOrganization(Organization organization);
    
    void addOrgLocation(int orgId, int locId);
    
    Sighting addSighting(Sighting sighting);
    
    Superpower addSuperpower(Superpower superpower);

    Location addLocation(Location location);
    
    List<Superperson> getAllSuperperson();
    
    List<Organization> getAllOrganizations();
    
    List<Organization> getOrgBySuperpersonId(int superId);
    
    List<Superpower> getAllSuperpowers();
    
    List<Location> getAllLocations();

    List<Sighting> getAllSightings();
    
    Superperson getSuperpersonByName(String name);
    
    Organization getOrganizationByName(String name);

    Location getLocationByName(String name);

    Superpower getSuperpowerByName(String name);
    
    Organization getOrganizationById(int id);

    Location getLocationByOrgId(int id);
    
    Superpower getSuperpowerById(int id);
    
    Location getLocationById(int id);
    
    Sighting getSightingById(int id);
    
    Sighting getSighting(String[] orgParams);
    
    List<Sighting> getRecentSightings();
    
    String getOrganizationMembers(int id);
    
    List<Sighting> getSightingsByDate(LocalDate date);
    
    void updateSuperperson(Superperson guessNumber);
    
    void updateOrganization(Organization organization);

    void updateOrgLocation(int orgId, int locId);

    void updateSighting(Sighting sighting);
    
    void updateSuperpower(Superpower superpower);
    
    void updateLocation(Location location);
    
    boolean deleteSuperperson(String name);
    
    boolean deleteSuperpersonOrg(int superId);

    boolean deleteOrganization(String name);
    
    boolean deleteOrgLocationById(int orgId);
    
    boolean deleteSighting(String entry);
    
    boolean deleteSuperpower(String name);
    
    boolean deleteLocation(String name);
}
