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
public class timetableModel {
    Connection conn;
    
    public timetableModel(){
        conn=db.dbConnection.getConnection();
    }

    public String loadTable(String crs, DefaultTableModel dtm1) {
      
        String loadatasub = "SELECT * FROM `class_schedule` WHERE course LIKE ?";
  
        try {
            PreparedStatement ps = conn.prepareStatement(loadatasub);
            ps.setString(1, crs);
//idclass, course, subject, instructor, date
            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm1.setRowCount(0);
            while (rs.next()) {
                String id = rs.getString("idclass");
                String crz = rs.getString("course");
                String subject = rs.getString("subject");
                String lec = rs.getString("instructor");
                Date date = rs.getDate("date");

                dataRow = new Object[]{id,crz, subject, lec, date};
                dtm1.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
