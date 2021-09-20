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
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Cosmos
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SuperpersonDatabaseDaoTest {
    
    @Autowired
    SuperpersonDao superDao;
    
    public SuperpersonDatabaseDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Location> locations = superDao.getAllLocations();
        for (Location location : locations) {
            superDao.deleteLocationById(location.getLocationId());
        }
        
        List<Organization> organizations = superDao.getAllOrganizations();
        for (Organization organization : organizations) {
            superDao.deleteOrganizationById(organization.getOrganizationId());
        }
        
        List<Sighting> sightings = superDao.getAllSightings();
        for (Sighting sighting : sightings) {
            superDao.deleteSightingById(sighting.getSightingId());
        }
        
        List<Superperson> superpersons = superDao.getAllSuperpersons();
        for (Superperson superperson : superpersons) {
            superDao.deleteSuperpersonById(superperson.getSuperpersonId());
        }
        
        List<Superpower> superpowers = superDao.getAllSuperpowers();
        for (Superpower superpower : superpowers) {
            superDao.deleteSuperpowerById(superpower.getSuperpowerId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addSuperperson method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddSuperperson() {
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        Superperson fromDao = superDao.getSuperpersonById(superperson.getSuperpersonId());
        
        assertEquals(superperson, fromDao);
    }

    /**
     * Test of addSuperpersonOrg method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddSuperpersonOrg() {
        int superId = 1;
        int orgId = 1;
        
        superDao.addSuperpersonOrg(superId, orgId);
        
        Organization org = superDao.getOrgBySuperpersonId(superId).get(0);
        
        assertEquals(orgId, org.getOrganizationId());
    }

    /**
     * Test of addOrganization method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddOrganization() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Organization fromDao = superDao.getOrganizationById(organization.getOrganizationId());
        
        assertEquals(organization, fromDao);
    }

    /**
     * Test of addOrgLocation method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddOrgLocation() {
        int orgId = 1;
        int locId = 1;
        
        superDao.addOrgLocation(orgId, locId);
        
        Location loc = superDao.getLocationByOrgId(orgId);
        
        assertEquals(locId, loc.getLocationId());
    }

    /**
     * Test of addSighting method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddSighting() {
        Sighting sighting = new Sighting();
        sighting.setSuperpersonId(1);
        sighting.setLocationId(1);
        sighting.setSightingDate(LocalDateTime.now());
        sighting = superDao.addSighting(sighting);
        
        Sighting fromDao = superDao.getSightingById(sighting.getSightingId());
        
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of addSuperpower method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("TestName");
        superpower = superDao.addSuperpower(superpower);
        
        Superpower fromDao = superDao.getSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of addLocation method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testAddLocation() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Location fromDao = superDao.getLocationById(location.getLocationId());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of getOrgBySuperpersonId method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetOrgBySuperpersonId() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        superDao.addSuperpersonOrg(1, organization.getOrganizationId());
        
        Organization fromDao = superDao.getOrgBySuperpersonId(1).get(0);
        
        assertEquals(organization.getOrganizationId(), fromDao.getOrganizationId());
    }

    /**
     * Test of getAllSuperpersons method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetAllSuperpersons() {
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        Superperson superperson2 = new Superperson();
        superperson2.setSuperpersonName("TestName");
        superperson2.setSuperpersonDesc("TestDesc");
        superperson2.setSuperpersonPowerId(1);
        superperson2 = superDao.addSuperperson(superperson2);
        
        List<Superperson> superpersons = superDao.getAllSuperpersons();
        
        assertEquals(2, superpersons.size());
        assertTrue(superpersons.contains(superperson));
        assertTrue(superpersons.contains(superperson2));
    }

    /**
     * Test of getAllOrganizations method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Organization organization2 = new Organization();
        organization2.setOrganizationName("TestName");
        organization2.setOrganizationDesc("TestDesc");
        organization2.setOrganizationAddr("TestAddr");
        organization2 = superDao.addOrganization(organization2);
        
        List<Organization> orgs = superDao.getAllOrganizations();
        
        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(organization));
        assertTrue(orgs.contains(organization2));
    }

    /**
     * Test of getAllLocations method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Location location2 = new Location();
        location2.setLocationName("TestName");
        location2.setLocationDesc("TestDesc");
        location2.setLocationAddr("TestAddr");
        location2.setLocationCoords("TestCoords");
        location2 = superDao.addLocation(location2);
        
        List<Location> locs = superDao.getAllLocations();
        
        assertEquals(2, locs.size());
        assertTrue(locs.contains(location));
        assertTrue(locs.contains(location2));
    }

    /**
     * Test of getAllSightings method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetAllSightings() {
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(LocalDateTime.now());
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        Sighting sighting2 = new Sighting();
        sighting2.setLocationId(1);
        sighting2.setSightingDate(LocalDateTime.now());
        sighting2.setSuperpersonId(1);
        sighting2 = superDao.addSighting(sighting2);
        
        List<Sighting> sightings = superDao.getAllSightings();
        
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    /**
     * Test of getLocationByOrgId method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetLocationByOrgId() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        superDao.addOrgLocation(1, location.getLocationId());
        
        Location fromDao = superDao.getLocationByOrgId(1);
        
        assertEquals(fromDao.getLocationId(), location.getLocationId());
    }

    /**
     * Test of getOrganizationById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetOrganizationById() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Organization fromDao = superDao.getOrganizationById(organization.getOrganizationId());
        
        assertEquals(organization, fromDao);
    }

    /**
     * Test of getSightingById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetSightingById() {
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(LocalDateTime.now());
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        Sighting fromDao = superDao.getSightingById(sighting.getSightingId());
        
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of getSighting method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetSighting() {
        LocalDateTime now = LocalDateTime.now();
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(now);
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        String[] orgParams = {"1", String.valueOf(sighting.getSightingId()), now.toString()};
        
        Sighting fromDao = superDao.getSighting(orgParams);
        
        assertEquals(sighting,fromDao);
    }

    /**
     * Test of getRecentSightings method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetRecentSightings() {
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(LocalDateTime.now());
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        List<Sighting> sightings = superDao.getRecentSightings();
        
        assertEquals(1, sightings.size());
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("TestName");
        superpower = superDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = superDao.getAllSuperpowers();
        
        assertEquals(1, superpowers.size());
    }

    /**
     * Test of getSuperpowerById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetSuperpowerById() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("TestName");
        superpower = superDao.addSuperpower(superpower);
        
        Superpower fromDao = superDao.getSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of getLocationById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetLocationById() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Location fromDao = superDao.getLocationById(location.getLocationId());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of getSuperpersonByName method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetSuperpersonByName() {
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        Superperson fromDao = superDao.getSuperpersonByName(superperson.getSuperpersonName());
        
        assertEquals(superperson, fromDao);
    }

    /**
     * Test of getOrganizationByName method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetOrganizationByName() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Organization fromDao = superDao.getOrganizationByName(organization.getOrganizationName());
        
        assertEquals(organization, fromDao);
    }

    /**
     * Test of getLocationByName method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetLocationByName() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Location fromDao = superDao.getLocationByName(location.getLocationName());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of getSuperpowerByName method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetSuperpowerByName() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("TestName");
        superpower = superDao.addSuperpower(superpower);
        
        Superpower fromDao = superDao.getSuperpowerByName(superpower.getSuperpowerName());
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of getOrganizationMembers method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetOrganizationMembers() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        superDao.addSuperpersonOrg(superperson.getSuperpersonId(), organization.getOrganizationId());
        
        List<Superperson> fromDao = superDao.getOrganizationMembers(organization.getOrganizationId());
                
        assertEquals(1, fromDao.size());
    }

    /**
     * Test of getSightingsByDate method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testGetSightingsByDate() {
        LocalDateTime now = LocalDateTime.now();
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(now);
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        List<Sighting> sightings = superDao.getSightingsByDate(now.toLocalDate());
        
        assertEquals(1, sightings.size());
    }

    /**
     * Test of updateSuperperson method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateSuperperson() {
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        Superperson fromDao = superDao.getSuperpersonById(superperson.getSuperpersonId());
        assertEquals(superperson, fromDao);
        
        superperson.setSuperpersonName("NewNameTest");
        superDao.updateSuperperson(superperson);
        
        assertNotEquals(superperson, fromDao);
        
        fromDao = superDao.getSuperpersonById(superperson.getSuperpersonId());
        
        assertEquals(superperson, fromDao);
    }

    /**
     * Test of updateOrganization method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Organization fromDao = superDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
        
        organization.setOrganizationName("NewNameTest");
        superDao.updateOrganization(organization);
        
        assertNotEquals(organization, fromDao);
        
        fromDao = superDao.getOrganizationById(organization.getOrganizationId());
        
        assertEquals(organization, fromDao);
    }

    /**
     * Test of updateOrgLocation method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateOrgLocation() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        superDao.addOrgLocation(organization.getOrganizationId(), location.getLocationId());
        
        Location fromDao = superDao.getLocationByOrgId(organization.getOrganizationId());
        assertEquals(location, fromDao);
        
        Location location2 = new Location();
        location2.setLocationName("TestName");
        location2.setLocationDesc("TestDesc");
        location2.setLocationAddr("TestAddr");
        location2.setLocationCoords("TestCoords");
        location2 = superDao.addLocation(location2);
        
        superDao.updateOrgLocation(organization.getOrganizationId(), location2.getLocationId());
        fromDao = superDao.getLocationByOrgId(organization.getOrganizationId());
        
        assertNotEquals(location, fromDao);
                
        assertEquals(location2, fromDao);
    }

    /**
     * Test of updateSighting method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateSighting() {
        LocalDateTime now = LocalDateTime.now();
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(now);
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        Sighting fromDao = superDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
        
        sighting.setSuperpersonId(2);
        superDao.updateSighting(sighting);
        
        assertNotEquals(sighting, fromDao);
        
        fromDao = superDao.getSightingById(sighting.getSightingId());
        
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of updateSuperpower method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("TestName");
        superpower = superDao.addSuperpower(superpower);
        
        Superpower fromDao = superDao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(superpower, fromDao);
        
        superpower.setSuperpowerName("NewTestName");
        superDao.updateSuperpower(superpower);
        
        assertNotEquals(superpower, fromDao);
        
        fromDao = superDao.getSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of updateLocation method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Location fromDao = superDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
        
        location.setLocationName("NewTestName");
        superDao.updateLocation(location);
        
        assertNotEquals(location, fromDao);
        
        fromDao = superDao.getLocationById(location.getLocationId());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteSuperpersonById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteSuperpersonById() {
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        Superperson fromDao = superDao.getSuperpersonById(superperson.getSuperpersonId());
        assertEquals(superperson, fromDao);
        
        superDao.deleteSuperpersonById(superperson.getSuperpersonId());
                
        fromDao = superDao.getSuperpersonById(superperson.getSuperpersonId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteSightingById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteSightingById() {
        LocalDateTime now = LocalDateTime.now();
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(now);
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        Sighting fromDao = superDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
        
        superDao.deleteSightingById(sighting.getSightingId());
                
        fromDao = superDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteOrganizationById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteOrganizationById() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Organization fromDao = superDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
        
        superDao.deleteOrganizationById(organization.getOrganizationId());
                
        fromDao = superDao.getOrganizationById(organization.getOrganizationId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteOrgLocationById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteOrgLocationById() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        superDao.addOrgLocation(organization.getOrganizationId(), location.getLocationId());
        
        Location fromDao = superDao.getLocationByOrgId(organization.getOrganizationId());
        assertEquals(location, fromDao);
        
        superDao.deleteOrgLocationById(organization.getOrganizationId());
                
        fromDao = superDao.getLocationByOrgId(organization.getOrganizationId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteSightingBySuperpersonId method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteSightingBySuperpersonId() {
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        LocalDateTime now = LocalDateTime.now();
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(1);
        sighting.setSightingDate(now);
        sighting.setSuperpersonId(superperson.getSuperpersonId());
        sighting = superDao.addSighting(sighting);
        
        Sighting fromDao = superDao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao.getSuperpersonId(), superperson.getSuperpersonId());
        
        superDao.deleteSightingBySuperpersonId(superperson.getSuperpersonId());
                
        fromDao = superDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteSightingByLocId method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteSightingByLocId() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        LocalDateTime now = LocalDateTime.now();
        Sighting sighting = new Sighting();
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingDate(now);
        sighting.setSuperpersonId(1);
        sighting = superDao.addSighting(sighting);
        
        Sighting fromDao = superDao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao.getLocationId(), location.getLocationId());
        
        superDao.deleteSightingByLocId(location.getLocationId());
                
        fromDao = superDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteLocOrganizationById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteLocOrganizationById() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Location fromDao = superDao.getLocationByOrgId(organization.getOrganizationId());
        assertEquals(fromDao.getLocationId(), location.getLocationId());
        
        superDao.deleteLocOrganizationById(location.getLocationId());
                
        fromDao = superDao.getLocationByOrgId(organization.getOrganizationId());
        assertNull(fromDao);
    }

    /**
     * Test of updateOrganizationSuperpersonById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testUpdateOrganizationSuperpersonById() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        superDao.addSuperpersonOrg(superperson.getSuperpersonId(), organization.getOrganizationId());
        
        Organization fromDao = superDao.getOrgBySuperpersonId(superperson.getSuperpersonId()).get(0);
        assertEquals(organization, fromDao);
                
        superDao.updateOrganizationSuperpersonById(organization.getOrganizationId());
                        
        fromDao = superDao.getOrgBySuperpersonId(superperson.getSuperpersonId()).get(0);
        assertNull(fromDao);
    }

    /**
     * Test of deleteSuperpersonOrgById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteSuperpersonOrgById() {
        Organization organization = new Organization();
        organization.setOrganizationName("TestName");
        organization.setOrganizationDesc("TestDesc");
        organization.setOrganizationAddr("TestAddr");
        organization = superDao.addOrganization(organization);
        
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName("TestName");
        superperson.setSuperpersonDesc("TestDesc");
        superperson.setSuperpersonPowerId(1);
        superperson = superDao.addSuperperson(superperson);
        
        superDao.addSuperpersonOrg(superperson.getSuperpersonId(), organization.getOrganizationId());
        
        Organization fromDao = superDao.getOrgBySuperpersonId(superperson.getSuperpersonId()).get(0);
        assertEquals(organization, fromDao);
                
        superDao.deleteSuperpersonOrgById(superperson.getSuperpersonId());
                        
        fromDao = superDao.getOrgBySuperpersonId(superperson.getSuperpersonId()).get(0);
        assertNull(fromDao);
    }

    /**
     * Test of deleteSuperpowerById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteSuperpowerById() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("TestName");
        superpower = superDao.addSuperpower(superpower);
        
        Superpower fromDao = superDao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(fromDao, superpower);
        
        superDao.deleteSuperpowerById(superpower.getSuperpowerId());
                
        fromDao = superDao.getSuperpowerById(superpower.getSuperpowerId());
        assertNull(fromDao);
    }

    /**
     * Test of deleteLocationById method, of class SuperpersonDatabaseDao.
     */
    @Test
    public void testDeleteLocationById() {
        Location location = new Location();
        location.setLocationName("TestName");
        location.setLocationDesc("TestDesc");
        location.setLocationAddr("TestAddr");
        location.setLocationCoords("TestCoords");
        location = superDao.addLocation(location);
        
        Location fromDao = superDao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
        
        superDao.deleteLocOrganizationById(location.getLocationId());
                
        fromDao = superDao.getLocationById(location.getLocationId());
        assertNull(fromDao);
    }
    
}
