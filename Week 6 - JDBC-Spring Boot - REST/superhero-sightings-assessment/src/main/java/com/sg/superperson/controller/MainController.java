/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.controller;

import com.sg.superperson.dto.Location;
import com.sg.superperson.dto.Organization;
import com.sg.superperson.dto.Sighting;
import com.sg.superperson.dto.Superperson;
import com.sg.superperson.dto.Superpower;
import com.sg.superperson.service.SuperpersonService;
import com.sg.superperson.service.SuperpersonServiceLayer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cosmos
 */
@Controller
public class MainController {
    Set<ConstraintViolation<Superperson>> violations = new HashSet<>();
    private final SuperpersonService service;

    public MainController(SuperpersonServiceLayer service) {
        this.service = service;
    }
    
    @GetMapping("home")
    public String homePage(Model model) {
        
        // Sightings
        List<Sighting> sightingList = service.getRecentSightings();
        model.addAttribute("sightings", sightingList);
        
        // Superpersons
        List<Superperson> superpersonList = service.getAllSuperperson();
        model.addAttribute("superpersons", superpersonList);
        
        // Superperson by name
        Map<Integer, String> superpersonsByName = superpersonList.stream().collect(
                Collectors.toMap(Superperson::getSuperpersonId, Superperson::getSuperpersonName));
        
        model.addAttribute("superpersonsById", superpersonsByName);
        
        // Locations
        List<Location> locationList = service.getAllLocations();
        model.addAttribute("locations", locationList);
        
        // Location by name
        Map<Integer, String> locationsByName = locationList.stream().collect(
                Collectors.toMap(Location::getLocationId, Location::getLocationName));
        
        model.addAttribute("locationsById", locationsByName);
        
        return "home";
    }
    
    @GetMapping("superpersons-manage")
    public String superpersonManage(Model model) {
        // Superpersons
        List<Superperson> superpersonList = service.getAllSuperperson();
        model.addAttribute("superpersons", superpersonList);
        
        // Superpowers
        List<Superpower> superpowerList = service.getAllSuperpowers();
        model.addAttribute("superpowers", superpowerList);
        
        // Superpower ID to Superpower Name
        Map<Integer, String> superpowersById = superpowerList.stream().collect(
                Collectors.toMap(Superpower::getSuperpowerId, Superpower::getSuperpowerName));
        
        model.addAttribute("superpowersById", superpowersById);
        
        // Organizations
        List<Organization> organizationList = service.getAllOrganizations();
        model.addAttribute("organizations", organizationList);
        
        // Organization ID to Organization Name
        Map<Integer, String> organizationsById = organizationList.stream().collect(
                Collectors.toMap(Organization::getOrganizationId, Organization::getOrganizationName));
        
        model.addAttribute("organizationsById", organizationsById);
        // Superperson ID to Superperson Name
        Map<Integer, String> superpersonMap = superpersonList.stream().collect(
                Collectors.toMap(Superperson::getSuperpersonId, Superperson::getSuperpersonName));
        
        // Get all the superperson keys
        List<Integer> superpersonKeys = new ArrayList<>(superpersonMap.keySet());
        // Superperson ID to Organizations
        Map<Integer, String> orgIdsBySuperpersonId = new HashMap<>();
        // Init keys
        superpersonKeys.forEach(key -> {
            orgIdsBySuperpersonId.put(key, "");
        });

        // Each superperson's organization
        superpersonList.forEach(superperson -> {
            List<Organization> userOrgs = service.getOrgBySuperpersonId(superperson.getSuperpersonId());
            for (Organization og : userOrgs) {
                String orgName = organizationsById.get(og.getOrganizationId());
                int superId = superperson.getSuperpersonId();
                String orgs = orgIdsBySuperpersonId.get(superId);
                if (orgs.length() < 1)
                    orgs = orgs + orgName;
                else
                    orgs = orgs + "," + orgName;
                
                orgIdsBySuperpersonId.put(superId, orgs);
            }            
        });
        model.addAttribute("organizationIdMap", orgIdsBySuperpersonId);        
        
        // Locations
        List<Location> locationList = service.getAllLocations();
        // Location ID to Location Name
        Map<Integer, String> locationMap = locationList.stream().collect(
                Collectors.toMap(Location::getLocationId, Location::getLocationName));
        
        // Sightings
        List<Sighting> sightingList = service.getAllSightings();
        model.addAttribute("sightings", sightingList);
        // Sighting ID to Location ID
        Map<Integer, Integer> sightingMap = sightingList.stream().collect(
                Collectors.toMap(Sighting::getSightingId, Sighting::getLocationId));
        
        List<Integer> sightingKeys = new ArrayList<>(sightingMap.keySet());
        Map<Integer, String> sightingIdsBySuperpersonId = new HashMap<>();
        // Init keys
        superpersonKeys.forEach(key -> {
            sightingIdsBySuperpersonId.put(key, "");
        });
        
        // All sightings of each superperson
        sightingList.forEach(sighting -> {
            String locationName = locationMap.get(sighting.getLocationId());
            String sightings = sightingIdsBySuperpersonId.get(sighting.getSuperpersonId());
            
            if (sightings.length() < 1)
                sightings = sightings + locationName;
            else
                if (!sightings.contains(locationName))
                    sightings = sightings + "," + locationName;
            
            sightingIdsBySuperpersonId.put(sighting.getSuperpersonId(), sightings);
        });
        model.addAttribute("sightingIdMap", sightingIdsBySuperpersonId);        
        
        return "superpersons-manage";
    }
    
