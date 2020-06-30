package service.AAADEVPoCDemoPage.Controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import service.AAADEVPoCDemoPage.Beans.UserBean;
import service.AAADEVPoCDemoPage.Http.HttpTokenRequest;
import service.AAADEVPoCDemoPage.Security.AES;
import service.AAADEVPoCDemoPage.Security.XSSPrevent;
import service.AAADEVPoCDemoPage.Util.Constants;

@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegisterController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("RegisterController -> doGet");
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			System.out.println("RegisterController -> doGet, No have session");
			removeCookie(request, response);
			request.getRequestDispatcher("/LogIn/").forward(request, response);
		} else if (!userBeanSession.getRol().equals("SADMIN")) {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
		}  else {
			System.out.println("RegisterController -> doGet, Have session");
			request.setAttribute("isLogged", true);
			
			String email = "";
			try {
				email = request.getParameter("email");
				System.out.println("RegisterController doGet Parametro = " + email);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("RegisterController doGet sin Parametro");
			}
			
			request.setAttribute("email", email);
			request.getRequestDispatcher("/Register/").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("RegisterController POst Request");
		
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null || !userBeanSession.getRol().equals("SADMIN")) {
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
			removeCookie(request, response);
		} else {
			try {
				//Por alguna razon, si obtengo el body despues de request get parameter ya no funciona, el body sale vacio
				String body = getBody(request);
				JSONObject jsonObject = new JSONObject(body);
				String email = Constants.decryptText(jsonObject.getString("email"));
				String creator = new AES().encrypt(XSSPrevent.stripXSS(userBeanSession.getUserName()));
				String create = new AES().encrypt(XSSPrevent.stripXSS(email));
				JSONObject payload = new JSONObject();
				payload.put("creator", creator);
				payload.put("create", create);
				
				JSONObject resp = new JSONObject(new HttpTokenRequest().createNewUser(new AES().encrypt(payload.toString())));
				if(resp.getString("message").contains("User created")) {
					String user = new AES().decrypt(resp.getJSONObject("user").getString("username"));
					String pass = new AES().decrypt(resp.getJSONObject("user").getString("password"));
					System.out.println("primer Respuesta: "+resp);
					resp.getJSONObject("user").put("username", user);
					resp.getJSONObject("user").put("password", pass);
				}
				
				response.getWriter().print(resp.toString());
				System.out.println("respuesta del endpoint: "+resp);
			} catch (Exception e) {
				System.out.println("RegisterController doPost exception: " + e.getMessage());
				response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST.put("error", "message: "+e.getMessage()+"\nCause:"+e.getCause()));
			}
		}
	}
	
}
