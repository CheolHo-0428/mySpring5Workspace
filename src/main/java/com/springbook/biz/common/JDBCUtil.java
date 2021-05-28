package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
   public static Connection getConnection() {
		String url = "jdbc:mysql://database-1.c5n30oqbrfya.ap-northeast-2.rds.amazonaws.com:3306/springbook";
		String id = "user";
		String pw = "playdata1*";
		String driver = "com.mysql.cj.jdbc.Driver";
      try {
         Class.forName(driver);
         return DriverManager.getConnection(url, id, pw);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   
   public static void close(PreparedStatement stmt, Connection conn) {
      if(stmt != null) {
         try {
            if(!stmt.isClosed()) {
               stmt.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            stmt = null;
         }
      }
      
      if(conn != null) {
         try {
            if(!conn.isClosed()) {
               conn.close();
            } 
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            conn = null;
         }
      }
   }
   
   public static void close(ResultSet rs,PreparedStatement stmt, Connection conn) {
      if(rs != null) {
         try {
            if(!rs.isClosed()) {
               rs.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            rs = null;
         }
      }
      
      
      if(stmt != null) {
         try {
            if(!stmt.isClosed()) {
               stmt.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            stmt = null;
         }
      }
      
      if(conn != null) {
         try {
            if(!conn.isClosed()) {
               conn.close();
            } 
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            conn = null;
         }
      }
   }
}