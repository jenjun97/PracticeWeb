package register;

import DB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class registerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //--------------------------------------------------------------------------------------------
        response.setContentType("text/html;charset=UTF-8");

        // 新增一個HasMap以放入key, value
        HashMap<String, String> DBmap = new HashMap();

        // 取出所有form的值放入HasMap
        DBmap.put("user_email", request.getParameter("inputEmail"));
        DBmap.put("user_password", request.getParameter("inputPassword"));
        DBmap.put("user_name", new String((request.getParameter("inputName")).getBytes("iso-8859-1"), "utf-8"));
        DBmap.put("user_birth", request.getParameter("inputBirth"));
        DBmap.put("user_sex", request.getParameter("inputSex"));

        // 因為getParameter取中文時會變亂碼，因此用iso-885-1編碼
        String inputCity = new String((request.getParameter("inputCity")).getBytes("iso-8859-1"), "utf-8");
        String inputArea = new String((request.getParameter("inputArea")).getBytes("iso-8859-1"), "utf-8");
        String inputAddress = new String((request.getParameter("inputAddress")).getBytes("iso-8859-1"), "utf-8");

        inputAddress = inputCity + inputArea + inputAddress;
        DBmap.put("user_address", inputAddress);
        DBmap.put("user_phone", request.getParameter("inputPhone"));

        // 寫入DB
        new ConnectDB().insertDB(DBmap);
        // -------------------------------------------------------
        HttpSession session = request.getSession();
        session.setAttribute("userName", DBmap.get("user_name"));
        session.setAttribute("userEmail", DBmap.get("user_email"));
        session.setAttribute("userLevel", new ConnectDB().getOneData("user_level", "user_email = \"" + session.getAttribute("userEmail") + "\""));
        response.sendRedirect("page/index.jsp");
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ItemPage/index.jsp");
//        rd.forward(request, response);

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
