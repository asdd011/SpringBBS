package spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.Paging;


public class List_Controller implements Controller {

	@Autowired
	private BbsDAO b_dao; 
	
	//페이징 기법을 위한 변수들
	public static final int BLOCK_LIST = 5;
	public static final int BLOCK_PAGE = 3;
	
	int nowPage; //현재페이지 값
	int rowTotal; // 총게시물의 수
	
	String pageCode;//페이징 처리된 HTML코드
	
	//검색기능에 필요한 변수들
	String searchType, searchValue;
	
	
	@Override
	public ModelAndView handleRequest(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//여기는 사용자가 브라우저 창에서
		//list.ajk로 요청을 했을 때 수행하는곳
		
		//현재페이지의 값을 얻어낸다.
		String c_page = request.getParameter("nowPage");
		if(c_page==null)nowPage=1;
		else nowPage = Integer.parseInt(c_page);
				
		//게시판을 구별하는 문자열(bname)
		String bname = "BBS";
		
		rowTotal =b_dao.getTotalCount(bname);
		//rowTotal을 받으면 페이지 객체를 생성할 수 있어요
		Paging page = new Paging(nowPage, rowTotal, BLOCK_LIST, BLOCK_PAGE);
		//위의 객체가 가지는 HTML을 가져와 pageCode에 집어 넣는다.
		StringBuffer sb_temp = page.getSb();
		pageCode = sb_temp.toString();//결국 요놈이 HTML코드
		
		//리스트를 가져오기 위한 값들을 구하자 
		int begin = page.getBegin();
		int end = page.getEnd();
		
		Map<String, String> map = new HashMap<>();
		map.put("bname", bname);
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		
		//목록가지고 오자아
		BbsVO[] ar = b_dao.getList(map);
		
		//jsp에서 표현하기위해 ModelAndView에 적재해서 보내줘어		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", ar);
		modelAndView.addObject("nowPage", nowPage);
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("rowTotal", rowTotal);
		modelAndView.addObject("blockList", BLOCK_LIST);
		
		modelAndView.setViewName("list");
		
		return modelAndView;
	}

}
