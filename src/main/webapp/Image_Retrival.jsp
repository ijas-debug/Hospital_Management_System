<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
Blob image = null;
Connection con = null;
byte[ ] imgData = null ;
Statement stmt = null;
ResultSet rs = null;
try {

    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospitalone","root","");
    stmt = con.createStatement();
    rs = stmt.executeQuery("select * from images");
    if (rs.next()) {
    image = rs.getBlob(1);
    imgData = image.getBytes(1,(int)image.length());
    } 
    else {
    out.println("Display Blob Example");
    out.println("image not found for given id>");
    return;
    }
    // display the image
    response.setContentType("image/jpg");
    OutputStream o = response.getOutputStream();
    o.write(imgData);
    o.flush();
    o.close();
} catch (Exception e) {
        out.println("Unable To Display image");
    out.println("Image Display Error=" + e.getMessage());
    return;
    } finally {
    
    try {
    rs.close();
    stmt.close();
    con.close();
    
    } catch (SQLException e) {
    
    e.printStackTrace();
    
    }

}

%>
</body>
</html>