package com.mazurbeam.beltexam.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.mazurbeam.beltexam.models.*;

import com.mazurbeam.beltexam.services.*;
import com.mazurbeam.beltexam.validators.UserValidator;

@Controller
public class Users {
    
	private UserService userService;
	private IdeaService ideaService;
	
	
	private UserValidator userValidator;
	 
	public Users(UserService userService, IdeaService ideaService, UserValidator userValidator) {
		this.userService = userService;
		this.ideaService = ideaService;
	    this.userValidator = userValidator;
	 }

   
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String registration( @ModelAttribute("user") User user, BindingResult result,Model model, HttpSession session) {
    		userValidator.validate(user, result);
		if (result.hasErrors()) {
    			return "login.jsp";
    		}
    		userService.saveWithUserRole(user);
    		return "redirect:/login";
    }
    
    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) { 
		System.out.println(error);
	
    		if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "login.jsp";
    }
    
    @RequestMapping(value = {"/", "/ideas"})
    public String home(@ModelAttribute("idea") Idea idea, Model model) {
        // 1
    		
    			
        
        model.addAttribute("ideas", ideaService.findAllIdeas());
        return "ideas.jsp";
    }
    
    @PostMapping("/newIdea")
    public String newIdea(@ModelAttribute("idea") Idea idea, BindingResult result, Model model, Principal principal) {
    		if (result.hasErrors()) {
			return "ideas.jsp";
    		}
    		idea.setUser(userService.findByUsername(principal.getName()));
    		ideaService.saveIdea(idea);
    		return "redirect:/ideas";
    }
    
    @RequestMapping("/like/{ideaId}")
    public String likeIdea(@PathVariable Long ideaId, Principal principal) {
    		Idea idea = ideaService.findIdeaWithId(ideaId);
    		User user = userService.findByUsername(principal.getName());
    		List<User> liked = idea.getLiked();
    		if(liked.contains(user)) {
    			return "redirect:/ideas";
    		}
    		idea.addLike(user);;
    		ideaService.saveIdea(idea);
    		return "redirect:/ideas";
    }
    
    @RequestMapping("idea/{ideaId}")
    public String ideaStatus(@PathVariable Long ideaId, Model model, Principal principal) {
    		Idea idea = ideaService.findIdeaWithId(ideaId);
    		model.addAttribute("idea", idea);
    		return "idea.jsp";
    }
    
    @RequestMapping("user/{userId}")
    public String userStatus(@PathVariable Long userId, Model model, Principal principal) {
    		User user = userService.findById(userId);
    		model.addAttribute("user", user);
    		return "user.jsp";
    }
    
    @RequestMapping("/delete/{ideaId}")
    public String deleteIdea(@PathVariable Long ideaId, Model model, Principal principal) {
    		User user = userService.findByUsername(principal.getName());
    		Idea idea = ideaService.findIdeaWithId(ideaId);
    		if(idea.getUser() == user) {
    			ideaService.deleteIdea(idea);
    		}
    		return "redirect:/ideas";
    }
}