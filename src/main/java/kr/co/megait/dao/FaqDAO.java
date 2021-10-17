package kr.co.megait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.megait.common.ConnectionDB;

public class FaqDAO {
   
   /**
    * 자주하는질문 전체 가져오기
    * @return
    * @throws SQLException
    */
   public JSONArray FaqList1(int current_page) throws SQLException{
      
      //변수 선언
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      
      JSONArray faq_list = new JSONArray();
      JSONObject faq_info = new JSONObject();
      
      int iEndPage = 10;
      int iStartPage = (current_page*iEndPage)-10;
      
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "select *, Date(reg_dt) as rdt, Date(mod_dt) as mdt from faqboard "
               + "order by reg_dt desc limit "+iStartPage+", "+iEndPage+" ";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            //게시글 정보
        	faq_info = new JSONObject();
        	faq_info.put("faq_idx", new Integer(rs.getInt("faq_idx")));
        	faq_info.put("faq_title", new String(rs.getString("faq_title")));
        	faq_info.put("faq_contents", new String(rs.getString("faq_contents")));
        	faq_info.put("reg_dt", new String(rs.getString("reg_dt")));
        	faq_info.put("mod_dt", new String(rs.getString("mod_dt")));
            faq_list.add(faq_info);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return faq_list;
   }
   

   /**
    * 자주하는질문 메인 페이지 
    * @param counter
    * @return
    * @throws SQLException
    */
   public JSONArray FaqList2(int counter) throws SQLException{
      
      //변수 선언
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      
      JSONArray faq_list = new JSONArray();
      JSONObject faq_info = new JSONObject();
      
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "select * from faqboard "
               + "order by reg_dt desc limit "+counter+" ";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            //게시글 정보
            faq_info.put("faq_idx", new Integer(rs.getInt("faq_idx")));
            faq_info.put("faq_title", new String(rs.getString("faq_title")));
            faq_info.put("faq_contents", new String(rs.getString("faq_contents")));
            faq_info.put("reg_dt", new String(rs.getString("reg_dt")));
            faq_info.put("mod_dt", new String(rs.getString("mod_dt")));
            faq_list.add(faq_info);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return faq_list;
   }

   
   /**
    * 자주하는질문 정보보기
    * @param notice_idx
    * @return
    * @throws SQLException
    */
   public JSONObject FaqInfo(int faq_idx) throws SQLException{
      
      //변수 선언
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      
      JSONObject faq_info = new JSONObject();
      
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "select * from faqboard where faq_idx = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, faq_idx);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            //게시글 정보
            faq_info.put("faq_idx", new Integer(rs.getInt("faq_idx")));
            faq_info.put("faq_title", new String(rs.getString("faq_title")));
            faq_info.put("faq_contents", new String(rs.getString("faq_contents")));
            faq_info.put("reg_dt", new String(rs.getString("reg_dt")));
            faq_info.put("mod_dt", new String(rs.getString("mod_dt")));
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return faq_info;
   }

   
   /**
    * 자주하는질문 전체 개수
    * @return
    * @throws SQLException
    */
   public int FaqTotal() throws SQLException{
      
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;

      int total_count = 0;
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "select count(*) from faqboard";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            total_count = rs.getInt(1);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return total_count;
   }

   
   /**
    * 자주하는질문 등록하기
    * @param params
    * @throws SQLException
    */
   public void FaqInsert(String faq_title, String faq_contents) throws SQLException{
      
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      String sql = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "insert into faqboard ("
               + "faq_title, "
               + "faq_contents, "
               + "reg_dt, "
               + "mod_dt) "
               + "values (?, ?,now(), now())";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, faq_title);
         pstmt.setString(2, faq_contents);
         pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
   /**
    * 자주하는질문 수정하기
    * @param params
    * @throws SQLException
    */
   public void FaqModify(int faq_idx, String faq_title, String faq_contents) throws SQLException{
      
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      String sql = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "update faqboard set "
               + "faq_title = ?, "
               + "faq_contents = ?, "
               + "mod_dt = now() "
               + "where faq_idx=?";

         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, faq_title);
         pstmt.setString(2, faq_contents);
         pstmt.setInt(3, faq_idx);
         pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
   /**
    * 자주하는질문 삭제
    * @param notice_idx
    * @throws SQLException
    */
   public void FaqDelete(int faq_idx) throws SQLException{
      
      Connection conn = null;
      ConnectionDB connectionDB = new ConnectionDB();
      PreparedStatement pstmt = null;
      String sql = null;
      
      
      try {
         conn = connectionDB.YesConnectionDB();
         sql = "delete from faqboard where faq_idx = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, faq_idx);
         pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
}