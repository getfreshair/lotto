package com.sadang.lotto.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sadang.lotto.exception.ResourceNotFoundException;
import com.sadang.lotto.model.Member;
import com.sadang.lotto.repository.MemberRepository;

/**
 * 
 * @author yang/2020.01.29
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired(required=true)
    private MemberRepository memberRepository;
	
	@PostMapping(value="/signin")
	public ModelAndView signin(ModelAndView response) throws ResourceNotFoundException {
		return response;
	}
	
	@PostMapping("/memberList")
    public ModelAndView memberList(ModelAndView response) throws ResourceNotFoundException {
		List<Member> memberList = memberRepository.findAll();
		logger.info("response : " + response.toString());
        
		response.addObject("memberList", memberList);
		response.setViewName("main");
        return response;
    }
	
	@GetMapping("memberCreate")
	public ModelAndView createMember(@Valid @RequestBody Member member, ModelAndView response)  {
		logger.info("response : " + member.getEmail());
        memberRepository.save(member);
        
		return response;
	}

}
