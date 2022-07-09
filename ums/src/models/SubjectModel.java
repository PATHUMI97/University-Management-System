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
public class SubjectModel {

    Connection conn;

    public SubjectModel() {
        conn = db.dbConnection.getConnection();
    }

    public String insertSubject(String subid, String subname, String sem, String course) {
        String msg = null;
        String query = "INSERT INTO `subjects` ( sub_no, name, sem, course) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, subid);
            psm.setString(2, subname);
            psm.setString(3, sem);
            psm.setString(4, course);
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

    public void loadTable(String keyword, DefaultTableModel dtm) {
        String loaddata = "SELECT * FROM subjects WHERE name LIKE ?";
        try {
            PreparedStatement psm = conn.prepareStatement(loaddata);
            psm.setString(1, "%" + keyword + "%");
            ResultSet rs = psm.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
                while (rs.next()) {
             
                String sno = rs.getString("sub_no");
                String name = rs.getString("name");
                String sem = rs.getString("sem");
                String crs = rs.getString("course");
                dataRow = new Object[]{ sno, name,sem,crs};
                dtm.addRow(dataRow);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[] getSelectedData(String sid) {
      String query ="SELECT * FROM subjects WHERE sub_no =?";
      
              try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, sid);
            ResultSet rs = psm.executeQuery();
            Object[] dataRow =null;
            while (rs.next()) {   
                String id =rs.getString("sub_no");
                String name =rs.getString("name");
                String sem =rs.getString("sem");
                String course =rs.getString("course");
                
                 dataRow = new Object[]{id,name, name, sem, course};
            }
            return dataRow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateSubject(String subid, String subname, String sem, String course) {
            String msg = null;
        String query = "UPDATE `subjects` SET  name=?, sem=?, course=? WHERE  sub_no=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            
            psm.setString(1, subname);
            psm.setString(2, sem);
            psm.setString(3, course);
            psm.setString(4, subid);
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

    public String deleteSubject(String subid) {
           String msg = null;
        String query = "DELETE FROM subjects WHERE sub_no =?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            
            psm.setString(1, subid);
            
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

}
