package spring.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
public class AnswerControl {

	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext servletContext;
	
	private String uploadPath;

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	
	//view에서 답변버튼을 클릭했을 때 수행
	@RequestMapping(value="/answer",method=RequestMethod.GET)
	public ModelAndView answer(BbsVO vo) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("vo",vo);
		modelAndView.setViewName("/answer");
		
		
		return modelAndView;
	}
	
	//view에서 답변버튼을 클릭했을 때 수행
		@RequestMapping(value="/answer",method=RequestMethod.POST)
		public ModelAndView addAns(BbsVO vo) throws Exception{
			ModelAndView modelAndView = new ModelAndView();
			
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
			
			
			///답변을 저장하기전에 참조글의 groups와 같고, 
			//참조글의 lev보다 큰 값을 가진 게시물들의 lev를 1씩 증가
			Map<String, String> map = new HashMap<>();
			map.put("groups", vo.getGroups());
			map.put("lev", vo.getLev());
			
			b_dao.updateLev(map);//lev조정함
			
			//이제 답변을 저장해야해
			//답변의 step과 lev는 참조글의 step+1 lev+1
			int step = Integer.parseInt(vo.getStep())+1;
			int lev = Integer.parseInt(vo.getLev())+1;
			
			//위에서 1씩 증가한 값을 다시 vo에 저장한다.
			vo.setStep(String.valueOf(step));
			vo.setLev(String.valueOf(lev));
			
			b_dao.addAns(vo);//답변저장!!
			
			
			modelAndView.setViewName
					("redirect:/list.ajk?nowPage"+vo.getNowPage());
			
			return modelAndView;
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
