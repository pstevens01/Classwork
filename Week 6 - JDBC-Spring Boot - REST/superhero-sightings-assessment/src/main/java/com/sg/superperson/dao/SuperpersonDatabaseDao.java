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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"database", "prod"})
public class SuperpersonDatabaseDao implements SuperpersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuperpersonDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Superperson addSuperperson(Superperson superperson) {
        final String sql = "INSERT INTO superperson(superpersonName,superpersonDesc,superpowerId) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, superperson.getSuperpersonName());
            statement.setString(2, superperson.getSuperpersonDesc());
            statement.setInt(3, superperson.getSuperpersonPowerId());
            return statement;

        }, keyHolder);

        superperson.setSuperpersonId(keyHolder.getKey().intValue());

        return superperson;
    }
    
    @Override
    public void addSuperpersonOrg(int superId, int orgId) {
        final String sql = "INSERT INTO superpersonOrganization(superpersonId,organizationId) VALUES(?,?);";

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, superId);
            statement.setInt(2, orgId);
            
            return statement;
        });
    }
    
    @Override
    public Organization addOrganization(Organization organization) {
        final String sql = "INSERT INTO organization(organizationName,organizationDesc,organizationAddr) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, organization.getOrganizationName());
            statement.setString(2, organization.getOrganizationDesc());
            statement.setString(3, organization.getOrganizationAddr());
            return statement;

        }, keyHolder);

        organization.setOrganizationId(keyHolder.getKey().intValue());

        return organization;
    }
    
    @Override
    public void addOrgLocation(int orgId, int locId) {
        final String sql = "INSERT INTO organizationLocation(organizationId,locationId) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, orgId);
            statement.setInt(2, locId);
            return statement;

        }, keyHolder);
    }
    
    @Override
    public Sighting addSighting(Sighting sighting) {
        final String sql = "INSERT INTO sighting(superpersonId,locationId,sightingDate) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, sighting.getSuperpersonId());
            statement.setInt(2, sighting.getLocationId());
            statement.setTimestamp(3, Timestamp.valueOf(sighting.getSightingDate()));
            return statement;

        }, keyHolder);

        sighting.setSightingId(keyHolder.getKey().intValue());

        return sighting;
    }
    
    @Override
    public Superpower addSuperpower(Superpower superpower) {
        final String sql = "INSERT INTO superpower(superpowerId,superpowerName) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, superpower.getSuperpowerId());
            statement.setString(2, superpower.getSuperpowerName());
            return statement;

        }, keyHolder);

        superpower.setSuperpowerId(keyHolder.getKey().intValue());

        return superpower;
    }
    
    @Override
    public Location addLocation(Location location) {
        final String sql = "INSERT INTO location(locationId,locationName,locationDesc,locationAddr,locationCoords) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, location.getLocationId());
            statement.setString(2, location.getLocationName());
            statement.setString(3, location.getLocationDesc());
            statement.setString(4, location.getLocationAddr());
            statement.setString(5, location.getLocationCoords());
            return statement;

        }, keyHolder);

        location.setLocationId(keyHolder.getKey().intValue());

        return location;
    }
    
    @Override
    public List<Organization> getOrgBySuperpersonId(int superId) {        
        final String sql = "SELECT * FROM superpersonOrganization WHERE superpersonId = ?;";
        
        return jdbcTemplate.query(sql, new superpersonOrg(), superId);
    }

    @Override
    public List<Superperson> getAllSuperpersons() {
        final String sql = "SELECT superpersonId,superpersonName,superpersonDesc,superpowerId FROM superperson;";
        return jdbcTemplate.query(sql, new SuperpersonMapper());
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        final String sql = "SELECT organizationId,organizationName,organizationDesc,organizationAddr FROM organization;";
        return jdbcTemplate.query(sql, new OrganizationMapper());
    }
        
    @Override
    public List<Location> getAllLocations() {
        final String sql = "SELECT locationId,locationName,locationDesc,locationAddr,locationCoords FROM location;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        final String sql = "SELECT sightingId,superpersonId,locationId,sightingDate FROM sighting;";
        return jdbcTemplate.query(sql, new SightingMapper());
    }
    
    @Override
    public Location getLocationByOrgId(int id) {
        try {
            final String sql = "SELECT ORG.organizationId, LOC.locationId"
                    + " FROM organization ORG JOIN organizationLocation OL"
                    + " ON ORG.organizationId = OL.organizationId"
                    + " JOIN location LOC"
                    + " ON LOC.locationId = OL.locationId"
                    + " WHERE ORG.organizationId = ?;";
            return jdbcTemplate.queryForObject(sql,new orgLocationMapper(),id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
        
    @Override
    public Organization getOrganizationById(int id) {
        final String sql = "SELECT organizationId,organizationName,organizationDesc,organizationAddr "
                + "FROM organization WHERE organizationId = ?;";
        return jdbcTemplate.queryForObject(sql, new OrganizationMapper(), id);
    }
    
    @Override
    public Sighting getSightingById(int id) {
        final String sql = "SELECT sightingId,superpersonId,locationId,sightingDate "
                + "FROM sighting WHERE superpresonId = ?;";
        
        return jdbcTemplate.queryForObject(sql, new SightingMapper(), id);
    }
    
    @Override
    public Sighting getSighting(String[] orgParams) {
        final String sql = "SELECT sightingId,superpersonId,locationId,sightingDate "
                + "FROM sighting WHERE superpersonId = ? AND locationId = ? AND sightingDate = ?;";
        
        int superpersonId = Integer.parseInt(orgParams[0]);
        int locationId = Integer.parseInt(orgParams[1]);
                
        return jdbcTemplate.queryForObject(sql, new SightingMapper(), superpersonId, locationId, orgParams[2]);
    }
    
    @Override
    public List<Sighting> getRecentSightings() {
        final String sql = "SELECT * FROM sighting order by sightingDate DESC LIMIT 10;";
        
        return jdbcTemplate.query(sql, new SightingMapper());
    }
    
    @Override
    public List<Superpower> getAllSuperpowers() {
        final String sql = "SELECT superpowerId,superpowerName FROM superpower;";
        return jdbcTemplate.query(sql, new SuperpowerMapper());
    }

    @Override
    public Superperson getSuperpersonById(int id) {
        final String sql = "SELECT superperson.* FROM superperson "
                + "WHERE superperson.superpersonId = ?;";
        return jdbcTemplate.queryForObject(sql, new SuperpersonMapper(), id);
    }
    
    @Override
    public Superpower getSuperpowerById(int id) {
        final String sql = "SELECT superpowerId,superpowerName FROM superpower "
                + "WHERE superpowerId = ?;";
        return jdbcTemplate.queryForObject(sql, new SuperpowerMapper(), id);
    }
    
    @Override
    public Location getLocationById(int id) {
        final String sql = "SELECT locationId,locationName,locationDesc,locationAddr,"
                + "locationCoords FROM location "
                + "WHERE locationId = ?;";
        return jdbcTemplate.queryForObject(sql, new LocationMapper(), id);
    }
    
    @Override
    public Superperson getSuperpersonByName(String name) {
        final String sql = "SELECT superpersonId,superpersonName,superpersonDesc,superpowerId "
                + "FROM superperson WHERE superpersonName = ?;";

        return jdbcTemplate.queryForObject(sql, new SuperpersonMapper(), name);
    }
    
    @Override
    public Organization getOrganizationByName(String name) {
        try {
            final String sql = "SELECT organizationId,organizationName,organizationDesc,organizationAddr "
                    + "FROM organization WHERE organizationName = ? LIMIT 1;";

            return jdbcTemplate.queryForObject(sql, new OrganizationMapper(), name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public Location getLocationByName(String name) {

        final String sql = "SELECT locationId,locationName,locationDesc,locationAddr,locationCoords "
                + "FROM location WHERE locationName = ? LIMIT 1;";

        return jdbcTemplate.queryForObject(sql, new LocationMapper(), name);
    }
    
    @Override
    public Superpower getSuperpowerByName(String name) {

        final String sql = "SELECT superpowerId,superpowerName "
                + "FROM superpower WHERE superpowerName = ?;";

        return jdbcTemplate.queryForObject(sql, new SuperpowerMapper(), name);
    }
    
    @Override
    public List<Superperson> getOrganizationMembers(int id) {
        final String sql = "SELECT SP.* from organization ORG "
                + "join superpersonOrganization SO on "
                + "ORG.organizationId = SO.organizationId "
                + "LEFT JOIN superperson SP on SP.superpersonId = SO.superpersonId "
                + "WHERE ORG.organizationId = ?;";
        
        return jdbcTemplate.query(sql, new SuperpersonMapper(), id);
    }
    
    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        final String sql = "SELECT sighting.* FROM sighting "
                + "WHERE CAST(sighting.sightingDate as DATE) = ?;";

        return jdbcTemplate.query(sql, new SightingMapper(), date);
    }
    
    @Override
    public void updateSuperperson(Superperson superperson) {
        final String sql = "UPDATE superperson "
                + "SET superpersonName = ?, superpersonDesc = ?, "
                + "superpowerId = ? "
                + "WHERE superpersonId = ?;";

        jdbcTemplate.update(sql, superperson.getSuperpersonName(), 
                superperson.getSuperpersonDesc(), superperson.getSuperpersonPowerId(), 
                superperson.getSuperpersonId());
    }
    
    @Override
    public void updateOrganization(Organization organization) {
        final String sql = "UPDATE organization "
                + "SET organizationName = ?, organizationDesc = ?, "
                + "organizationAddr = ? "
                + "WHERE organizationId = ?;";

        jdbcTemplate.update(sql, organization.getOrganizationName(), 
                organization.getOrganizationDesc(), organization.getOrganizationAddr(), 
                organization.getOrganizationId());
    }
    
    @Override
    public void updateOrgLocation(int orgId, int locId) {
        final String sql = "UPDATE organizationLocation "
                + "SET locationId = ? "
                + "WHERE organizationId = ?;";

        jdbcTemplate.update(sql, locId, orgId);
    }
    
    @Override
    public void updateSighting(Sighting sighting) {
        final String sql = "UPDATE sighting "
                + "SET superpersonId = ?, locationId = ?, "
                + "sightingDate = ? "
                + "WHERE sightingId = ?;";
        
        jdbcTemplate.update(sql, sighting.getSuperpersonId(), 
                sighting.getLocationId(), sighting.getSightingDate(), 
                sighting.getSightingId());
    }
    
    @Override
    public void updateSuperpower(Superpower superpower) {
        final String sql = "UPDATE superpower "
                + "SET superpowerName = ? "
                + "WHERE superpowerId = ?;";
        
        jdbcTemplate.update(sql, superpower.getSuperpowerName(), 
                superpower.getSuperpowerId());
    }
    
    @Override
    public void updateLocation(Location location) {
        final String sql = "UPDATE location "
                + "SET locationName = ?, locationDesc = ?, "
                + "locationAddr = ?, locationCoords = ? "
                + "WHERE locationId = ?;";
        
        jdbcTemplate.update(sql, location.getLocationName(),
                location.getLocationDesc(), location.getLocationAddr(),
                location.getLocationCoords(), location.getLocationId());
    }

    @Override
    public boolean deleteSuperpersonById(int id) {
        deleteSightingBySuperpersonId(id);
        deleteSuperpersonOrgById(id);
        final String sql = "DELETE FROM superperson "
                + "WHERE superpersonId = ?;";

        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public boolean deleteSuperpersonOrg(int superId) {
        final String sql = "DELETE FROM superpersonOrganization "
                + "WHERE superpersonId = ?;";
        
        return jdbcTemplate.update(sql, superId) == 1;
    }
    
    @Override
    public boolean deleteSightingById(int id) {
        final String sql = "DELETE FROM sighting "
                + "WHERE sightingId = ?;";

        return jdbcTemplate.update(sql, id) == 1;
    }
        
    @Override
    public boolean deleteOrganizationById(int id) {
        // Foreign key constraints
        deleteOrgLocationById(id);
        updateOrganizationSuperpersonById(id);
        final String sql = "DELETE FROM organization WHERE organizationId = ?;";

        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public boolean deleteOrgLocationById(int id) {
        final String sql = "DELETE FROM organizationLocation"
                    + " WHERE organizationId = ?;";
        
        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public boolean deleteSightingBySuperpersonId(int id) {
        final String sql = "DELETE FROM sighting"
                    + " WHERE superpersonId = ?;";
        
        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public boolean deleteSightingByLocId(int id) {
        final String sql = "DELETE FROM sighting"
                    + " WHERE locationId = ?;";
        
        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public boolean deleteLocOrganizationById(int id) {
        final String sql = "DELETE FROM organizationLocation"
                    + " WHERE locationId = ?;";
        
        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public void updateOrganizationSuperpersonById(int orgId) {
        final String sql = "DELETE FROM superpersonOrganization "
                + "WHERE organizationId=?;";

        jdbcTemplate.update(sql, orgId);
    }
    
    @Override
    public void deleteSuperpersonOrgById(int superId) {
        final String sql = "DELETE FROM superpersonOrganization "
                + "WHERE superpersonId=?;";

        jdbcTemplate.update(sql, superId);
    }
    
    @Override
    public boolean deleteSuperpowerById(int id) {
        final String sql = "DELETE FROM superpower "
                + "WHERE superpowerId = ?;";

        return jdbcTemplate.update(sql, id) == 1;
    }
    
    @Override
    public boolean deleteLocationById(int id) {
        deleteLocOrganizationById(id);
        deleteSightingByLocId(id);
        final String sql = "DELETE FROM location "
                + "WHERE locationId = ?;";

        return jdbcTemplate.update(sql, id) == 1;
    }
    
    private static final class SuperpersonMapper implements RowMapper<Superperson> {

        @Override
        public Superperson mapRow(ResultSet rs, int index) throws SQLException {
            Superperson sp = new Superperson();
            sp.setSuperpersonId(rs.getInt("superpersonId"));
            sp.setSuperpersonName(rs.getString("superpersonName"));
            sp.setSuperpersonDesc(rs.getString("superpersonDesc"));
            sp.setSuperpersonPowerId(rs.getInt("superpowerId"));
            
            return sp;
        }
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organizationId"));
            org.setOrganizationName(rs.getString("organizationName"));
            org.setOrganizationDesc(rs.getString("organizationDesc"));
            org.setOrganizationAddr(rs.getString("organizationAddr"));
            
            return org;
        }
    }
    
    private static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower sp = new Superpower();
            sp.setSuperpowerId(rs.getInt("superpowerId"));
            sp.setSuperpowerName(rs.getString("superpowerName"));
            
            return sp;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getInt("locationId"));
            loc.setLocationName(rs.getString("locationName"));
            loc.setLocationDesc(rs.getString("locationDesc"));
            loc.setLocationAddr(rs.getString("locationAddr"));
            loc.setLocationCoords(rs.getString("locationCoords"));
            
            return loc;
        }
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            Sighting stg = new Sighting();
            stg.setSightingId(rs.getInt("sightingId"));
            stg.setSuperpersonId(rs.getInt("superpersonId"));
            stg.setLocationId(rs.getInt("locationId"));
            
            stg.setSightingDate(rs.getObject("sightingDate", LocalDateTime.class));
            
            return stg;
        }
    }
    
    private static final class orgLocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getInt("locationId"));
            
            return loc;
        }
    }
    
    private static final class superpersonOrg implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organizationId"));
            
            return org;
        }
    }
}