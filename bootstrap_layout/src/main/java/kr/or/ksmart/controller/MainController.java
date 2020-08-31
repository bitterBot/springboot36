package kr.or.ksmart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("title", "index 화면");
		return "index";
	}
	
}
