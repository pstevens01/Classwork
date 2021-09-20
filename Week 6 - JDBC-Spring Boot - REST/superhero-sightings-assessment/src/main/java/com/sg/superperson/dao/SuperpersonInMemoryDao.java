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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cosmos
 */
@Repository
@Profile("memory")
public class SuperpersonInMemoryDao implements SuperpersonDao{
    private static final List<Superperson> superpersons = new ArrayList<>();
    private static final List<Organization> organizations = new ArrayList<>();
    private static final Map<Integer, Integer> organizationLocations = new HashMap<>();
    private static final Map<Integer, Integer> superpersonOrganizations = new HashMap<>();
    private static final List<Location> locations = new ArrayList<>();
    private static final List<Superpower> superpowers = new ArrayList<>();
    private static final List<Sighting> sightings = new ArrayList<>();

    @Override
    public Superperson addSuperperson(Superperson superperson) {

        int nextId = superpersons.stream()
                .mapToInt(i -> i.getSuperpersonId())
                .max()
                .orElse(0) + 1;

        superperson.setSuperpersonId(nextId);
        superpersons.add(superperson);
        // TODO: Adding an organization
        return superperson;
    }
    
    @Override
    public Organization addOrganization(Organization organization) {
        
        int nextId = organizations.stream()
                .mapToInt(i -> i.getOrganizationId())
                .max()
                .orElse(0) + 1;
        
        organization.setOrganizationId(nextId);
        
        organizations.add(organization);
        
        return organization;
    }
    
    @Override
    public Sighting addSighting(Sighting sighting) {
        
        int nextId = sightings.stream()
                .mapToInt(i -> i.getSightingId())
                .max()
                .orElse(0) + 1;
        
        sighting.setSightingId(nextId);
        
        sightings.add(sighting);
        
        return sighting;
    }
    
    @Override
    public void addSuperpersonOrg(int superId, int orgId) {
        superpersonOrganizations.put(superId, orgId);
    }
    
    @Override
    public void addOrgLocation(int orgId, int locId) {
        organizationLocations.put(orgId, locId);
    }
    
    @Override
    public Superpower addSuperpower(Superpower superpower) {
        
        int nextId = superpowers.stream()
                .mapToInt(i -> i.getSuperpowerId())
                .max()
                .orElse(0) + 1;
        
        superpower.setSuperpowerId(nextId);
        
        superpowers.add(superpower);
        
        return superpower;
    }
    
    @Override
    public Location addLocation(Location location) {
        
        int nextId = locations.stream()
                .mapToInt(i -> i.getLocationId())
                .max()
                .orElse(0) + 1;
        
        location.setLocationId(nextId);
        
        locations.add(location);
        
        return location;
    }

    @Override
    public List<Organization> getOrgBySuperpersonId(int superId) {
        List<Organization> userOrgs = new ArrayList<>();
        organizations.stream().filter(org -> (superpersonOrganizations.get(superId) == org.getOrganizationId())).forEachOrdered(org -> {
            userOrgs.add(org);
        });
        return userOrgs;
    }
    
    @Override
    public List<Superperson> getAllSuperpersons() {
        return new ArrayList<>(superpersons);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return new ArrayList<>(organizations);
    }
    
    @Override
    public List<Superpower> getAllSuperpowers() {
        return new ArrayList<>(superpowers);
    }
    
    @Override
    public List<Location> getAllLocations() {
        return new ArrayList<>(locations);
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        return new ArrayList<>(sightings);
    }
    
