/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangeUserData;

import DB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.coyote.http11.Constants;

/**
 *
 * @author Jun
 */
public class ChangeUserDataServlet extends HttpServlet {

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

        System.out.println("get in ");
        try {
            String userEmail = request.getSession().getAttribute("userEmail").toString(); // 抓取前端的email
//            request.getSession().setAttribute("userName", new String((request.getParameter("inputName")).getBytes("iso-8859-1"), "UTF-8"));

            // 下列為要送去sql的語法
            String keyValue = "";
            String whereSql = "user_email = \"" + userEmail + "\"";

            keyValue += "user_name = \"" + new String((request.getParameter("inputName")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
            keyValue += "user_password = \"" + new String((request.getParameter("inputPassword")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
            keyValue += "user_sex = \"" + new String((request.getParameter("inputSex")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
            keyValue += "user_birth = \"" + new String((request.getParameter("inputBirth")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
            keyValue += "user_phone = \"" + new String((request.getParameter("inputPhone")).getBytes("iso-8859-1"), "UTF-8") + "\" , ";
            keyValue += "user_address = \"" + new String((request.getParameter("inputAddress")).getBytes("iso-8859-1"), "UTF-8") + "\"";

            System.out.println("key>>" + keyValue);

            new ConnectDB().updataDB(keyValue, whereSql);

            String getUserName = new ConnectDB().getOneData("user_name", "user_email = \"" + userEmail + "\"");
            request.getSession().setAttribute("userName", getUserName);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            response.sendRedirect("/PracticeWeb/page/index.jsp");
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