    @PostMapping(value="/superpersons-manage", params="action=add")
    public String addSuperperson(HttpServletRequest request) {
                
        String name = request.getParameter("superName");
        if (service.getSuperpersonByName(name) != null)
            return "redirect:/superpersons-manage";
        
        
        String powerName = request.getParameter("pow");
        Superpower superpower = service.getSuperpowerByName(powerName);
        
        String desc = request.getParameter("desc");
        
        Superperson superperson = new Superperson();
        superperson.setSuperpersonName(name);
        superperson.setSuperpersonDesc(desc);
        superperson.setSuperpersonPowerId(superpower.getSuperpowerId());
        
        superperson = service.addSuperperson(superperson);
        
        String orgsSel = request.getParameter("orgSel");
        if (orgsSel.contains(",")) {
            String[] orgs = orgsSel.split(",");
            for (String orgName : orgs) {
                System.out.println("Hi " + orgName);
                Organization org = service.getOrganizationByName(orgName);
                service.addSuperpersonOrg(superperson.getSuperpersonId(),
                        org.getOrganizationId());
            }
        } else {
            System.out.println("Hit " + orgsSel);
            Organization org = service.getOrganizationByName(orgsSel);
                service.addSuperpersonOrg(superperson.getSuperpersonId(),
                        org.getOrganizationId());
        }
        
        return "redirect:/superpersons-manage";
    }
    
    @PostMapping(value="/superpersons-manage", params="action=edit")
    public String editSuperperson(HttpServletRequest request) {
        
        String newName = request.getParameter("superName");
        Superperson superperson = service.getSuperpersonByName(newName);
        if (superperson != null && !newName.equals(""))
            return "redirect:/superpersons-manage";
        
        // Get old superperson object
        String oldName = request.getParameter("superSel");
        superperson = service.getSuperpersonByName(oldName);
        
        // Name
        if (!newName.equals(""))
            superperson.setSuperpersonName(newName);
        
        // Update attributes
        String desc = request.getParameter("desc");
        if (!desc.equals(""))
            superperson.setSuperpersonDesc(desc);
        
        // Get power
        String powerName = request.getParameter("pow");
        if (!powerName.equals("")) {
            Superpower superpower = service.getSuperpowerByName(powerName);
            superperson.setSuperpersonPowerId(superpower.getSuperpowerId());
        }
        
        // Organization
        String orgName = request.getParameter("orgSel");
        if (orgName.contains(",")) {
            String[] orgs = orgName.split(",");
            for (String orgEntry : orgs) {
                Organization org = service.getOrganizationByName(orgEntry);
                service.addSuperpersonOrg(superperson.getSuperpersonId(),
                        org.getOrganizationId());
            }
        } else {
            Organization org = service.getOrganizationByName(orgName);
            service.deleteSuperpersonOrg(superperson.getSuperpersonId());
            service.addSuperpersonOrg(superperson.getSuperpersonId(),
                        org.getOrganizationId());
        }
        
        service.updateSuperperson(superperson);
        
        return "redirect:/superpersons-manage";
    }
        
    @PostMapping(value="/superpersons-manage", params="action=del")
    public String deleteSuperperson(HttpServletRequest request) {
        service.deleteSuperperson(request.getParameter("superSel"));
        
        return "redirect:/superpersons-manage";
    }
        
