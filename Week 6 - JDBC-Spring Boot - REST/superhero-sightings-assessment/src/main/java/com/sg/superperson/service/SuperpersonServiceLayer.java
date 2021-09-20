/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.service;

import com.sg.superperson.dao.SuperpersonDatabaseDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import com.sg.superperson.dao.SuperpersonDao;
import com.sg.superperson.dto.Location;
import com.sg.superperson.dto.Organization;
import com.sg.superperson.dto.Sighting;
import com.sg.superperson.dto.Superperson;
import com.sg.superperson.dto.Superpower;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Cosmos
 */
@Service
@Profile({"service", "prod"})
public class SuperpersonServiceLayer implements SuperpersonService {
    
    private final SuperpersonDao dao;

    @Autowired
    public SuperpersonServiceLayer(SuperpersonDatabaseDao dao) {
        this.dao = dao;
    }
    
    @Override
    public Superperson addSuperperson(Superperson superperson) {
        return dao.addSuperperson(superperson);
    }
    
    @Override
    public void addSuperpersonOrg(int superId, int orgId) {
        List<Organization> orgs = dao.getOrgBySuperpersonId(superId);
        if (orgs != null)
            for (Organization org : orgs) {
                if (org.getOrganizationId() == orgId)
                    return;
            }
        
        dao.addSuperpersonOrg(superId, orgId);
    }
    
    @Override
    public Organization addOrganization(Organization organization) {
        return dao.addOrganization(organization);
    }
    
    @Override
    public void addOrgLocation(int orgId, int locId) {
        dao.addOrgLocation(orgId, locId);
    }
    
    @Override
    public Sighting addSighting(Sighting sighting) {
        
        if (sighting == null)
            return null;
        
        if (dao.getAllSightings().contains(sighting))
            return null;
        
        return dao.addSighting(sighting);
    }
    
    @Override
    public Superpower addSuperpower(Superpower superpower) {
        
        if (superpower == null)
            return null;
        
        if (dao.getAllSuperpowers().contains(superpower))
            return null;
        
        return dao.addSuperpower(superpower);
    }
    
    @Override
    public Location addLocation(Location location) {
        
        if (location == null)
            return null;
        
        if (dao.getAllLocations().contains(location))
            return null;
        
        return dao.addLocation(location);
    }

    @Override
    public List<Superperson> getAllSuperperson() {
        
        List<Superperson> result = dao.getAllSuperpersons();
        
        if (result.size() < 1)
            result = new ArrayList<>();        
        
        return result;
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> result = dao.getAllOrganizations();
        
        if (result.size() < 1)
            result = new ArrayList<>();        
        
        return result;
    }
    
    @Override
    public List<Organization> getOrgBySuperpersonId(int superId) {
        return dao.getOrgBySuperpersonId(superId);
    }
    
    @Override
    public List<Superpower> getAllSuperpowers() {
        List<Superpower> result = dao.getAllSuperpowers();
        
        if (result.size() < 1)
            result = new ArrayList<>();        
        
        return result;
    }
    
    @Override
    public List<Location> getAllLocations() {
        List<Location> result = dao.getAllLocations();
        
        if (result.size() < 1)
            result = new ArrayList<>();        
        
        return result;
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> result = dao.getAllSightings();
        
        if (result.size() < 1)
            result = new ArrayList<>();        
        
        return result;
    }
    
    @Override
    public Superperson getSuperpersonByName(String name) {
        List<Superperson> sp = dao.getAllSuperpersons();
        
        if (sp.size() < 1)
            return null;
        
        boolean found = false;
        for (Superperson superperson : sp) {
            if (superperson.getSuperpersonName().equals(name)) {
                found = true;
                break;
            }
        }
        if (!found)
            return null;
        
        return dao.getSuperpersonByName(name);
    }
    
    @Override
    public Organization getOrganizationByName(String name) {
        if (dao.getAllOrganizations().size() < 1)
            return null;   
        
        return dao.getOrganizationByName(name);
    }
    
    @Override
    public Organization getOrganizationById(int id) {
        if (dao.getAllOrganizations().size() < 1 || id == 0) {
            return null;
        }
        Organization og = dao.getOrganizationById(id);
        
        return og;
    }
    
    @Override
    public Location getLocationByName(String name) {
        
        List<Location> locations = dao.getAllLocations();
        
        if (locations.size() < 1)
            return null;
        
        boolean found = false;
        for(Location loc : locations)
            if (loc.getLocationName().equals(name)) {
                found = true;
                break;
            }
        
        if (!found)
            return null;
        
        return dao.getLocationByName(name);
    }
    
    @Override
    public Superpower getSuperpowerByName(String name) {
        
        List<Superpower> superpowers = dao.getAllSuperpowers();
        
        if (superpowers.size() < 1 || !superpowers.contains(new Superpower(name)))
            return null;
        
        return dao.getSuperpowerByName(name);
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        if (dao.getAllSuperpersons().size() < 1 || id == 0) {
            return null;
        }
        Superpower sp = dao.getSuperpowerById(id);
        
        return sp;
    }
    
