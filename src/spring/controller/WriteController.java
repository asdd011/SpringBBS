package spring.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.FileUploadUtil;

@Controller
public class WriteController {

	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext servletContext;
	
	//자동으로 채워지지 않고 Controller-servelt.xml에서
	//현재 객체를 선언할 때 <property .../>로 값을 저장할 변수를 선언하자
	
	private String uploadPath;

	
	@RequestMapping("/writeForm")
	public String writeForm() {//단지 경로이동만 쓸때
		
	//	ModelAndView modelAndView = new ModelAndView();
	//	modelAndView.setViewName("write");
		
		
		return "write";
	}
	
	@RequestMapping(value="/write", method =RequestMethod.POST)
	public ModelAndView write(BbsVO vo) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println("글쓴이 :" +vo.getWriter());
		
		//파일첨부를 확인하는 조건문 
		if(vo.getUpload().getSize()>0){
			//파일이 첨부 됬으니까 파일을 원하는 곳에 저장하자.
			String path = servletContext.getRealPath(uploadPath);//WebContent의 upload폴더
			
			//인자로 전달된 vo에 있는 첨부파일 가져오기
			MultipartFile mf = vo.getUpload();
			
			//첨부파일의 파일명을 가져온다.
			String f_name = mf.getOriginalFilename();
		
			//파일을 저장하기 전에 동일 한 파일명이 있을 수 있으므로 
			//동일한 파일명을 다른 이름으로 변경하자
			f_name = 
				FileUploadUtil.checkSameFileName(f_name, path);
		
			//파일저장하자
			mf.transferTo(new File(path,f_name));
			
			//DB에 저장할 정보를 가지고 있는 vo에도
			//파일명을 저장하자!
			System.out.println("파일이름"+f_name);
			vo.setUploadFileName(f_name);
		}else{
			vo.setUploadFileName("");
		}
		
		vo.setIp(request.getRemoteAddr());
		
		//mybatis를이용하여 vo를 전달하면서 값을 저장
		b_dao.writeBbs(vo);
		
		modelAndView.setViewName("redirect:/list.ajk");
		
		return modelAndView;
	}
	
	
	
	
	
	
	   
	
	
	
	
	
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	
	
	
}
