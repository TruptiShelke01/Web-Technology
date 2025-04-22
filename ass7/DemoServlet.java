import jakarta.servlet.http.*;  
import jakarta.servlet.*;  
import java.io.*; 
import java.sql.*;  

public class DemoServlet extends HttpServlet {  
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
        throws ServletException, IOException {  

        res.setContentType("text/html");  
        PrintWriter pw = res.getWriter();   

        pw.println("<html><body>");
        pw.println("<h2>Welcome By Sneha Somnath More</h2>");  
        pw.println("<table border='5'>");  
        pw.println("<tr><th>Book ID</th><th>Book Title</th><th>Book Author</th><th>Book Price</th><th>Quantity</th></tr>");

        try { 
            // ✅ Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");  

            // ✅ Connect to the database
            String url = "jdbc:mysql://localhost:3306/MiniBini?useSSL=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "");  // replace "" with password if any

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Sneha"); 

            while (rs.next()) {    
                long bookId = rs.getLong(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                double price = rs.getDouble(4);
                int quantity = rs.getInt(5);

                pw.println("<tr><td>" + bookId + "</td><td>" + title + "</td><td>" + author + "</td><td>" + price + "</td><td>" + quantity + "</td></tr>");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            pw.println("<tr><td colspan='5' style='color:red;'>Error: " + e.getMessage() + "</td></tr>");  
            e.printStackTrace();  // logs in console
        } 

        pw.println("</table>");
        pw.println("</body></html>");    
        pw.close();  
    }  
}