    @Override
    public Location getLocationById(int id) {
        if (dao.getAllLocations().size() < 1 || id == 0) {
            return null;
        }
        Location loc = dao.getLocationById(id);
        
        return loc;
    }
    
    @Override
    public Location getLocationByOrgId(int id) {
        if (dao.getAllOrganizations().size() < 1 || id == 0) {
            return null;
        }
        Location loc = dao.getLocationByOrgId(id);
        
        return loc;
    }
    
    @Override
    public Sighting getSightingById(int id) {
        if (dao.getAllOrganizations().size() < 1 || id == 0) {
            return null;
        }
        
        return dao.getSightingById(id);
    }
    
    @Override
    public Sighting getSighting(String[] orgParams) {
        for (String param : orgParams)
            if (param == null)
                return null;
        
        return dao.getSighting(orgParams);
    }
    
    @Override
    public List<Sighting> getRecentSightings() {
        List<Sighting> sightings = dao.getRecentSightings();
        
        return sightings;
    }
    
    @Override
    public String getOrganizationMembers(int id) {
        List<Superperson> members = dao.getOrganizationMembers(id);
        List<String> memberNames = new ArrayList<>();
        for (Superperson sp : members) {
            memberNames.add(sp.getSuperpersonName());
        }
        String memberString = memberNames.stream().collect(Collectors.joining(", "));
        
        return memberString;
    }
    
    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return dao.getSightingsByDate(date);
    }
    
    public boolean sightingExists(Sighting sighting) {
        return dao.getAllSightings().contains(sighting);
    }

    @Override
    public void updateSuperperson(Superperson superperson) {
        
        if (superperson == null)
            return;
        
        dao.updateSuperperson(superperson);
    }
    
    @Override
    public void updateOrganization(Organization organization) {
        
        if (organization == null)
            return;
        
        dao.updateOrganization(organization);
    }
    
    @Override
    public void updateOrgLocation(int orgId, int locId) {
        
        if (getOrganizationById(orgId) == null || getLocationById(locId) == null)
            return;
        
        dao.updateOrgLocation(orgId, locId);
    }
    
    @Override
    public void updateSighting(Sighting sighting) {
        
        if (sighting == null)
            return;
        
        dao.updateSighting(sighting);
    }
    
    @Override
    public void updateSuperpower(Superpower superpower) {
        
        if (superpower == null)
            return;
        
        dao.updateSuperpower(superpower);
    }
    
    @Override
    public void updateLocation(Location location) {
        
        if (location == null)
            return;
        
        dao.updateLocation(location);
    }

    @Override
    public boolean deleteSuperperson(String name) {
        List<Superperson> superpersons = dao.getAllSuperpersons();
        Superperson sp = getSuperpersonByName(name);
        if (superpersons.size() < 1 || name == null || sp == null) {
            System.out.println(sp);
            return false;
        }
        
        return dao.deleteSuperpersonById(sp.getSuperpersonId());
    }
    
    @Override
    public boolean deleteSuperpersonOrg(int superId) {
        return dao.deleteSuperpersonOrg(superId);
    }
    
    @Override
    public boolean deleteOrganization(String name) {
        List<Organization> organizations = dao.getAllOrganizations();
        Organization org = getOrganizationByName(name);
        if (organizations.size() < 1 || name == null || org == null) {
            return false;
        }
        
        return dao.deleteOrganizationById(org.getOrganizationId());
    }
    
    @Override
    public boolean deleteOrgLocationById(int orgId) {
        Organization org = getOrganizationById(orgId);
        if (org == null)
            return false;
        
        return dao.deleteOrgLocationById(org.getOrganizationId());
    }
    
    @Override
    public boolean deleteSighting(String entry) {
        List<Sighting> sightings = dao.getAllSightings();
        List<Location> locations = dao.getAllLocations();
        
        if (sightings.size() < 1) {
            return false;
        }
        
        // Location by name
        Map<String, Integer> locationsById = locations.stream().collect(
                Collectors.toMap(Location::getLocationName, Location::getLocationId));
        
        // Name,LocationName,Date
        String[] entryArr = entry.split(",");
        for (Sighting sighting : sightings) {
            // Location match
            if (sighting.getLocationId() == locationsById.get(entryArr[1])) {
                // Sighting date match
                if (sighting.getSightingDate().toString().equals(entryArr[2]))
                    return dao.deleteSightingById(sighting.getSightingId());
            }
        }
        
        return false;
    }
    
    @Override
    public boolean deleteSuperpower(String name) {
        List<Superpower> superpowers = dao.getAllSuperpowers();
        Superpower pow = getSuperpowerByName(name);
        if (superpowers.size() < 1 || name == null) {
            return false;
        }
                
        return dao.deleteSuperpowerById(pow.getSuperpowerId());
    }
    
    @Override
    public boolean deleteLocation(String name) {
        List<Location> organizations = dao.getAllLocations();
        Location org = getLocationByName(name);
        if (organizations.size() < 1 || name == null) {
            return false;
        }
        
        return dao.deleteLocationById(org.getLocationId());
    }
}
