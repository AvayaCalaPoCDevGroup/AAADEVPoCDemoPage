package service.AAADEVPoCDemoPage.Actions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.avaya.aus.archiveschemas.aus.ServiceDescription.Component;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.AAADEVPoCDemoPage.Dao.CollateralsJpaController;
import service.AAADEVPoCDemoPage.Dao.Exceptions.NonexistentEntityException;
import service.AAADEVPoCDemoPage.Entity.Collaterals;
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
    
    public void DeleteCollateral() throws IOException {
    	long id = 0;
		id = Integer.valueOf(request.getParameter("d"));
		boolean resp = new AaaDevPoCDemoPageImpl().deleteCollateral(id);
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
    
    public void CreateCollateral(String iddemo, String title, String type, Part filePart) throws IOException, ServletException {
    	//System.out.println("body: " + body);
    	String name = filePart.getName();
    	String filetype = filePart.getContentType();
    	byte[] data = InputSteamToByteArray(filePart.getInputStream());
    	System.out.println("iddemo: " + iddemo + " par.name: " + name + "type: " + filetype);
    	
    	Collaterals collaterals = new Collaterals();
    	collaterals.setIddemo(iddemo);
    	collaterals.setTitle(title);
    	collaterals.setType(type);
    	collaterals.setFiletype(filetype);
    	collaterals.setFile(data);
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		collaterals.setUpdatedtime(formatter.format(date));
    	
    	Collaterals resp = new AaaDevPoCDemoPageImpl().crearColateral(collaterals);    	
    	
    	if(resp != null) {
    		resp.setFile(new byte[] {0x00});
			response.getWriter().print(Constants.HTTP_RESPONSE_OK.put("entity", new JSONObject(resp)));
		} else {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    public void getCollateralFile() throws IOException {
//    	String iddemo = request.getParameter("d");
		Long id = Long.parseLong(request.getParameter("i"));
		Collaterals resp = new AaaDevPoCDemoPageImpl().getByIdDemoAndId(id);
		if(resp != null) {
			response.setContentType(resp.getFiletype());
			response.setHeader("Content-Disposition", "filename=\""+resp.getTitle()+"\"");
			response.setContentLength(resp.getFile().length);
			OutputStream os = response.getOutputStream();

			try {
			   os.write(resp.getFile() , 0, resp.getFile().length);
			} catch (Exception excp) {
			   //handle error
			} finally {
			    os.close();
			}
		} else {
			response.getWriter().print(Constants.HTTP_RESPONSE_LOGIN_ERROR_BAD_REQUEST);
		}
    }
    
    private byte[] InputSteamToByteArray(InputStream is) throws IOException {
    			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    			int nRead;
    			byte[] data = new byte[16384];

    			while ((nRead = is.read(data, 0, data.length)) != -1) {
    			  buffer.write(data, 0, nRead);
    			}

    			return buffer.toByteArray();
    }
}
