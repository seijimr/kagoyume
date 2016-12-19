
package ky;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userdata.UserDataDAO;
import userdata.UserDataDTO;

public class Logincheck extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            //アクセスルートチェック
            HttpSession session = request.getSession(true);
            String accesschk = request.getParameter("ac");
            if (accesschk == null || (Integer) session.getAttribute("ac") != Integer.parseInt(accesschk)) {
                throw new Exception("不正なアクセスです");
            }
            String mail = request.getParameter("mail");
            String pass = request.getParameter("pass");
            String url =(request.getParameter("Cart")==null)
                    ?request.getParameter("url"):request.getParameter("Cart");

            UserDataDTO userdata = new UserDataDAO().LoginCheck(mail, pass);
            //登録がなければログインページへ
            if (userdata == null) {
                request.setAttribute("url", url);
                request.setAttribute("loginerror", "YES");
                request.setAttribute("mail", mail);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } //削除フラグのあるユーザーがログインを試みた場合エラーページへ
            else if (userdata.getDeleteflg() != 0) {
                session.removeAttribute("ac");
                throw new Exception("退会済みのユーザーです。");
            } //ログインに成功した場合はログインし、元の画面に戻る
            else {
                session.removeAttribute("ac");
                //ログインしていない時にカートに入れた商品をユーザー用カートに移す
                int id = userdata.getUserID();
                if (session.getAttribute("user" + id + "cart") != null) {
                    if (session.getAttribute("cart") != null) {
                        ArrayList<String> usercart = (ArrayList<String>) session.getAttribute("user" + id + "cart");
                        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
                        for (int i = 0; i < cart.size(); i++) {
                            usercart.add(cart.get(i));
                        }
                        session.setAttribute("user" + id + "cart", usercart);
                    }
                } else {
                    session.setAttribute("user" + id + "cart", session.getAttribute("cart"));
                }
                session.removeAttribute("cart");
                session.setAttribute("userdata", userdata);
                response.sendRedirect(url);
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
