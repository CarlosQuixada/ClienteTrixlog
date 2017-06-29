package br.trixlog.carlos.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	@RequestMapping(value="/")
	public String home(){
		return "index";
	}
}
