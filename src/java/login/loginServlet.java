package login;

import DB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String inputEmail = request.getParameter("inputEmail"); // 前端輸入email
        String inputPassword = request.getParameter("inputPassword"); // 前端輸入password

        ConnectDB connectDB = new ConnectDB(); // 建立與DB連線

        String getDBuserEmail = connectDB.getOneData("user_email", "user_email = \"" + inputEmail + "\""); // 取得與eamil相同之email
        String getDBuserPassword = connectDB.getOneData("user_password", "user_email = \"" + inputEmail + "\""); // 取得與email相同之password

        String str = "";

        if (getDBuserEmail.equals("")) {
            str = "";
        } else if (!getDBuserPassword.equals(inputPassword)) {
            str = "({email:true,password:false})";
        } else {
            str = "({email:true,password:true})";
            
            HttpSession session = request.getSession();
            session.setAttribute("userName", connectDB.getOneData("user_name", "user_email = \"" + inputEmail + "\""));
            session.setAttribute("userEmail", getDBuserEmail);
            session.setAttribute("userLevel", connectDB.getOneData("user_level", "user_email = \"" + inputEmail + "\"")); // 取得與email相同之帳號Level
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(str);
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