    @RequestMapping("organizations-manage")
    public String organizationsManage(Model model) {
        
        // Organizations
        List<Organization> organizationList = service.getAllOrganizations();
        model.addAttribute("organizations", organizationList);
        
        // Locations
        List<Location> locationList = service.getAllLocations();
        model.addAttribute("locations", locationList);
        
        // Location by name
        Map<Integer, String> locationsByName = locationList.stream().collect(
                Collectors.toMap(Location::getLocationId, Location::getLocationName));
        
        model.addAttribute("locationsByName", locationsByName);
        
        Map<Integer, String> orgLocation = new HashMap<>();
        Map<Integer, String> orgMembers = new HashMap<>();
        for (Organization org : organizationList) {
            int orgId = org.getOrganizationId();
            // Location ID from orgLocation table.
            Location loc = service.getLocationByOrgId(orgId);
            
            if (loc != null) {
                // Location object from location table.
                loc = service.getLocationById(loc.getLocationId());
                orgLocation.put(orgId, loc.getLocationName());
            }
            orgMembers.put(orgId, service.getOrganizationMembers(orgId));
        }
        model.addAttribute("orgLocation", orgLocation);
        model.addAttribute("orgMembers", orgMembers);
        
        return "organizations-manage";
    }
    
    @PostMapping(value="/organizations-manage", params="action=add")
    public String addOrganization(HttpServletRequest request) {
        
        String name = request.getParameter("orgName");
        String desc = request.getParameter("orgDesc");
        String orgAddr = request.getParameter("orgAddr");
        
        // Check if exists already.
        Organization org = service.getOrganizationByName(name);
        if (org != null)
            return "redirect:/organizations-manage";
        
        Location loc = service.getLocationByName(request.getParameter("locName"));
        
        org = new Organization();
        org.setOrganizationName(name);
        org.setOrganizationDesc(desc);
        org.setOrganizationAddr(orgAddr);
        
        org = service.addOrganization(org);
        // Add Org Location
        service.addOrgLocation(org.getOrganizationId(), loc.getLocationId());
        
        return "redirect:/organizations-manage";
    }
    
    @PostMapping(value="/organizations-manage", params="action=edit")
    public String editOrganization(HttpServletRequest request, Model model) {
        
        String selOrg = request.getParameter("orgSel");
        String locName = request.getParameter("locName");
        String name = request.getParameter("orgName");
        String desc = request.getParameter("orgDesc");
        String addr = request.getParameter("orgAddr");
        if (selOrg.equals(""))
            return "redirect:/organizations-manage";
        
        Organization organization = service.getOrganizationByName(selOrg);
        
        if (!name.equals(""))
            organization.setOrganizationName(name);
        
        if (!desc.equals(""))
            organization.setOrganizationDesc(desc);
        
        if (!addr.equals(""))
            organization.setOrganizationAddr(addr);
        
        service.updateOrganization(organization);
        if (!locName.equals("")) {
            Location loc = service.getLocationByName(locName);
            service.updateOrgLocation(organization.getOrganizationId(), loc.getLocationId());
        }
        
        return "redirect:/organizations-manage";
    }
        
    @PostMapping(value="organizations-manage", params="action=del")
    public String deleteOrganization(HttpServletRequest request) {
        String orgSel = request.getParameter("orgSel");
        if (orgSel != null)
            service.deleteOrganization(orgSel);
        
        return "redirect:/organizations-manage";
    }
    
