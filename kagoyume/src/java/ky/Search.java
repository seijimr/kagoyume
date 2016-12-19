package ky;

import base.YahooApiManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
class EmptyException extends Exception {
}

public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EmptyException {
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            YahooApiManager yam = new YahooApiManager();

            //現在のパラメータを含むURLを取得する
            StringBuffer url = request.getRequestURL();
            url.append("?").append(request.getQueryString());
            String urls = url.toString();
            request.setAttribute("url", urls);

            String search = null;
            String page=(request.getParameter("page") != null) ? request.getParameter("page") : null;
            
            search = request.getParameter("search");

            //クエリにpageがあるかで処理を変更
            ArrayList<ItemDataBeans> searchresult = null;
            String json = null;
            if (page == null) {
                json = yam.productData(search);
                searchresult = yam.parse(json);
            } else {
                json = yam.productData(search, page);
                searchresult = yam.parse(json);
            }

            //検索結果が0件だった場合に例外を投げる
            if (searchresult.isEmpty()) throw new EmptyException();

            //合計金額
            String total = yam.total(json);

            request.setAttribute("jsp", "/search.jsp");
            request.setAttribute("total", total);
            request.setAttribute("searchresult", searchresult);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        } catch (IOException i) {
            //APIにアクセスする際にエラーが出た場合。想定は検索キーワードの入力がなかった時。
            request.setAttribute("nothing", "<h3>検索したい言葉（キーワード）が入力されていません。</h3>キーワードを入力し、再度「検索」ボタンを押してください。<br>");
            request.setAttribute("jsp", "/search.jsp");
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        } catch (EmptyException i) {
            //検索結果が0件だった場合
            request.setAttribute("nothing", "検索結果がありませんでした。");
            request.setAttribute("jsp", "/search.jsp");
            request.getRequestDispatcher("/search.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (EmptyException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (EmptyException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
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
