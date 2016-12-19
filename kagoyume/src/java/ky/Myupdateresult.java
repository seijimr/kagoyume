package ky;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userdata.UserDataBeans;
import userdata.UserDataDAO;
import userdata.UserDataDTO;

public class Myupdateresult extends HttpServlet {

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
            session.removeAttribute("ac");

            UserDataDTO udd = (UserDataDTO) session.getAttribute("userdata");
            String name = (!request.getParameter("name").equals(""))
                    ? request.getParameter("name") : "";
            String passcur = (!request.getParameter("passcur").equals(""))
                    ? request.getParameter("passcur") : "";
            String passnew = (!request.getParameter("passnew").equals(""))
                    ? request.getParameter("passnew") : "";
            String mail = (!request.getParameter("mail").equals(""))
                    ? request.getParameter("mail") : "";
            String address = (!request.getParameter("address").equals(""))
                    ? request.getParameter("address") : "";
            Boolean mailbool=false;
            if(!mail.equals(udd.getMail())){
                mailbool = new UserDataDAO().RegisteredConfirm(mail);
            }
            

            UserDataBeans udb = new UserDataBeans();
            udb.setName(name);
            if (passnew.equals("")) {
                udb.setPass(passcur);
            } else {
                udb.setPass(passnew);
            }
            udb.setMail(mail);
            udb.setAddress(address);
            request.setAttribute("forminput", udb);


            //登録済み、入力漏れ、パスワードの不一致等を防止する
            if (passcur.equals(udd.getPass()) && passnew.equals(request.getParameter("repass")) && !name.equals("") && !mail.equals("") && !address.equals("") && !mailbool) {
                new UserDataDAO().update(udb, udd.getUserID());
                udd.setName(name);
                udd.setPass(passnew);
                udd.setMail(mail);
                udd.setAddress(address);
                session.setAttribute("userdata", udd);
                request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
            } else if (passcur.equals(udd.getPass()) && passnew.equals("") && !name.equals("") && !mail.equals("") && !address.equals("") && !mailbool) {
                new UserDataDAO().update(udb, udd.getUserID());
                udd.setName(name);
                udd.setPass(passcur);
                udd.setMail(mail);
                udd.setAddress(address);
                session.setAttribute("userdata", udd);
                request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
            } else {
                if (!passcur.equals(udd.getPass())) {
                    request.setAttribute("passcurnotmatch", "<br><font color=\"red\">現在のパスワードが間違っています</font>");
                } else if (!passnew.equals(request.getParameter("repass"))) {
                    request.setAttribute("passnotmatch", "<br><font color=\"red\">パスワードが一致していません。</font>");
                }
                if (name.equals("") || mail.equals("") || address.equals("")) {
                    request.setAttribute("omission", "<br><font color=\"red\">全ての項目を入力してください</font>");
                }
                if (mailbool) {
                    request.setAttribute("registered", "<br><font color=\"red\">既に登録されているメールアドレスです。</font>");
                }
                if (passcur.equals(udd.getPass())) {
                    request.setAttribute("pass", "<br><font color=\"red\"></font>");
                }
                request.getRequestDispatcher("Myupdate").forward(request, response);
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
