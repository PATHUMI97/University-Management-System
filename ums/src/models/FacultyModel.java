
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;




public class FacultyModel {
    Connection conn;
    
    public FacultyModel(){
    conn = db.dbConnection.getConnection();
}

    public String createFaculty(String id,String faculty) {
     String msg = null;
     String query = "INSERT INTO faculty (id,description) VALUES (?,?)";
        try {
            PreparedStatement psm =conn.prepareStatement(query);
            psm.setString(1, id);
            psm.setString(2, faculty);
            psm.execute();
            
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg ="Error"+e.getMessage();
        }
        return msg;
    }
    
}
