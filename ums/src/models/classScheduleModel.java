/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PATHUMI
 */
public class classScheduleModel {
    Connection conn;
    
    public classScheduleModel(){
        conn =db.dbConnection.getConnection();
    }
    

    public String scheduleClass(String crs, String subname, String instructor, Date date) {
       String msg = null;
        String query = "INSERT INTO class_schedule (course, subject, instructor, date) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            
            psm.setString(1, crs);
            psm.setString(2, subname);
            psm.setString(3, instructor);
            psm.setDate(4, new java.sql.Date(date.getTime()));
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

    public void loadTable(int cat, String keyword, DefaultTableModel dtm) {
           String bycourse = "SELECT * FROM `class_schedule` WHERE course LIKE ?";
        String loadatasub = "SELECT * FROM `class_schedule` WHERE subject LIKE ?";
        String instructor = "SELECT * FROM `class_schedule` WHERE instructor LIKE ?";
        String runningQuery=null;
        
         if (cat == 0) {
            runningQuery = bycourse;
        } else if (cat == 1) {
            runningQuery = loadatasub;
        }else if(cat==2){
            runningQuery = instructor;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%"+keyword+"%");
//idclass, course, subject, instructor, date
            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                String id = rs.getString("idclass");
                String crs = rs.getString("course");
                String subject = rs.getString("subject");
                String lec = rs.getString("instructor");
                Date date = rs.getDate("date");

                dataRow = new Object[]{id,crs, subject, lec, date};
                dtm.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
