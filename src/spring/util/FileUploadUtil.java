package spring.util;

import java.io.File;

public class FileUploadUtil {
	
	public static String checkSameFileName(String filename, String path) {
		//같은파일의 이름이 있는가를 체크하는거 
		System.out.println("유틸쪽 : "+filename);
		//파일이름을 가려내자
		int period = filename.lastIndexOf(".");
		
		//ex) test.txt
		String f_name = filename.substring(0,period); // = test
		String suffix = filename.substring(period); // = .txt
		
		System.out.println("유틸쪽 f_name : "+f_name); 
		System.out.println("유틸쪽 suffix : "+suffix);
		
		System.out.println("유틸쪽 path : "+path);
		
		
		//전체경로를 받읍시다.
		String saveFileName = path + 
				System.getProperty("file.separator") + //운영체제가 무엇이든 자바가 알아서 파일구분자를 넣어준다 Ex) / \ 
				filename;
	
		
		//파일이 존재하는지를 알기 위해서는 무조건 
		//java.io.File객체를 만들어서 exists()로 확인 해야해 
		File f = new File(saveFileName);

		//같은 이름이 있다면 번호를 붙여야해
		//번호를 가지는 변수를 선언하자
		int idx=1;
		
		while(f!=null && f.exists()){
			//동일한 이름의 파일이 있는 경우
			
			//파일명을 변경해야해
			StringBuffer sb = new StringBuffer();
			sb.append(f_name);
			sb.append("("+(idx++)+")");
			sb.append(suffix);
			
			filename = sb.toString();//파일이름이 바뀜
			
			//파일이름을 바꿧는데 바꾼놈이 또있다면
			saveFileName = path + System.getProperty("file.separator") + filename;
			
			
			f = new File(saveFileName);
				//파일의 경로를 다시해서 또 똑같은놈이있으면 와일조건문으로 다시감
		}//while end
	
		
		
		return f.getName();//바뀐이름을 리턴함
	}
	
	
	
}
