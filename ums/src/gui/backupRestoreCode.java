
package gui;


public class backupRestoreCode {
    
    public String backupDB(String path){
    
        String msg=null;
        
        try {
            Runtime runtime=Runtime.getRuntime();
            Process p=runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe -P3307 -uroot -ppathu@1997.4 --add-drop-database -B university_management_system -r"+path );
            int processComplete=p.waitFor();
            System.out.println("process done"+processComplete);
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="Error"+e.getMessage();
         }
        
    return msg;
    }    
    
     public String restoreDB(String path){
    
        String msg=null;
        
        try {
            Runtime runtime=Runtime.getRuntime();
            Process p=runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe -user=root --password=pathu@1997.4 -e "+path );
            int processComplete=p.waitFor();
            System.out.println("Restore process done"+processComplete);
            msg="Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="Error"+e.getMessage();
         }
        
    return msg;
    }    
}
