package ky;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userdata.UserDataDTO;

public class Add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            HttpSession session = request.getSession(true);
            ArrayList<ItemDataBeans> cart = new ArrayList<ItemDataBeans>();
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String img = request.getParameter("img");
            ItemDataBeans idb = new ItemDataBeans();
            idb.setCode(code);
                    idb.setName(name);
                    idb.setPrice(price);
                    idb.setSmallimg(img);
            
            if (session.getAttribute("userdata") != null) {
                UserDataDTO ud = (UserDataDTO) session.getAttribute("userdata");

                //ログインしている＋カートを持っている
                if (session.getAttribute("user" + ud.getUserID() + "cart") != null) {
                    cart = (ArrayList<ItemDataBeans>) session.getAttribute("user" + ud.getUserID() + "cart");
                    cart.add(idb);
                    session.setAttribute("user" + ud.getUserID() + "cart", cart);
                } else {
                    //ログインしている＋カートを持っていない
                    cart.add(idb);
                    session.setAttribute("user" + ud.getUserID() + "cart", cart);
                }

                //ログインしていない＋カートを持っている
            } else if (session.getAttribute("cart") != null) {
                cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
                cart.add(idb);
                session.setAttribute("cart", cart);
            } else {
                //ログインしていない＋カートを持っていない
                cart.add(idb);
                session.setAttribute("cart", cart);
            }
            
            request.setAttribute("idb", idb);
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        } catch (Exception e) {
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
