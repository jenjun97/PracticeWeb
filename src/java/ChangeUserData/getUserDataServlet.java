/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangeUserData;

import DB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jun
 */
@WebServlet(name = "getUserDataServlet", urlPatterns = {"/getUserDataServlet.html"})
public class getUserDataServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        String userEmail = session.getAttribute("userEmail").toString();

        ConnectDB connectDB = new ConnectDB();
        HashMap<String,String> UserData = connectDB.getUserData(userEmail);

        if (UserData.size() != 0) {
            session.setAttribute("userEmail", UserData.get("userEmail").toString());
            session.setAttribute("userPassword", UserData.get("userPassword").toString());
            session.setAttribute("userName", UserData.get("userName").toString());
            session.setAttribute("userBirth", UserData.get("userBirth").toString());
            session.setAttribute("userSex", UserData.get("userSex").toString());
            session.setAttribute("userAddress", UserData.get("userAddress").toString());
            session.setAttribute("userPhone", UserData.get("userPhone").toString());
        }

        System.out.println("success");

        response.sendRedirect("/PracticeWeb/page/changeUserData.jsp");

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
