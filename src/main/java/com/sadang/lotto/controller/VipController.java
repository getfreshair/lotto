package com.sadang.lotto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/viewVip")
public class VipController {
	
	@GetMapping("/vipMix")
    public ModelAndView root_test(ModelAndView mav) throws Exception{
		mav.addObject("response", "test");
		mav.setViewName("dynamic/vipMix");
        return mav;
    }
}
