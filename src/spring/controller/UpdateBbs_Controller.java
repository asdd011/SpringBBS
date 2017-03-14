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
public class UpdateBbs_Controller { 

	@Autowired
	private BbsDAO bbsDAO;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext servletContext;
	
	private String uploadPath="/upload";
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView bbsUpdate(String seq,String nowPage) {
		ModelAndView modelAndView = new ModelAndView();
		BbsVO vo = bbsDAO.getBbs(seq);		
		vo.setNowPage(Integer.parseInt(nowPage));
		
		modelAndView.addObject("vo",vo);
		modelAndView.addObject("seq", seq);

		modelAndView.setViewName("update");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView bbsUpdate(BbsVO vo, String nowPage) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		vo.setIp(request.getRemoteAddr()); 
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
					vo.setUploadFileName(f_name);
				}else{
					vo.setUploadFileName(vo.getUploadFileName());
				}
		
		
		bbsDAO.updateBbs(vo);
		
		modelAndView.addObject("vo",vo);
		modelAndView.addObject("seq", vo.getSeq());
		modelAndView.addObject("nowPage", nowPage);

		
		modelAndView.setViewName("view");
		return modelAndView;
	}
	
}
