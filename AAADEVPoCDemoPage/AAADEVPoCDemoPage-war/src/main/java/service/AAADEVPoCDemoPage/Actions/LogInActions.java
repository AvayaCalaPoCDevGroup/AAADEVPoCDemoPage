package service.AAADEVPoCDemoPage.Actions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.avaya.collaboration.util.logger.Logger;

import service.AAADEVPoCDemoPage.Beans.UserBean;
//import service.AAADEVPoCDemoPage.HttpLocal.HttpTokenRequest;
import service.AAADEVPoCDemoPage.Http.HttpTokenRequest;
import service.AAADEVPoCDemoPage.Security.AES;
import service.AAADEVPoCDemoPage.Security.XSSPrevent;
import service.AAADEVPoCDemoPage.Util.Constants;
import service.AAADEVPoCDemoPage.Util.LoggerSnapIn;
import service.AAADEVPoCDemoPage.Util.PartToString;

/**
 *
 * @author umansilla
 */
public class LogInActions {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    //private final Logger logger = Logger.getLogger(getClass());

    public LogInActions(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void logOut() throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("UserBeanSession");
        removeCookie(request);
        response.getWriter().println(new JSONObject().put("status", "ok").put("message", "Log OUT"));
    }

    public void register() throws IOException, ServletException {
        //EL USERNAME DEBE IR ENCRIPTADO
        //AL IGUAL QUE EL PASSWORD
    	System.out.println("HttpBreeze -> Login Register request");
    	//logger.info("HttpBreeze -> Login Register request");
        UserBean userBean = new UserBean();
        userBean.setUserName(new AES().encrypt(XSSPrevent.stripXSS(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_USERNAME)))));
        JSONObject json = null;
        try {
        	//logger.info(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_USERNAME)));
        	//logger.info(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_PASSWORD)));
        	//logger.info(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_COUNTRY)));
        	//logger.info(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_CLIENTE)));
            json = new HttpTokenRequest().accessRequest(userBean, 
            		new AES().encrypt(XSSPrevent.stripXSS(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_PASSWORD)))), 
            		new AES().encrypt(XSSPrevent.stripXSS(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_COUNTRY)))),
            		new AES().encrypt(XSSPrevent.stripXSS(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_CLIENTE)))));

        } catch (Exception ex) {
        	System.out.println("Error al solicitar token: " + ex.getMessage());
        	//logger.info("HttpBreeze Error al solicitar token: " + ex.getMessage());
        }
        
        if(json.getInt("code") == 666) {
        	response.setStatus(401);
            response.getWriter().println(json.toString());
            return;
        }
        //USUARIO NO AUTORIZADO
        if (json.getInt("code") != 200) {
            response.setStatus(401);
            response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
        } else {
            if (json.getInt("code") == 200) {
                UserBean userBeanSession = null;
                //Debemos de generar un nuevo Token de Acceso por Refresh
                if (json.has("access_token") && json.getString("access_token").equals("valid")) {
                    try {
                        //DEbemos hacer una nueva petición con el access token para obtener los datos del usuario
                        json = new HttpTokenRequest().validateAccessToken(json.getString("token_access"));
                        if (json.getString("access_token").equals("valid")) {
                            userBeanSession = new UserBean(json.getString("userName"), json.getString("rol"), new AES().encrypt(json.getString("token_access")), new AES().encrypt(json.getString("token_refresh")));
                        } else {
                            response.setStatus(401);
                            response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.toString());
                    }
                } else if (json.has("refresh_token") && json.getString("refresh_token").equals("valid")) {
                    try {
                        json = new HttpTokenRequest().refreshAccessRequest(json.getString("token_refresh"));
                        
                    } catch (Exception ex) {
                       
                    }
                    userBeanSession = new UserBean(XSSPrevent.stripXSS(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_USERNAME))), json.getString("scope"), new AES().encrypt(json.getString("token_access")), new AES().encrypt(json.getString("token_refresh")));
                } else {
                    //TOKENS GENERADOS
                    userBeanSession = new UserBean(XSSPrevent.stripXSS(decryptText(new PartToString(request).getStringValue(Constants.LOGIN_ACTION_USERNAME))), json.getString("scope"), new AES().encrypt(json.getString("token_access")), new AES().encrypt(json.getString("token_refresh")));

                }
                //Crearemos session y redireccionamos
                HttpSession session = request.getSession();
                session.setAttribute("UserBeanSession", userBeanSession);
                //CREACIÓN DE SESSION Y COOKIE
                Cookie cookieTokenAccess = new Cookie("JWT", userBeanSession.getJWTAccess());
                cookieTokenAccess.setMaxAge(-1);  //INDICAMOS QUE LA COOKIE DURE LA SESSIÓN DEL BROWSER
                removeCookie(request);
                /*new LoggerSnapIn(
						Constants.SNAP_IN,
						userBeanSession.getUserName(),
						XSSPrevent.stripXSS(decryptText(new PartToString(
								request)
								.getStringValue(Constants.LOGIN_ACTION_COUNTRY))),
						XSSPrevent.stripXSS(decryptText(new PartToString(
								request)
								.getStringValue(Constants.LOGIN_ACTION_CLIENTE))))
						.LoiIn("Access Snap In");*/
                response.addCookie(cookieTokenAccess);
                response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_OK_AUTHORIZED);
            } else {
                response.setStatus(401);
                response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
            }
        }

    }

    private static String decryptText(String cipherText) {

        String decryptedText = null;
        byte[] cipherData = java.util.Base64.getDecoder().decode(cipherText);
        byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, Constants.SECRET_LOG_IN.getBytes(StandardCharsets.UTF_8), md5);
            SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
            IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

            byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
            Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decryptedData = aesCBC.doFinal(encrypted);
            decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
            System.out.println("LoginActions Decrypt text: " + decryptedText);
            return decryptedText;
        } catch (Exception ex) {
        	System.out.println("LoginActions Decrypt CACTH text: " + decryptedText);
            return decryptedText;
        }
    }

    private static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

        int digestLength = md.getDigestLength();
        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();

            // Repeat process until sufficient data has been generated
            while (generatedLength < keyLength + ivLength) {

                // Digest data (last digest if available, password data, salt if available)
                if (generatedLength > 0) {
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                }
                md.update(password);
                if (salt != null) {
                    md.update(salt, 0, 8);
                }
                md.digest(generatedData, generatedLength, digestLength);

                // additional rounds
                for (int i = 1; i < iterations; i++) {
                    md.update(generatedData, generatedLength, digestLength);
                    md.digest(generatedData, generatedLength, digestLength);
                }

                generatedLength += digestLength;
            }

            // Copy key and IV into separate byte arrays
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
            if (ivLength > 0) {
                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);
            }

            return result;

        } catch (DigestException e) {

            throw new RuntimeException(e);

        } finally {
            // Clean out temporary data
            Arrays.fill(generatedData, (byte) 0);
        }
    }

    private void removeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieName")) {
                    //do something
                    //value can be retrieved using #cookie.getValue()
                    if (cookie.getName().equals("JWT")) {
                        cookie.setMaxAge(0);             //ELIMINAMOS LA COOKIE
                    }
                }
            }
        }

    }

}
