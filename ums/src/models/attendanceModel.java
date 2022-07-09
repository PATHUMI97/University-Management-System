
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PATHUMI
 */
public class attendanceModel {
    Connection conn;
    
    public attendanceModel(){
        conn=db.dbConnection.getConnection();
    }

    public String scheduleClass(String nic, String clz, String attendance) {
          String msg = null;
        String query = "INSERT INTO `attendance` ( student, class, attendance) VALUES ( ?, ?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            psm.setString(2, clz);
            psm.setString(3, attendance);
           
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

    public void loadTable(String keyword, DefaultTableModel dtm) {
        String loadatanic = "SELECT * FROM `class_schedule` INNER JOIN attendance WHERE attendance.student LIKE ?";
        
        
    
        try {
            PreparedStatement ps = conn.prepareStatement(loadatanic);
            ps.setString(1, "%"+keyword+"%");

            ResultSet rs = ps.executeQuery();
            Object[] dataRow;
            dtm.setRowCount(0);
            while (rs.next()) {
                
                String crs = rs.getString("course");
                String sb = rs.getString("subject");
                String inst = rs.getString("instructor");
                String dt = rs.getString("date");
String st= rs.getString("student");
String at = rs.getString("attendance");
                dataRow = new Object[]{crs,sb, inst, dt, st,at};
                dtm.addRow(dataRow);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