    @GetMapping("sightings-manage")
    public String sightingsManage(Model model) {
        // Superpersons
        List<Superperson> superpersonList = service.getAllSuperperson();
        model.addAttribute("superpersons", superpersonList);
        
        // Superperson by name
        Map<Integer, String> superpersonsByName = superpersonList.stream().collect(
                Collectors.toMap(Superperson::getSuperpersonId, Superperson::getSuperpersonName));
        
        model.addAttribute("superpersonsByName", superpersonsByName);
        
        // Locations
        List<Location> locationList = service.getAllLocations();
        model.addAttribute("locations", locationList);
        
        // Sightings
        List<Sighting> sightingList = service.getAllSightings();
        model.addAttribute("sightings", sightingList);
        
        // Location by name
        Map<Integer, String> locationsByName = locationList.stream().collect(
                Collectors.toMap(Location::getLocationId, Location::getLocationName));
        
        model.addAttribute("locationsByName", locationsByName);        
        
        // Sighting keys (sightingDate)
        List<LocalDate> sightingKeys = new ArrayList<>();
        // List of sightings in string format
        List<String> nameLocDateList = new ArrayList<>();
        for (Sighting sighting : sightingList) {
            String entry = superpersonsByName.get(sighting.getSuperpersonId()) + ","
            + locationsByName.get(sighting.getLocationId()) + "," + sighting.getSightingDate();
            
            nameLocDateList.add(entry);
            sightingKeys.add(sighting.getSightingDate().toLocalDate());
        }
        model.addAttribute("nameLocDates", nameLocDateList);
        
        // Date to sighting as string
        Map<String, String> sightingByDate = new HashMap<>();
        List<String> dates = new ArrayList<>();
        // Get all sightings by date
        sightingKeys.stream().map(date -> {
            dates.add(date.toString());
            return date;
        }).forEachOrdered(date -> {
            String entry = "";
            List<Sighting> sightingAtDate = service.getSightingsByDate(date);
            
            // Each sighting on current date
            for (Sighting st : sightingAtDate) {
                if (entry.equals(""))
                    entry = entry + superpersonsByName.get(st.getSuperpersonId()) + "@" + locationsByName.get(st.getLocationId());
                else
                    entry = entry + ", " + superpersonsByName.get(st.getSuperpersonId()) + "@" + locationsByName.get(st.getLocationId());
            }
            sightingByDate.put(date.toString(), entry);
        });
        model.addAttribute("dates", dates);
        model.addAttribute("sightingByDate", sightingByDate);
                
        return "sightings-manage";
    }

    @PostMapping(value="/sightings-manage", params="action=add")
    public String addSighting(HttpServletRequest request) {
        
        // Setup object attributes
        String superName = request.getParameter("superName");
        String locName = request.getParameter("locName");
        String dateTime = request.getParameter("dateTime").replace('T', ' ');
        
        Superperson superperson = service.getSuperpersonByName(superName);
        Location location = service.getLocationByName(locName);
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(location.getLocationId());
        sighting.setSuperpersonId(superperson.getSuperpersonId());
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
            LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
                        
            sighting.setSightingDate(date);
        } catch (DateTimeParseException ex) {
            return "redirect:/sightings-manage";
        }

        service.addSighting(sighting);
        
