package ManagementUser;

import DB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jun
 */
public class ManagementChangeUserDataServlet extends HttpServlet {

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

        // 要更新的是哪一筆email
        String userEmail = new String((request.getParameter("inputEmail")).getBytes("iso-8859-1"), "UTF-8");

        System.out.println("get in ");

        String keyValue = ""; // 放入要更新的值key value
        String whereSql = "user_email = \"" + userEmail + "\"";
        keyValue += "user_name = \"" + new String((request.getParameter("inputName")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
        keyValue += "user_password = \"" + new String((request.getParameter("inputPassword")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
        keyValue += "user_sex = \"" + new String((request.getParameter("inputSex")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
        keyValue += "user_birth = \"" + new String((request.getParameter("inputBirth")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
        keyValue += "user_phone = \"" + new String((request.getParameter("inputPhone")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
        keyValue += "user_address = \"" + new String((request.getParameter("inputAddress")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
        keyValue += "user_level = \"" + new String((request.getParameter("inputLevel")).getBytes("iso-8859-1"), "UTF-8") + "\"";

        //  顯示sql update set的值
        System.out.println("key>>" + keyValue);

        // 對資料庫該筆資料做更新
        new ConnectDB().updataDB(keyValue, whereSql);

        // 這裡做一個判斷，看登入者是否修改的是自己的名稱
        // 如果是一樣的，那就更改session的使用者名稱
        if (userEmail.equals(request.getSession().getAttribute("userEmail").toString())) {
            request.getSession().setAttribute("userName", new String((request.getParameter("inputName")).getBytes("iso-8859-1"), "UTF-8"));
        }

        response.sendRedirect("/PracticeWeb/page/ManagementUser.jsp");

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
