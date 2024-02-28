package com.gg.gop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {
	@GetMapping("/match")
	public String go() {
		return "match/match";
	}
}
