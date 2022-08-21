package servlets;

import db.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*Name: Elmaddin Azizli
Class: CNT:4714, Project 4
Date: 04/20/2022
 */
@WebServlet(urlPatterns = {"/RootUserApp"})
public class RootUserApp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String query = request.getParameter("query");
            HttpSession session = request.getSession();

            if (query.contains("select")) {
                // select all table rows
                try {

                    Connection con = ConnectionProvider.getConnection();
                    PreparedStatement ps = con.prepareStatement(query);
                    ResultSet set = ps.executeQuery();
                    session.setAttribute("result", set);
                    response.sendRedirect("rootHome.jsp");

                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    session.setAttribute("error", e.getMessage());
                    response.sendRedirect("rootHome.jsp");
                }

            } else if (query.contains("insert")) {

                if (query.contains("shipments")) {

                    try {

                        Connection con = ConnectionProvider.getConnection();
                        PreparedStatement ps = con.prepareStatement(query);
                        int row = ps.executeUpdate();

                        String msg = "The statement executed successfully.<br>" + row
                                + " row(s) affected.<br>";
                        String q = query.split("'")[1];
                        System.out.println(query);
                        System.out.println(q);
                        ps = con.prepareStatement("update suppliers set status=status+5 where snum=?");
                        ps.setString(1, q);
                        row = ps.executeUpdate();

                        msg += "<b>Bussines Logic Detected! - Updating Supplier Status<br>"
                                + "Bussiness Logic updated " + row + " supplier status marks.</b>";
                        session.setAttribute("success", msg);
                        response.sendRedirect("rootHome.jsp");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        session.setAttribute("error", e.getMessage());
                        response.sendRedirect("rootHome.jsp");
                    }

                } else {
                    try {

                        Connection con = ConnectionProvider.getConnection();
                        PreparedStatement ps = con.prepareStatement(query);
                        int row = ps.executeUpdate();
                        session.setAttribute("success", "The statement executed successfully.<br>"
                                + row + " row(s) affected.<br><b>Bussiness Logic Not Triggered!<b>");
                        response.sendRedirect("rootHome.jsp");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        session.setAttribute("error", e.getMessage());
                        response.sendRedirect("rootHome.jsp");
                    }
                }

            } else if (query.contains("update")) {

                if (query.contains("shipments")) {

                    try {

                        Connection con = ConnectionProvider.getConnection();
                        PreparedStatement ps = con.prepareStatement(query);
                        int row = ps.executeUpdate();

                        String msg = "The statement executed successfully.<br>" + row
                                + " row(s) affected.<br>";

                        int row2 = 0;

                        ps = con.prepareStatement("select * from shipments where quantity>=100");
                        ResultSet set = ps.executeQuery();

                        if (set != null) {
                            while (set.next()) {
                                ps = con.prepareStatement("update suppliers set status = status+5"
                                        + " where snum = ?");
                                ps.setString(1, set.getString("snum"));
                                row2 += ps.executeUpdate();
                            }
                        }

                        msg += "<b>Bussines Logic Detected! - Updating Supplier Status<br>"
                                + "Bussiness Logic updated " + row2 + " supplier status marks.</b>";
                        session.setAttribute("success", msg);
                        response.sendRedirect("rootHome.jsp");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                        session.setAttribute("error", e.getMessage());
                        response.sendRedirect("rootHome.jsp");
                    }

                } else {
                    try {

                        Connection con = ConnectionProvider.getConnection();
                        PreparedStatement ps = con.prepareStatement(query);
                        int row = ps.executeUpdate();
                        session.setAttribute("success", "The statement executed successfully.<br>"
                                + row + " row(s) affected.<br><b>Bussiness Logic Not Triggered!<b>");
                        response.sendRedirect("rootHome.jsp");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        session.setAttribute("error", e.getMessage());
                        response.sendRedirect("rootHome.jsp");
                    }
                }
            } else if (query.contains("replace")) {
                try {

                    Connection con = ConnectionProvider.getConnection();
                    PreparedStatement ps = con.prepareStatement(query);
                    int row = ps.executeUpdate();
                    session.setAttribute("success", "The statement executed successfully.<br>"
                            + row + " row(s) affected.<br><b>Bussiness Logic Not Triggered!<b>");
                    response.sendRedirect("rootHome.jsp");

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    session.setAttribute("error", e.getMessage());
                    response.sendRedirect("rootHome.jsp");
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
