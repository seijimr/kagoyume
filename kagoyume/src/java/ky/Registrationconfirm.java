package ky;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userdata.UserDataBeans;
import userdata.UserDataDAO;

public class Registrationconfirm extends HttpServlet {

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
            String name = (!request.getParameter("name").equals(""))
                    ? request.getParameter("name") : "";
            String pass = (!request.getParameter("pass").equals(""))
                    ? request.getParameter("pass") : "";
            String mail = (!request.getParameter("mail").equals(""))
                    ? request.getParameter("mail") : "";
            String address = (!request.getParameter("address").equals(""))
                    ? request.getParameter("address") : "";
            Boolean mailbool = new UserDataDAO().RegisteredConfirm(mail);

            UserDataBeans udb = new UserDataBeans();
            udb.setName(name);
            udb.setPass(pass);
            udb.setMail(mail);
            udb.setAddress(address);
            request.setAttribute("forminput",udb);
            
            //登録済み、入力漏れ、パスワードの不一致を防止する
            if (pass.equals(request.getParameter("repass")) && !name.equals("") && !mail.equals("") && !address.equals("") && !mailbool) {
                request.getRequestDispatcher("/registrationconfirm.jsp").forward(request, response);
            }else{
            if (name.equals("") || mail.equals("") || address.equals("")) {
                request.setAttribute("omission", "<br><font color=\"red\">全ての項目を入力してください</font>");
            }
            if (!pass.equals(request.getParameter("repass"))) {
                request.setAttribute("passnotmatch", "<br><font color=\"red\">パスワードが一致していません。</font>");
            }
            if (mailbool) {
                request.setAttribute("registered", "<br><font color=\"red\">既に登録されているメールアドレスです。</font>");
            }
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
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
