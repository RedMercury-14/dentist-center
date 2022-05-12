package by.mytest.main.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.mytest.main.dao.DAOException;
import by.mytest.main.model.Role;
import by.mytest.main.model.Schedule;
import by.mytest.main.model.User;
import by.mytest.main.service.ScheduleService;
import by.mytest.main.service.SecurityService;
import by.mytest.main.service.ServiceException;
import by.mytest.main.service.UserService;
import by.mytest.main.validator.DateValidation;
import by.mytest.main.validator.UserValidator;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private DateValidation dateValidation;

	@Autowired
	SecurityService securityService;

	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping("/home")
	public String homePage(Model model) {
		List<User> userList = userService.getUsers();
		model.addAttribute("userlist", userList);
		return "home";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String logination(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	
	@PostMapping("/registration")
	public String registrationPage(@ModelAttribute("user") User user, BindingResult bindingResult, Model model,
			String error) {
		model.addAttribute("errorMessage", userValidator.validate(user));
		if (userValidator.validate(user) != null) {
			return "registration";
		}
		userService.saveUser(user);
		return "redirect:/home";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/list")
	public String backToList(Model model) {
		List<User> userList = userService.getUsers();
		model.addAttribute("userlist", userList);
		return "list";
	}

	@RequestMapping("/home/who")
	public String whoTest(Model model, String message) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			model.addAttribute("message", username);
		} else {
			String username = principal.toString();
			model.addAttribute("message", username);
		}
		return "home";
	}
	
	@RequestMapping("/home/order")
	public String orderPage(Model model) throws ServiceException {
		List <Schedule> scheduleList = scheduleService.getFreeSchedule();
		model.addAttribute("schedules", scheduleList);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			Set<Role> roles = userService.getUser(((UserDetails) principal).getUsername()).getRoles();
			String role = roles.iterator().next().getAuthority();
			model.addAttribute("role", role);
		}
		return "order";
	}

	@RequestMapping(value = "/home/order/addschedule", method = RequestMethod.GET)
	public String addSchedule(Model model) throws ServiceException {
		Set<User> doctors = getOnlyDoctor();
		Schedule schedule = new Schedule();
		model.addAttribute("doctors", doctors);
		
		return "schedule-form";
	}

	
	@RequestMapping(value = "/home/order/addschedule", method = RequestMethod.POST)
	public String saveSchedule(Model model,@RequestParam(value = "isDoctor", required = false) String doctor, 
			@RequestParam(value = "dateStart", required = false) Date dateStart, 
			@RequestParam(value = "dateFinish", required = false) Date dateFinish, 
			@RequestParam(value = "isShift", required = false) int shiftStart, 
			String message) throws DAOException, ServiceException {			
		model.addAttribute("errorMessage", dateValidation.validate(dateStart.toLocalDate(), dateFinish.toLocalDate()));
		if (dateValidation.validate(dateStart.toLocalDate(), dateFinish.toLocalDate()) != null) {
			Set<User> doctors = getOnlyDoctor();
			model.addAttribute("doctors", doctors);
			return "schedule-form";
		}
		User user = userService.getUser(doctor);
		scheduleService.saveMonthSchedule(scheduleService.getMonthSchedule(dateStart.toLocalDate(), dateFinish.toLocalDate(), shiftStart, user));	
		
		
		return "redirect:/home/order/";

	}
	
	@RequestMapping(value = "/home/userpage/proof", method = RequestMethod.GET)
	public String userPageProof(Model model, @RequestParam(value = "scheduleId", required = false) int id,
			String message) throws DAOException, ServiceException {		
		Schedule schedule = scheduleService.getSchedule(id);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		schedule.setStatus(username);
		scheduleService.saveOrUpdateSchedule(schedule);
		return "redirect:/home/userpage";

	}
	
	@RequestMapping(value = "/home/userpage", method = RequestMethod.GET)
	public String userPage(Model model, String message) throws DAOException, ServiceException {	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUser(((UserDetails) principal).getUsername());
		List<Schedule> orders = scheduleService.getScheduleListWhereLogin(user);
		model.addAttribute("orders", orders);		
		return "userPage";

	}
	@RequestMapping(value = "/home/userpage/cancel", method = RequestMethod.GET)
	public String userDeleteOrder(Model model, String message, 
			@RequestParam(value = "scheduleId", required = false) int id) throws DAOException, ServiceException {	
		Schedule schedule = scheduleService.getSchedule(id);
		schedule.setStatus(null);
		scheduleService.saveOrUpdateSchedule(schedule);
		return "redirect:/home/userpage";

	}
	private final int idRoleDoctor = 3;
	private Set<User> getOnlyDoctor(){
		List<User> userList = userService.getUsers();
		Role targetRole = new Role();
		targetRole.setId(idRoleDoctor);
		targetRole.setRole("ROLE_DOCTOR");
		Set<User> doctors = new HashSet<User>();
		for (User user : userList) {
			Set<Role> roles = user.getRoles();
			if (!roles.add(targetRole)) {
				doctors.add(user);
			}
		}		
		return doctors;		
	}
}
