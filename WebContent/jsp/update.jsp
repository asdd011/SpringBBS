<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(ff){
		//유효성 검사
		
		ff.submit();
	}
</script>
</head>
<body>

	<form action="update.ajk" method="post" 
	enctype="multipart/form-data">
	<input type="hidden" name="bname" value="BBS">

	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center" height="10"></td>
        </tr>
        <tr>
          <td align="center"><u><b>BBS 수 정 하 기</b></u></td>
        </tr>
        <tr>
          <td align="center" valign="top">
               
    	<input type="hidden" name="seq" value="${vo.seq}">
	<input type="hidden" name="nowPage" value="${vo.nowPage}">
	<input type="hidden" name="groups" value="${vo.groups}">
    <input type="hidden" name="step" value="${vo.step}">
	<input type="hidden" name="lev" value="${vo.lev}" >
    
    
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center" height="10"></td>
        </tr>

        <tr>
          <td align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table>
            <table width="556" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="2" bgcolor="#C3C3C3"></td>
              </tr>
              <tr>
                <td bgcolor="#E5E5E5"><table width="100%" border="0" cellspacing="1" cellpadding="2">

                    <tr>
                      <td width="90" height="20" align="center" bgcolor="#669AB3"><font color="#FFFFFF">작성자</font></td>
                      <td bgcolor="#F2F7F9" align="left"> <input type="text" cssStyle="width:100px" theme="simple" disabled="disabled"
                      		value="${vo.writer}"/>
                      		<input type="hidden" name="writer" value="${vo.writer}"/>
                      		</td>
                    </tr>

                    <tr>
                      <td height="20" align="center" bgcolor="#669AB3"><font color="#FFFFFF">제목</font></td>
                      <td bgcolor="#F2F7F9" align="left"> <input type="text" name="title" size="50" theme="simple"
                      		value="${vo.title }"/></td>
                    </tr>
                    <tr>
                      <td height="20" align="center" bgcolor="#669AB3"><font color="#FFFFFF">내용</font></td>
                      <td bgcolor="#F2F7F9" align="left"> <textarea name="content" cols="50" rows="10" theme="simple">${vo.content }</textarea></td>
                    </tr>
                    <tr>
                      <td height="20" align="center" bgcolor="#669AB3"><font color="#FFFFFF">첨부파일</font></td>
                      <td bgcolor="#F2F7F9" align="left">
                        <input type="file" name="upload" cssStyle="width:300px" theme="simple"
                        	/>&nbsp;&nbsp; ${vo.uploadFileName }
                        <input type="hidden" name="uploadFileName" value="${vo.uploadFileName }">	
                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="center" bgcolor="#669AB3"><font color="#FFFFFF">비밀번호</font></td>
                      <td bgcolor="#F2F7F9" align="left"> <input type="password" name="pwd" cssStyle="width:200px" theme="simple"/>
                        <font color="#0066CC">* 삭제.수정시 필요</font> </td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <table width="556" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="20" valign="middle"><img src="/images/sub_it/point_line.gif" width="556" height="3"></td>
              </tr>
              <tr>
                <td align="right"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="315" align="center">
                        
                      </td>
                      <td width="241" align="right">
                      <input type="button" onclick="check(this.form)" value="보내기"/>
                      <input type="reset" value="재입력"/>
                      </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="19"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
          </td>
        </tr>
        <tr>
          <td height="19"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
	</form>
</body>
</html>












