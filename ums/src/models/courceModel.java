
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class courceModel {
    Connection conn;
    
     public courceModel(){
        conn =db.dbConnection.getConnection();
    }

    public String insertCource(String code, String name, String sy, String total) {
        String msg = null;
        String query = "INSERT INTO `university_management_system`.`course` ( code, name, sem_year, total_sem_year) VALUES (?, ?, ?, ?)";
        
           try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, code);
            psm.setString(2, name);
            psm.setString(3, sy);
            psm.setString(4, total);
            psm.execute();
            
            msg = "Success";
            
        } catch (Exception e) {
            e.printStackTrace();
             msg = "Error" + e.getMessage();
        }
  
        return msg; 
    }

    public void loadTable(String keyword, DefaultTableModel dtm) {
     String loadData = "SELECT * FROM course WHERE id_course LIKE ?";
       try {
            PreparedStatement ps = conn.prepareStatement(loadData);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                int id =rs.getInt("id_course");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String sy = rs.getString("sem_year");
                String tsm = rs.getString("total_sem_year");
                dataRow = new Object[]{id, code, name,sy,tsm};
                dtm.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
    }
    }

    public String createRollNo(String code, String sem, String rollno) {
       String msg = null;
       String query="INSERT INTO `cource_enrollment` (idcource_enrollment, code, semster) VALUES (?,?,?)";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, rollno);
            psm.setString(2, code);
            psm.setString(3, sem);
            
            psm.execute();
             msg ="Success";
        } catch (Exception e) {
             e.printStackTrace();
              msg ="Error"+e.getMessage();
        }
        return msg;
    }

    public Object[] getSelectedData(int cid) {
      String query ="SELECT * FROM course WHERE id_course =?";
      
              try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, cid);
            ResultSet rs = psm.executeQuery();
            Object[] dataRow =null;
            while (rs.next()) {   
                int id =rs.getInt("id_course");
                String code =rs.getString("code");
                String name =rs.getString("name");
                String sy =rs.getString("sem_year");
                String total =rs.getString("total_sem_year");
                
                 dataRow = new Object[]{id,code, name, sy, total};
            }
            return dataRow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateCource(String id,String code, String name, String sy, String total) {
         String msg = null;
        String query = "UPDATE `university_management_system`.`course` SET code=?, name=?, sem_year=?, total_sem_year=? WHERE id_course=? ";
        
           try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, code);
            psm.setString(2, name);
            psm.setString(3, sy);
            psm.setString(4, total);
            psm.setString(5, id);
            psm.execute();
            
            msg = "Success";
            
        } catch (Exception e) {
            e.printStackTrace();
             msg = "Error" + e.getMessage();
        }
  
        return msg; 
    }
    

}
