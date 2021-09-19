/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javabrewers.bespokejewelerblog.model.Creator;
import javabrewers.bespokejewelerblog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javabrewers.bespokejewelerblog.service.BespokeJewelerBlogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author coleogden
 */
@Controller
@RequestMapping("/bespoke-jeweler")
public class BespokeJewelerBlogController {
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private int currentUserId;

    @Autowired
    private final BespokeJewelerBlogService service;

    public BespokeJewelerBlogController(BespokeJewelerBlogService gameService) {
        this.service = gameService;
    }
    
    @GetMapping("/submitPost")
    public String addPost(Model model) {
        model.addAttribute("currentCreator", null);
        model.addAttribute("post", null);
        return "submitPost";
    }
    
    @GetMapping("/submitPost/{id}")
    public String addPost(@PathVariable Integer id, @RequestParam int userId, Model model) {
        //User currentUser = session.getCurrentUser();
        Creator currentCreator = service.getCreator(userId);
//        if(currentUser.isLoggedIn()) {
//            model.addAttribute("currentUser", currentUser);
//        }
        if(currentCreator != null){
            model.addAttribute("currentCreator", currentCreator);
        }
        // Pre-populate form information with
        // stored post info for editing (if existing post)
        if (service.getPost(id) != null) {
            model.addAttribute("post", service.getPost(id));
        }
        return "submitPost";
    }
    @PostMapping("/submitPost")
    public String addPost(HttpServletRequest request) throws ParseException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        int creatorId = Integer.parseInt(request.getParameter("creatorId"));
        Date startDate = dateFormat.parse(request.getParameter("startDate")); //TODO: handle parse exception
        Date endDate = dateFormat.parse(request.getParameter("endDate"));     //TODO: handle parse exception
        String postTitle = request.getParameter("postTitle");
        String postText = request.getParameter("postText");
        String action = request.getParameter("action");
        // id will be 0 if there is no id on request
        Post post = new Post(postId, creatorId, startDate, endDate, postText, postTitle);
        if(action.equals("publish")) {
            post.setIsVisible(true);
        }
        if (postId == 0) {
            Post result = service.addPost(post);
        } else {
            service.editPost(postId, post);
        }
        return "redirect:/bespoke-jeweler/submitPost/" + postId + "?userId=" + creatorId;
    }
    
    @PostMapping("/publishPost/{postId}")
    public String publishPost(@PathVariable Integer postId, Model model){
        
        Post post = service.getPost(postId);
        service.publishPost(post);
        
        return "publishPost";
    }

    @GetMapping("viewPost/{id}")
    public String viewPost(@PathVariable Integer id, Model model) {
        Post post = service.getPost(id);

        if (post.getIsVisible()) { 
            model.addAttribute("post", post);
        }

        return "viewPost";
    }

    @GetMapping("/viewAllPosts")
    public String viewAllPosts(Model model) {
        List<Post> posts = service.getAllPosts();
        model.addAttribute("posts", posts);
        return "viewAllPosts";
    }
    
    @GetMapping("/aboutMe")
    public String aboutMe(){
        return "aboutMe";
    }
    
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
    
}
