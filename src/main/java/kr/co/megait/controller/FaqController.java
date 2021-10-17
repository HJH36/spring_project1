package kr.co.megait.controller;

import java.io.PrintWriter;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.megait.common.CommonUtil;
import kr.co.megait.dao.LoginDAO;
import kr.co.megait.dao.MemberDAO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class FaqController {
	
	private static final Logger logger = LoggerFactory.getLogger(FaqController.class);

	/**
	 * 자주하는질문 리스트 페이지
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/faq_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView FaqDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Faq/faq_default");
		return mv;
		
	}
	
	/**
	 * 자주하는질문 정보 보기
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/faq_view", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView FaqViewDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Faq/faq_view");
		return mv;
		
	}
	
	
}
