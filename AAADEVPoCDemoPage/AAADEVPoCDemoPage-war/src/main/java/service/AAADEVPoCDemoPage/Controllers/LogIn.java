package service.AAADEVPoCDemoPage.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avaya.collaboration.util.logger.Logger;

import service.AAADEVPoCDemoPage.Actions.LogInActions;
import service.AAADEVPoCDemoPage.Util.Constants;
import service.AAADEVPoCDemoPage.Util.PartToString;

/**
 *
 * @author umansilla
 */
@MultipartConfig
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.getRequestDispatcher("/LogIn/").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        
        String part = new PartToString().getStringValue(request.getPart(Constants.LOG_IN_ACTION));
        switch (part) {
            case Constants.LOG_IN_LOG_OUT:
                new LogInActions(request, response).logOut();
                break;
            case Constants.LOG_IN_LOGIN:
                new LogInActions(request, response).register();
                break;
            default:
                response.setStatus(400);
                response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
                break;
        }
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", Constants.ACCESS_ORIGIN);
        response.setHeader("Access-Control-Allow-Credentials", Constants.ACCESS_CREDENTIALS);
        response.setHeader("Access-Control-Allow-Methods", Constants.ACCESS_METHODS);
        response.setHeader("Access-Control-Allow-Headers", Constants.ACCESS_HEADERS);
        response.setHeader("Content-Type", Constants.CONTENT_TYPE);
    }

}
