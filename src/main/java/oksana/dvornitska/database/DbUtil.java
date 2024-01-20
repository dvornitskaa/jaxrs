package oksana.dvornitska.database;

import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class DbUtil {

    public static String url = "jdbc:mysql://localhost:3306/warehouse";
    public static String user = "root";
    public static String password = "password";
    public static String driver = "com.mysql.cj.jdbc.Driver";

}
