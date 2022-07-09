/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PATHUMI
 */
public class GradesModel {

    Connection conn;

    public GradesModel() {
        conn = db.dbConnection.getConnection();
    }



    public String updateSubject(String nic, String crs, String subname, String tmark, String pmark) {
        String msg = null;
        String query = "INSERT INTO `grades` ( nic,course, subject_name, theory_marks, practical_marks) VALUES (?, ?, ?, ?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            psm.setString(2, crs);
            psm.setString(3, subname);
            psm.setString(4, tmark);
            psm.setString(5, pmark);
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

    public void loadTable(int cat, String keyword, DefaultTableModel dtm) {
        String loadatanic = "SELECT * FROM grades WHERE nic LIKE ?";
        String loadatasub = "SELECT * FROM grades WHERE subject_name LIKE ?";
        String loadatacrs = "SELECT * FROM grades WHERE course LIKE ?";
        String runningQuery=null;
        
         if (cat == 0) {
            runningQuery = loadatacrs;
        } else if (cat == 1) {
            runningQuery = loadatanic;
        }else if(cat==2){
            runningQuery = loadatasub;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(runningQuery);
            ps.setString(1, "%"+keyword+"%");

            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                String nic = rs.getString("nic");
                String crs = rs.getString("course");
                String sname = rs.getString("subject_name");
                String tmark = rs.getString("theory_marks");
                String pmarks = rs.getString("practical_marks");

                dataRow = new Object[]{nic,crs, sname, tmark, pmarks};
                dtm.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadTable(String nic, DefaultTableModel dtm) {
      String loadatanic = "SELECT * FROM grades WHERE nic LIKE ?";
     
        
        
        try {
            PreparedStatement ps = conn.prepareStatement(loadatanic);
            ps.setString(1, nic);

            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                String id= rs.getString("nic");
                String crs = rs.getString("course");
                String sname = rs.getString("subject_name");
                String tmark = rs.getString("theory_marks");
                String pmarks = rs.getString("practical_marks");

                dataRow = new Object[]{id,crs, sname, tmark, pmarks};
                dtm.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }

}
