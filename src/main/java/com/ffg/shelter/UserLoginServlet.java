package com.ffg.shelter;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = -6169462831325650504L;

    @Override
    public void doGet(HttpServletRequest httpReq, HttpServletResponse httpRes)
            throws IOException {
        String url = httpReq.getParameter("redirect");
        if (url != null) {
            UserService userService = UserServiceFactory.getUserService();
            httpRes.sendRedirect(userService.createLoginURL(httpReq.getParameter("")));
        }
    }
}