        return "redirect:/sightings-manage";
    }
    
    @PostMapping(value="/sightings-manage", params="action=edit")
    public String editSighting(HttpServletRequest request) {
                
        // Get original sighting object ID
        String locDate = request.getParameter("locDate");
        // Superperson Name, Location Name, Datetime
        String[] orgParams = locDate.split(",");
        
        int superId = service.getSuperpersonByName(orgParams[0]).getSuperpersonId();
        orgParams[0] = Integer.toString(superId);
        
        int locId = service.getLocationByName(orgParams[1]).getLocationId();
        orgParams[1] = Integer.toString(locId);
        
        orgParams[2] = orgParams[2].replace('T', ' ');
        
        Sighting sighting = service.getSighting(orgParams);
        
        // Setup object attributes
        String superName = request.getParameter("superName");
        String locName = request.getParameter("locName");
        String dateTime = request.getParameter("dateTime").replace('T', ' ');
        
        // Check for blank input.
        if (!dateTime.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
            try {
                LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
                sighting.setSightingDate(date);
            } catch (DateTimeParseException ex) {
                return "sightings-manage";
            }
            
        }
        if (!superName.equals("")) {
            Superperson superperson = service.getSuperpersonByName(superName);
            if (superperson != null)
                sighting.setSuperpersonId(superperson.getSuperpersonId());
        }
        if (!locName.equals("")) {
            Location location = service.getLocationByName(locName);
            if (location != null)
                sighting.setLocationId(location.getLocationId());
        }
        
        service.updateSighting(sighting);
        
        return "redirect:/sightings-manage";
    }
        
    @PostMapping(value="sightings-manage", params="action=del")
    public String deleteSighting(HttpServletRequest request) {
        service.deleteSighting(request.getParameter("locDate"));
        
        return "redirect:/sightings-manage";
    }
    
    @GetMapping("superpowers-manage")
    public String superpowersManage(Model model) {
        
        // Superpowers
        List<Superpower> superpowerList = service.getAllSuperpowers();
        model.addAttribute("superpowers", superpowerList);
        
        return "superpowers-manage";
    }

    @PostMapping(value="/superpowers-manage", params="action=add")
    public String addSuperpower(HttpServletRequest request) {
        
        // Setup object attributes
        String powerName = request.getParameter("powName");
        
        // Check if exists already.
        Superpower pow = service.getSuperpowerByName(powerName);
        if (pow != null)
            return "redirect:/superpowers-manage";

        pow = new Superpower();
        pow.setSuperpowerName(powerName);
        
        service.addSuperpower(pow);
        
        return "redirect:/superpowers-manage";
    }
    
    @PostMapping(value="/superpowers-manage", params="action=edit")
    public String editSuperpower(HttpServletRequest request) {
                
        String oldName = request.getParameter("powSel");
        Superpower pow = service.getSuperpowerByName(oldName);
        if (pow == null)
            return "redirect:/superpowers-manage";
        
        String newName = request.getParameter("powName");
        pow.setSuperpowerName(newName);
        
        service.updateSuperpower(pow);
        
        return "redirect:/superpowers-manage";
    }
        
    @PostMapping(value="superpowers-manage", params="action=del")
    public String deleteSuperpower(HttpServletRequest request) {
        service.deleteSuperpower(request.getParameter("powSel"));
        
        return "redirect:/superpowers-manage";
    }
    
    @GetMapping("/locations-manage")
    public String locationsManage(Model model) {
        // Sightings
        List<Sighting> sightingList = service.getAllSightings();
        model.addAttribute("sightings", sightingList);
        
        // Locations
        List<Location> locationList = service.getAllLocations();
        model.addAttribute("locations", locationList);
        
        // Superpersons
        List<Superperson> superpersonList = service.getAllSuperperson();
        model.addAttribute("superpersons", superpersonList);
        
        // Superpersons ID to Name
        Map<Integer, String> superpersonMap = superpersonList.stream().collect(
                Collectors.toMap(Superperson::getSuperpersonId, Superperson::getSuperpersonName));
        
        // Location by name
        Map<Integer, String> locationsByName = locationList.stream().collect(
                Collectors.toMap(Location::getLocationId, Location::getLocationName));
        
        model.addAttribute("locationsByName", locationsByName);
        
        List<Integer> locationKeys = new ArrayList<>(locationsByName.keySet());
        Map<Integer, String> sightingsByLocationId = new HashMap<>();
        // Init keys
        locationKeys.forEach(key -> {
            sightingsByLocationId.put(key, "");
        });
        
        // Get all superpersons spotted at each location
        sightingList.forEach(sighting -> {
            // Superperson spotted
            String superName = superpersonMap.get(sighting.getSuperpersonId());
            // Location superpersons spotted
            String sightings = sightingsByLocationId.get(sighting.getLocationId());
            
            if (sightings.length() < 1)
                sightings = sightings + superName;
            else
                if (!sightings.contains(superName))
                    sightings = sightings + "," + superName;
                        
            sightingsByLocationId.put(sighting.getLocationId(), sightings);
        });
        model.addAttribute("locationIdMap", sightingsByLocationId);   
        
        return "locations-manage";
    }

    @PostMapping(value="/locations-manage", params="action=add")
    public String addLocation(HttpServletRequest request) {
        
        String locSel = request.getParameter("locSel");
        Location loc = service.getLocationByName(locSel);
        if (loc != null)
            return "locations-manage";
        
        String locName = request.getParameter("locName");
        String locDesc = request.getParameter("desc");
        String locAddr = request.getParameter("addr");
        String locCords = request.getParameter("cord");
        
        loc = new Location();
        loc.setLocationName(locName);
        loc.setLocationDesc(locDesc);
        loc.setLocationAddr(locAddr);
        loc.setLocationCoords(locCords);
        
        service.addLocation(loc);
        
        return "redirect:/locations-manage";
    }
    
    @PostMapping(value="/locations-manage", params="action=edit")
    public String editLocation(HttpServletRequest request) {
                
        String oldName = request.getParameter("locSel");
        Location loc = service.getLocationByName(oldName);
        if (loc == null)
            return "locations-manage";
        
        String newName = request.getParameter("locName");
        if (!newName.equals(""))
            loc.setLocationName(newName);

        String locDesc = request.getParameter("desc");
        if (!locDesc.equals(""))
            loc.setLocationDesc(locDesc);
        
        String locAddr = request.getParameter("addr");
        if (!locAddr.equals(""))
            loc.setLocationAddr(locAddr);
        
        String locCords = request.getParameter("cord");
        if (!locCords.equals(""))
            loc.setLocationCoords(locCords);
        
        service.updateLocation(loc);
        
        return "redirect:/locations-manage";
    }
        
    @PostMapping(value="/locations-manage", params="action=del")
    public String deleteLocation(HttpServletRequest request) {
        service.deleteLocation(request.getParameter("locSel"));
        
        return "redirect:/locations-manage";
    }
}
