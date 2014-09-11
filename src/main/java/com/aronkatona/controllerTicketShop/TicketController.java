package com.aronkatona.controllerTicketShop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aronkatona.manager.EventManager;
import com.aronkatona.manager.UserManager;
import com.aronkatona.model.Event;
import com.aronkatona.service.ChairService;
import com.aronkatona.service.EventService;
import com.aronkatona.service.UserService;

@Controller
public class TicketController {

	private EventManager eventManager = new EventManager();
	private UserManager userManager = new UserManager();
	private ChairService chairService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us) {
		this.userManager.setUserService(us);
		this.eventManager.setUserService(us);
	}
	
	@Autowired(required = true)
	@Qualifier(value = "eventService")
	public void setEventService(EventService es) {
		this.eventManager.setEventService(es);
		this.userManager.setEventService(es);
	}
	
	@Autowired(required = true)
	@Qualifier(value = "chairService")
	public void setChairService(ChairService cs) {
		this.eventManager.setChairService(cs);
		this.chairService = cs;
	}
	
	@RequestMapping(value="/welcome")
	public String welcome(Model model,HttpSession session){
		model.addAttribute("eventList", this.eventManager.getEvents());
		model.addAttribute("myEvents",this.userManager.getMyEvents(session.getAttribute("userName").toString()));
		model.addAttribute("myChairs",this.userManager.getMyChairs(session.getAttribute("userName").toString()));
		return "welcome";
	}
	
	@RequestMapping(value="/welcome/goTo.{id}")
	public String goToEvent(Model model,HttpSession session, @PathVariable int id){
		Event event = this.eventManager.getEventService().getEventById(id);
		model.addAttribute("event",event);
		session.setAttribute("eventNumber",event.getId());
		return "eventDetails";
	}
	
	@RequestMapping(value="/welcome/buyTicketTo.{chairNumber}")
	public String buyTicketToEvent(Model model,HttpSession session, @PathVariable int chairNumber){
		this.eventManager.buyTicketToEvent(session.getAttribute("eventNumber").toString(),chairNumber,session.getAttribute("userName").toString());		
		
		return "redirect:/welcome";
	}
}
