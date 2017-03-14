package spring.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.regexp.internal.recompile;

import mybatis.dao.BbsDAO;

@Controller
public class ViewControl {

	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/view")
	public ModelAndView viewBbs(String seq,String nowPage ) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
//		String seq = request.getParameter("seq");
//		String nowPage = request.getParameter("nowPage");
		
		modelAndView.addObject("vo", b_dao.getBbs(seq));
		//modelAndView.addObject("nowPage",nowPage);		
		
		
		
		modelAndView.setViewName("view");
		return modelAndView;
		
	}
	
	
}
