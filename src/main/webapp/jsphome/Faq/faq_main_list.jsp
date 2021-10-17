<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.*"%>
<%@page import="kr.co.megait.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	FaqDAO faqDAO = new FaqDAO();
	JSONArray faq_list = new JSONArray();
	JSONObject faq_info = new JSONObject();
	
	faq_list = faqDAO.FaqList2(5);
	
	int faq_idx;
	String faq_title = null;
	String faq_contents = null;
	String reg_dt = null;
	String mod_dt = null;

	int total_count = faqDAO.FaqTotal();
	
%>	

<div class="row">
	<div class="col-lg-6">
		<h3>자주하는질문</h3>
	</div>
	<div class="col-lg-6 text-right">
		<a href="/faq_default.do">더보기</a>
	</div>
</div>

<div style="text-align:left;margin:10px;">
	전체 게시글 : <%=total_count %> 개
</div>

<table class="table">
	<tr>
		<td class="td1">No.</td>
		<td class="td1">제목</td>
		<td class="td1">등록일</td>
		<td class="td1">수정일</td>
	</tr>
	
	
	<%if(faq_list.size()>0){ %>
		
		<%for(int i=0;i<faq_list.size();i++){ %>
		<%
			faq_info = (JSONObject)faq_list.get(i);
			faq_idx = (Integer)faq_info.get("faq_idx");
			faq_title = (String)faq_info.get("faq_title");
			faq_contents = (String)faq_info.get("faq_contents");
			reg_dt = (String)faq_info.get("reg_dt");
			mod_dt = (String)faq_info.get("mod_dt");
		%>

			<tr>
				<td><%=faq_list.size()-i%></td>
				<td>
					<a href="/faq_view.do?faq_idx=<%=faq_idx%>"><%=faq_title %></a>
				</td>
				<td><%=reg_dt.substring(0, 10) %></td>
				<td><%=mod_dt.substring(0,10) %></td>
			</tr>
		
		<%} %>
		
		
	<%}else{ %>

		<tr>
			<td colspan="4" style="height:150px;text-align:center;vertical-align:middle;">
				현재 등록된 자주하는질문이 없습니다.
			</td>
		</tr>
	<%} %>

</table>

<script>


</script>
