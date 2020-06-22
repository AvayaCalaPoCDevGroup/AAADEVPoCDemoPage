package service.AAADEVPoCDemoPage.Actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.avaya.aus.archiveschemas.aus.ServiceDescription.Component;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.AAADEVPoCDemoPage.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;
import service.AAADEVPoCDemoPage.Entity.Tag;
import service.AAADEVPoCDemoPage.Service.AaaDevPoCDemoPageImpl;
import service.AAADEVPoCDemoPage.Util.Constants;

public class DemoActions extends BaseAction{
	
    public DemoActions(HttpServletRequest request, HttpServletResponse response) {
        super( request, response);
    }
    
    public void CreateOrUpdateDemo(String body) throws NonexistentEntityException, Exception {
    	
//    	JSONObject jsonObject = new JSONObject(body);
//    	Demo demo = new Demo();
//    	Long id = jsonObject.getLong("id");
//    	if(id>0) {
//    		demo.setId(id);
//    	}
//    	demo.setDescription(jsonObject.getString("description"));
//    	demo.setTitle(jsonObject.getString("title"));
//    	demo.setLink(jsonObject.getString("link"));
//    	demo.setAvailable(jsonObject.getBoolean("available"));
		Demo demo = new Gson().fromJson(body, Demo.class);
		boolean resp = new AaaDevPoCDemoPageImpl().CreateOrUpdateDemo(demo);
		if(resp) {
			response.getWriter().println(Constants.HTTP_RESPONSE_OK);
		} else {
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    public void DeleteDemo() throws IOException {
    	long id = 0;
		id = Integer.valueOf(request.getParameter("d"));
		boolean resp = new AaaDevPoCDemoPageImpl().DeleteDemo(id);
		if(resp) {
			response.getWriter().print(Constants.HTTP_RESPONSE_OK);
		} else {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    public void DeleteComponent() throws IOException {
    	long id = 0;
		id = Integer.valueOf(request.getParameter("d"));
		boolean resp = new AaaDevPoCDemoPageImpl().deleteComponent(id);
		if(resp) {
			response.getWriter().print(Constants.HTTP_RESPONSE_OK);
		} else {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    public void DeleteTag() throws IOException {
    	long id = 0;
		id = Integer.valueOf(request.getParameter("d"));
		boolean resp = new AaaDevPoCDemoPageImpl().deleteTag(id);
		if(resp) {
			response.getWriter().print(Constants.HTTP_RESPONSE_OK);
		} else {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    public void CreateComponent(String body) throws IOException {
    	
		Components component = new Gson().fromJson(body, Components.class);
		Components resp = new AaaDevPoCDemoPageImpl().createComponent(component);
		if(resp != null) {
			response.getWriter().println(Constants.HTTP_RESPONSE_OK.put("entity", new JSONObject(resp)));
		} else {
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    public void CreateTag(String body) throws IOException {
    	
		Tag component = new Gson().fromJson(body, Tag.class);
		Tag resp = new AaaDevPoCDemoPageImpl().createTag(component);
		if(resp != null) {
			response.getWriter().println(Constants.HTTP_RESPONSE_OK.put("entity", new JSONObject(resp)));
		} else {
			response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
   
}
