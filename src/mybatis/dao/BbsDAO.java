package mybatis.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.BbsVO;

public class BbsDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	
	
	//-------------------------- 비지니스 로직들 --------------------------
	
	//전체게시물의 수를 반환하는 기능
	public int getTotalCount(String bname){
		return template.selectOne("bbs.total",bname);
	}
		
	//리스트 화면을 위한 목록 기능 (ListAction에서 호출함)
	public BbsVO[] getList(Map<String, String> map){
		List<BbsVO> list = template.selectList("bbs.list", map);	
		BbsVO[] ar = null;//반환값
		if(list !=null && list.size()>0){
			ar = new BbsVO[list.size()];
			
			//리스트의 항목들을 ar에 복사함.
			list.toArray(ar);
		}	
		return ar;
	}
		
	//원글 저장 기능
	public boolean writeBbs(BbsVO vo){
		int cnt = template.insert("bbs.write", vo);	
		
		if(cnt>0) return true;
		else return false;
		
	}	
	
	//기본키로 게시물 검색 기능
	public BbsVO getBbs(String seq) {				
		return template.selectOne("bbs.getBbs",seq);
	}
	
	//게시물 수정 기능
	public void updateBbs(BbsVO vo) {
	//	System.out.println("Updatedao"+vo.getUploadFileName());
		template.update("bbs.updateBbs",vo);
	}
	
	//답변 저장 전에 lev값 변경 기능
	public void updateLev(Map<String, String> map) {
		template.update("bbs.updateLev",map);
	}
	
	//답변 저장 기능
	public void addAns(BbsVO vo) {
		template.insert("bbs.addAns",vo);
		
	}
	
	
	
	
	//게시물 삭제 기능
}
