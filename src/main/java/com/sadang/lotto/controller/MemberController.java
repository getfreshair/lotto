package com.sadang.lotto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sadang.lotto.exception.ResourceNotFoundException;

/**
 * 
 * @author yang/2020.01.29
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	@RequestMapping("/login")
    public ModelAndView memberLogin(ModelAndView modelAndView) throws ResourceNotFoundException{
		modelAndView.setViewName("main");
        return modelAndView;
    }
 
    @RequestMapping("/logout")
    public ModelAndView memberLogout(ModelAndView modelAndView) throws ResourceNotFoundException{
    	modelAndView.setViewName("main");
        return modelAndView;
    }
}
