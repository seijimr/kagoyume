package ky;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userdata.UserDataDTO;

public class Cart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            HttpSession session = request.getSession(true);
            UserDataDTO userdata;

            //ログインしていなければログイン画面へ
            if (session.getAttribute("userdata") == null) {
                request.setAttribute("Cart","Cart");
                request.getRequestDispatcher("Login").forward(request, response);
            } else {
                userdata = (UserDataDTO) session.getAttribute("userdata");
                int id = userdata.getUserID();
                //まだカートがなければカートを持たせる
                if (session.getAttribute("user" + id + "cart") == null) {
                    session.setAttribute("user" + id + "cart", new ArrayList<ItemDataBeans>());
                }
                //カートの削除ボタンからの遷移の場合要素を削除
                if (request.getParameter("delete") != null) {
                    ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("user" + id + "cart");
                    int i = new Integer(request.getParameter("delete"));
                    cart.remove(i);
                    session.setAttribute("user" + id + "cart", cart);
                }
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }

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
