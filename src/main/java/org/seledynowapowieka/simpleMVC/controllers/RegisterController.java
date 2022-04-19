package org.seledynowapowieka.simpleMVC.controllers;

import javax.validation.Valid;

import org.seledynowapowieka.simpleMVC.entities.ModelUser;
import org.seledynowapowieka.simpleMVC.entities.User;
import org.seledynowapowieka.simpleMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	
	ModelUser userModel;
	
	static RegisterController registerController;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@RequestMapping("/register")
	public String showRegisterForm(Model theModel) {
		ModelUser modelUser = new ModelUser();
		theModel.addAttribute("modelUser", modelUser);
		return "registrationForm";
	}
	
	@PostMapping("/processRegistration")
	public String processRegistration(@Valid @ModelAttribute("modelUser") ModelUser modelUser, 
			BindingResult br, Model theModel) {
		
		setInstance(this);
		setUserModel(modelUser);
		
		
		if(br.hasErrors()) {
			return "registrationForm";
		}
		
		
		User existingUser = userService.findByUserName(modelUser.getUserName());
		if(existingUser != null) {
			
			theModel.addAttribute("modelUser", modelUser);
			theModel.addAttribute("error", "user already exist in DB!");
			return "registrationForm";
		}
		
		//create method for checking email address: userService.findByUserEmail("")
		
		
		userService.save(modelUser);
		
		theModel.addAttribute("modelUser", modelUser);
		return "registrationSuccess";
	}


	public ModelUser getUserModel() {
		return userModel;
	}

	public void setUserModel(ModelUser userModel) {
		this.userModel = userModel;
	}
	
	public static RegisterController getInstance() {
		if(registerController == null) {
			registerController = new RegisterController();
			System.out.println("creating new RegisterController instance");
		}
		
		return registerController;
	}
	
	public void setInstance(RegisterController regController) {
		registerController = regController;
	}
	
}