    @Override
    public Superperson getSuperpersonById(int id) {
        return superpersons.stream()
                .filter(i -> i.getSuperpersonId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Superperson getSuperpersonByName(String name) {
        return superpersons.stream()
                .filter(i -> i.getSuperpersonName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Organization getOrganizationByName(String name) {
        return organizations.stream()
                .filter(i -> i.getOrganizationName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Organization getOrganizationById(int id) {
        return organizations.stream()
                .filter(i -> i.getOrganizationId()== id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Location getLocationById(int id) {
        return locations.stream()
                .filter(i -> i.getLocationId()== id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Location getLocationByName(String name) {
        return locations.stream()
                .filter(i -> i.getLocationName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Location getLocationByOrgId(int id) {
        return locations.stream()
                .filter(i -> i.getLocationId()== id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Superpower getSuperpowerByName(String name) {
        return superpowers.stream()
                .filter(i -> i.getSuperpowerName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Superpower getSuperpowerById(int id) {
        return superpowers.stream()
                .filter(i -> i.getSuperpowerId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Sighting getSightingById(int id) {
        return sightings.stream()
                .filter(i -> i.getSightingId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public Sighting getSighting(String[] orgParams) {
        int superpersonId = Integer.parseInt(orgParams[0]);
        int locationId = Integer.parseInt(orgParams[1]);
        LocalDateTime date = LocalDateTime.parse(orgParams[2]);
        
        for (Sighting sighting : sightings) {
            if (sighting.getSuperpersonId() == superpersonId)
                if (sighting.getLocationId() == locationId)
                    if (sighting.getSightingDate().equals(date))
                        return sighting;
        }
        return null;
    }
    
    @Override
    public List<Sighting> getRecentSightings() {
        if (sightings.size() < 10) {
            return sightings;
        }
        List<Sighting> recentSightings = new ArrayList<>();
        for (int i = sightings.size() - 1; i > sightings.size() - 11; i--) {
            recentSightings.add(0, sightings.get(i));
        }
        return recentSightings;
    }
    
    @Override
    public List<Superperson> getOrganizationMembers(int id) {
        List<Superperson> members = new ArrayList<>();
        for (Superperson sp : superpersons) {
            if (organizationLocations.get(sp.getSuperpersonId()) == id) {
                members.add(sp);
            }
        }
        return members;
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        List<Sighting> sightingAtDate = new ArrayList<>();
        for (Sighting sighting : sightings) {
            if (sighting.getSightingDate().toLocalDate() == date)
                sightingAtDate.add(sighting);
        }
        return sightingAtDate;
    }

    @Override
    public void updateSuperperson(Superperson superperson) {

        int index = 0;
        while (index < superpersons.size()
                && superpersons.get(index).getSuperpersonId() != superperson.getSuperpersonId()) {
            index++;
        }

        if (index < superpersons.size()) {
            superpersons.set(index, superperson);
        }
    }
    
    @Override
    public void updateOrganization(Organization organization) {

        int index = 0;
        while (index < organizations.size()
                && organizations.get(index).getOrganizationId() != 
                organization.getOrganizationId()) {
            index++;
        }

        if (index < organizations.size()) {
            organizations.set(index, organization);
        }
    }
    
    @Override
    public void updateSighting(Sighting sighting) {
        int index = 0;
        while (index < sightings.size()
                && sightings.get(index).getSightingId() != 
                sighting.getSightingId()) {
            index++;
        }

        if (index < sightings.size()) {
            sightings.set(index, sighting);
        }
    }
    
    @Override
    public void updateSuperpower(Superpower superpower) {
        int index = 0;
        while (index < superpowers.size()
                && superpowers.get(index).getSuperpowerId()!= 
                superpower.getSuperpowerId()) {
            index++;
        }

        if (index < superpowers.size()) {
            superpowers.set(index, superpower);
        }
    }
    
    @Override
    public void updateLocation(Location location) {
        int index = 0;
        while (index < locations.size()
                && locations.get(index).getLocationId()!= 
                location.getLocationId()) {
            index++;
        }

        if (index < locations.size()) {
            locations.set(index, location);
        }
    }
    
    @Override
    public void updateOrganizationSuperpersonById(int orgId) {
        for (int key : superpersonOrganizations.keySet()) {
            if (superpersonOrganizations.get(key) == orgId) {
                superpersonOrganizations.remove(key);
                return;
            }
        }
    }
    
    @Override
    public void deleteSuperpersonOrgById(int superId) {
        superpersonOrganizations.remove(superId);
    }

    @Override
    public boolean deleteSuperpersonById(int id) {
        
        for (Superperson sp : superpersons) {
            if (sp.getSuperpersonId() == id) {
                return superpersons.remove(sp);
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteSightingById(int id) {
        
        for (Sighting stg : sightings) {
            if (stg.getSightingId() == id) {
                return sightings.remove(stg);
            }
        }
        return false;
    }

    @Override
    public boolean deleteOrganizationById(int id) {
        
        for (Organization org : organizations) {
            if (org.getOrganizationId() == id) {
                return organizations.remove(org);
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteSuperpowerById(int id) {
        
        for (Superpower pow : superpowers) {
            if (pow.getSuperpowerId() == id) {
                return superpowers.remove(pow);
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteLocationById(int id) {
        
        for (Location loc : locations) {
            if (loc.getLocationId() == id) {
                return locations.remove(loc);
            }
        }
        return false;
    }
    
    @Override
    public void updateOrgLocation(int orgId, int locId) {
        organizationLocations.put(orgId, locId);
    }
    
    @Override
    public boolean deleteOrgLocationById(int id) {
        organizationLocations.remove(id);
        return !organizationLocations.keySet().contains(id);
    }
    
    @Override
    public boolean deleteLocOrganizationById(int id) {
        for (int key : organizationLocations.keySet()) {
            if (organizationLocations.get(key) == id) {
                organizationLocations.remove(key);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteSightingBySuperpersonId(int id) {
        for (Sighting sighting : sightings) {
            if (sighting.getSightingId() == id) {
                sightings.remove(sighting);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteSightingByLocId(int id) {
        for (Sighting sighting : sightings) {
            if (sighting.getLocationId()== id) {
                sightings.remove(sighting);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteSuperpersonOrg(int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
