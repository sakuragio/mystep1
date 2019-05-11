package com.wteng.websocket.practise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangteng on 2019/5/11.
 */
@WebServlet("/do")
public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flag", "WTENG");
        req.getRequestDispatcher("/WEB-INF/view/websocket.jsp").forward(req, resp);
    }
}
