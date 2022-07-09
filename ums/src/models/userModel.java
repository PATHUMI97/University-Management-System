
package models;

import gui.StudentProfile;
import gui.lecturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class userModel {
    private StudentModel stm;
    Connection conn;
    
    public userModel(){
        conn=db.dbConnection.getConnection();
    }
    
    public String createUser(String nic, String password, String type) {
            String msg = null;
        String query = "INSERT INTO `users` ( nic, password, type) VALUES (?, ?, ?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, nic);
            psm.setString(2, password);
            psm.setString(3, type);
            
            psm.execute();

            msg = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error" + e.getMessage();
        }

        return msg;
    }

    public String login(String username, String password, int type) {
        String msg =null;
     String runningQuery = null;
        String lectureQuery ="SELECT * FROM `lecturer` WHERE NIC=? AND password=?  ";
         String studentQuery ="SELECT * FROM student WHERE NIC=? AND password=?  ";
        //  String adminQuery ="SELECT * FROM `lecturer` WHERE NIC=? AND password=?  ";
        if(type==1){
        runningQuery =lectureQuery;
        }else if (type ==2) {
            runningQuery=studentQuery;
        }
        try {
            PreparedStatement psm = conn.prepareStatement(runningQuery);
            psm.setString(1, username);
            psm.setString(2, password);
    
            ResultSet rs =psm.executeQuery();
            
            while (rs.next()) {    
                if (runningQuery ==lectureQuery) {
               
                 msg ="welcome lecturer";
                 //   new lecturer().setVisible(true);
                }else if (runningQuery==studentQuery) {
                    msg ="welcome student";
                
                 //   new StudentProfile().setVisible(true);
                    
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    
}
