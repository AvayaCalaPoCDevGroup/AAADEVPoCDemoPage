package service.AAADEVPoCDemoPage.Controllers;

import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import service.AAADEVPoCDemoPage.Beans.ResgisterRequestModel;
import service.AAADEVPoCDemoPage.Util.Constants;
import service.AAADEVPoCDemoPage.Util.MailUtils;

/**
 * Servlet implementation class SingUp
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends BaseController {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignUp() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jsonString = getBody(request);
			ResgisterRequestModel registerRequest = new Gson().fromJson(jsonString, ResgisterRequestModel.class);
			String mailtext = Constants.MAIL_REGISTER_REQUEST
					.replace(Constants.MAILPART_NOMBRES, registerRequest.nombres)
					.replace(Constants.MAILPART_APELLIDOS, registerRequest.apellidos)
					.replace(Constants.MAILPART_EMAIL, registerRequest.correo)
					.replace(Constants.MAILPART_MOVIL, registerRequest.telefonoMovil)
					.replace(Constants.MAILPART_CIUDAD, registerRequest.ciudad);
					
			new MailUtils(Constants.SMTP_USER, 
					//new String[] {"jlramirez@avaya.com", "umansilla@avaya.com", "martinez71@avaya.com", registerRequest.correo},
					new String[] {"martinez71@avaya.com", registerRequest.correo},
					mailtext, 
					"Solicitud de acceso a pagina de demos", null).Send();
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_OK_AUTHORIZED);
		} catch (Exception e) {			
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST.put("error", "message: "+e.getMessage()+"\nCause: "+e.getCause()+"\nCause: "+e.getStackTrace()));
			System.out.println("SignUpController doPostError: " + e.getMessage());
		}
	}

}
