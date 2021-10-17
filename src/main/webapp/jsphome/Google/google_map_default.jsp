<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.megait.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	VisitDAO visitDAO = new VisitDAO();
	visitDAO.VisitReg(request);	


	int current_page = 1;
	if(request.getParameter("current_page")!=null){
		current_page = Integer.parseInt(request.getParameter("current_page"));
	}
	
	String latitude_temp = "";
	String longitude_temp = "";
	if(request.getParameter("latitude")!=null){
		latitude_temp = request.getParameter("latitude");
		longitude_temp = request.getParameter("longitude");
	}
	
	System.out.println(latitude_temp);
	System.out.println(longitude_temp);
	
	LibraryDAO libraryDAO = new LibraryDAO();
	JSONArray library_list = new JSONArray();
	JSONObject library_info = new JSONObject();
	
	library_list = libraryDAO.LibraryList(0);
	int total_count = libraryDAO.LibraryTotal();

	int library_idx;
	String latitude = "";
	String longitude = "";
	String name = null;
	String phone = null;
	String address = null;
	String url = null;
	

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>

	<script src="https://maps.google.com/maps/api/js?key=AIzaSyC57xgJ446JM9AJpMRLkndrBdv3oyOGjvs" sensor="true"></script>
	
</head>
<body>

	<jsp:include page="/jsphome/top.jsp" flush="true"/>	
	
	<div class="container">
		
		<span style="font-size:20px;font-weight:700;">Google 도서관 주소 분포</span>
		<div class="text-right">
			전체 도서관 수 : <%=total_count %> 개
		</div>
			
		<!-- 지도를 표시할 div 입니다 -->
		<div id="map" style="width:100%;height:700px;margin-top:10px;"></div>
		
		<script>
         
         $(document).ready(function(){
            
            //초기 위치
            var initCenter = new google.maps.LatLng(37.499, 127.028);
            var myLat = 37.499;
            var myLng = 127.028;
            var zoomLevel = 5;
            var markerTitle = "메가스터디IT";
            var markerMaxWidth = 300;
            var markerContents = "내용";
            
            var myLatLng = new google.maps.LatLng(myLat, myLng);
            var mapOptions = {
                  zoom: zoomLevel,
                  center: myLatLng,
                  mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            
            var map = new google.maps.Map(document.getElementById('map'), mapOptions);
            
            var marker = new google.maps.Marker({
               position: myLatLng,
               map: map,
               title: markerTitle
            });
            
            var infowindow = new google.maps.InfoWindow({
               content: markerContents,
               maxWidth: markerMaxWidth
            });
            
            google.maps.event.addListener(marker, 'click', function(){
               infowindow.open(map, marker);
            });
            
         });
         
      </script>
		
       
		
	</div>
	
	<jsp:include page="/jsphome/footer.jsp" flush="true" />	
	
	
	<!-- Jquery CDN -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>        

</body>
</html>

<script>
	
	//공공 도서관 상세 보기
	function send_view(library_idx){
		location.href="/library_view_default.do?library_idx="+library_idx+"";
	}

</script>