package service.AAADEVPoCDemoPage.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import service.AAADEVPoCDemoPage.Entity.Collaterals;
import service.AAADEVPoCDemoPage.Entity.Components;
import service.AAADEVPoCDemoPage.Entity.Demo;
import service.AAADEVPoCDemoPage.Service.AaaDevPoCDemoPageImpl;
import service.AAADEVPoCDemoPage.Util.Constants;
import service.AAADEVPoCDemoPage.Util.ConstantsHttpResponse;

/**
 *
 * @author umansilla
 */
@WebServlet(name = "SnapIns", urlPatterns = {"/SnapIns"})
public class SnapIns extends HttpServlet {
    

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        List<Demo> demos = new AaaDevPoCDemoPageImpl().obtenerTodoslosDemos();
        for (Demo demo : demos) {
        	
            JSONObject jsonObject = new JSONObject(new Gson().toJson(demo));
            List<Components> componentes = new AaaDevPoCDemoPageImpl().obtenerComponentesPorIdDemo(Long.toString(demo.getId()));
            jsonObject.put("componentDetails", new JSONArray(new Gson().toJson(componentes)));
            List<Collaterals> collaterals = new AaaDevPoCDemoPageImpl().obtenerTodosLosCollateralsPorIdDemo(Long.toString(demo.getId()));
            jsonObject.put("collaterals", new JSONArray(new Gson().toJson(collaterals)));
            jsonArray.put(jsonObject);
            
        }
        response.getWriter().println(jsonArray.toString());
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        try {
            
        } catch (Exception e) {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_500(e.toString()));
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
