package bankapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController 
{
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login(@RequestParam(value="error", required=false) boolean error,ModelMap model) 
	{	
		if (error == true) {
			// Assign an error message
			model.put("error", "Invalid Captcha!!");
		} else {
			model.put("error", "");
		}
			model.addAttribute("SessionTimeOut", "");
	model.addAttribute("logout", "");
		return "login"; 
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) 
	{
 		model.addAttribute("error", "true");
 			model.addAttribute("SessionTimeOut", "");
		model.addAttribute("logout", "");
		return "login";
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) 
	{
		model.addAttribute("SessionTimeOut", "");
		
		model.addAttribute("error", "");
		model.addAttribute("logout", "true");
		return "login";
	} 
	@RequestMapping(value="/SessionTimeOut", method = RequestMethod.GET)
	public String SessionTimeOut(ModelMap model) 
	{
		model.addAttribute("SessionTimeOut", "true");
		model.addAttribute("logout", "");
		model.addAttribute("error", "");
		return "login";
	} 
}