package com.aronkatona.controllerTicketShop;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aronkatona.manager.EventManager;
import com.aronkatona.manager.UserManager;
import com.aronkatona.model.Chair;
import com.aronkatona.model.Event;
import com.aronkatona.model.User;
import com.aronkatona.server.ServerThread;
import com.aronkatona.service.ChairService;
import com.aronkatona.service.EventService;
import com.aronkatona.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private UserManager userManager = new UserManager();
	private EventManager eventManager = new EventManager();
	private ServerThread serverThread = ServerThread.getInstance();
	private ChairService chairService;
	
	
	@Autowired
	public void setMailService(JavaMailSender mailSender){
		this.userManager.setMailSender(mailSender);
	}

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us) {
		this.userManager.setUserService(us);
		this.serverThread.setUserService(us);
	}
	

	@Autowired(required = true)
	@Qualifier(value = "eventService")
	public void setEventService(EventService es) {
		this.eventManager.setEventService(es);
	}
	
	@Autowired(required = true)
	@Qualifier(value = "chairService")
	public void setChairService(ChairService cs) {
		this.chairService = cs;
	}
	
	@RequestMapping(value="/error404")
	public String errorPage(Locale locale){
		return "error";
	}
	
	@RequestMapping(value="/")
	public String goToHome(){
		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpSession session) {
		model.addAttribute("successSignUp",session.getAttribute("successSignUp"));
		session.setAttribute("successSignUp", "");
		model.addAttribute("successLogin", session.getAttribute("successLogin"));
		session.setAttribute("successLogin","");
		
		if(session.getAttribute("userName") == null){
			session.setAttribute("userName", "");
		}
		
		if(!session.getAttribute("userName").equals("") ){
			return "redirect:/welcome";
		}
		
		return "home";
	}
	
	@RequestMapping(value="/activate.{activationString}")
	public String activateRegistration(Locale locale,Model model, @PathVariable String activationString){
		this.userManager.activateUser(activationString);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/loginForm")
	public String loginForm(){
		
		//1.lepes: event, userek
		Event event1 = new Event("mozi", "film1", "budapest", new Date(), 100,10,10);
		Event event2 = new Event("mozi", "film2", "szeged", new Date(), 100,20,5);
		this.eventManager.saveEvent(event1);
		this.eventManager.saveEvent(event2);
		
		User user1 = new User("asd", "asdfdgf@waedfdgf.asdfd", "123");
		User user2 = new User("bsd", "asdfdgf@waedfdgf.asdfd", "123");
		User user3 = new User("csd", "asdfdgf@waedfdgf.asdfd", "123");
		this.userManager.saveUser(user1);
		this.userManager.saveUser(user2);
		this.userManager.saveUser(user3);
		
		Event e1 = this.eventManager.getEventService().getEventById(1);
		User u1 = this.userManager.getUserById(2);
		u1.getEvents().add(e1);
		e1.getUsers().add(u1);
		this.userManager.getUserService().updateUser(u1);
		
		
		//2.lepes szekek hozzadasa
		Event eventChair1 = this.eventManager.getEventService().getEventById(1);
		for(int i = 1; i < 11; ++i){
			for(int j = 1; j < 11; ++j){
				Chair chair = new Chair(850,i,j);
				eventChair1.getChairs().add(chair);
				chair.getEventChairs().add(eventChair1);
				this.eventManager.getEventService().updateEvent(eventChair1);
			}
		}
		
		Event eventChair2 = this.eventManager.getEventService().getEventById(2);
		for(int i = 1; i < 21; ++i){
			for(int j = 1; j < 6; ++j){
				Chair chair = new Chair(850,i,j);
				eventChair2.getChairs().add(chair);
				chair.getEventChairs().add(eventChair2);
				this.eventManager.getEventService().updateEvent(eventChair2);
			}
		}
		
	  

		
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model,HttpSession session,@RequestParam Map<String, String> reqPar){

		User user = this.userManager.loginUser(reqPar);

		if(user != null && session.getAttribute("userName").equals("")  ){
			session.setAttribute("userName",  user.getName());
			return "redirect:/welcome";
		} 
		else if(user != null && session.getAttribute("userName").equals(user.getName())){
			session.setAttribute("userName", user.getName());
			return "redirect:/welcome";
		}
		else if( !session.getAttribute("userName").equals("")){
			session.setAttribute("successLogin", "alreadyIn");
			return "redirect:/home";
		}		
		else if( user == null){
			session.setAttribute("successLogin", "notSuccessLogin");
			return "redirect:/home";	
		} 
		return "redirect:/home";
	}
	
	@RequestMapping(value="/signupForm")
	public String signupForm(Model model){
		return "signup";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model, @RequestParam Map<String,String> reqPar,HttpSession session){
		
		if(this.userManager.signUpUser(reqPar)){
			session.setAttribute("successSignUp", "successSignUp");
		} else{
			session.setAttribute("successSignUp", "notSuccessSignUp");
		}
		
		return "redirect:/home";
	}
	
}
