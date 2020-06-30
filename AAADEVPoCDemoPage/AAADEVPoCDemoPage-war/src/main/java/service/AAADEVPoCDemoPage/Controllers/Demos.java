package service.AAADEVPoCDemoPage.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.google.gson.Gson;

import service.AAADEVPoCDemoPage.Actions.DemoActions;
import service.AAADEVPoCDemoPage.Beans.UserBean;
import service.AAADEVPoCDemoPage.Entity.Demo;
import service.AAADEVPoCDemoPage.Service.AaaDevPoCDemoPageImpl;
import service.AAADEVPoCDemoPage.Util.Constants;

/**
 * Servlet implementation class Demos
 */
@WebServlet(name = "Demos", urlPatterns = {"/Demos"})
@MultipartConfig
public class Demos extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Demos -> doGet");
		//response.getWriter().append("/Demos/").append(request.getContextPath());
		setAccessControlHeaders(response);
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			removeCookie(request, response);
			request.getRequestDispatcher("/LogIn/").forward(request, response);
		} else {
			if(request.getParameterMap().containsKey("p") && !request.getParameter("p").isEmpty()) {
				//preguntamos si trae la opcion admin para demos
				switch (request.getParameter("p")) {
				case "admin":
					if(userBeanSession.getRol().equals("SADMIN"))
						request.getRequestDispatcher("/DemosAdmin/").forward(request, response);
					else 
						response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
					break;
				default:
					response.getWriter().println(
							new JSONObject().put("status", "error").put("message", "Bad Request").put("code", 400));
					break;
				}
				
			} else if (request.getParameterMap().containsKey("i") && !request.getParameter("i").isEmpty()) {
				System.out.println("Demos Get file");
				new DemoActions(request, response).getCollateralFile();
			} else {
				//demos
				
				request.getRequestDispatcher("/Demos/").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Demos POst Request");
		setAccessControlHeaders(response);
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
		} else if (!userBeanSession.getRol().equals("SADMIN")) {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
		}  else {
			try {
				//Por alguna razon, si obtengo el body despues de request get parameter ya no funciona, el body sale vacio
				
				Part filePart = null;
				String iddemo = null;
				String type = null;
				String title = null;
				try {
					filePart = request.getPart("collateral");
					iddemo = request.getParameter("iddemo");
					title = request.getParameter("title");
					type =  request.getParameter("type");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
				
				String body = getBody(request);
				String action = request.getParameter("action");
				switch (action) {
				case "demo":
					new DemoActions(request, response).CreateOrUpdateDemo(body);
					break;
				case "component":
					System.out.println("Demos POst Request Component");
					new DemoActions(request, response).CreateComponent(body);
					break;
				case "tag":
					System.out.println("Demos POst Request Tag");
					new DemoActions(request, response).CreateTag(body);
					break;
				case "collateral":
					System.out.println("Demos POst Request Collateral");
					new DemoActions(request, response).CreateCollateral(iddemo, title, type ,filePart);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Demos doPost exception: " + e.getMessage());
				response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST.put("error", "message: "+e.getMessage()+"\nCause:"+e.getCause()));
			}
		}
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doDelete");
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
		} else if (!userBeanSession.getRol().equals("SADMIN")) {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
		}  else {
			
			try {
				String action = request.getParameter("action");
				switch (action) {
				case "demo":
					new DemoActions(request, response).DeleteDemo();
					break;
				case "component":
					new DemoActions(request, response).DeleteComponent();
					break;
				case "tag":
					new DemoActions(request, response).DeleteTag();
					break;
				case "collateral":
					new DemoActions(request, response).DeleteCollateral();
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.err.println("error doDelete" + e.getLocalizedMessage());
				response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
			}
		}
	}

}
