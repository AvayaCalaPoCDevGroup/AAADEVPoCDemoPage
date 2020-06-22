package service.AAADEVPoCDemoPage.Util;

/**
*
* @author umansilla
*/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.avaya.collaboration.util.logger.Logger;

public class LoggerSnapIn {
   private final Logger logger = Logger.getLogger(getClass());
   private final FileWriter fichero;
   private final PrintWriter pw;
   private String snapIn;
   private String user;
   private String country;
   private String customer;

   public LoggerSnapIn(String snapIn, String user, String country, String customer) throws IOException {
       this.fichero = new FileWriter(Constants.ACCESS_LOGS, true);
       this.pw = new PrintWriter(fichero);
       this.snapIn = snapIn;
       this.user = user;
       this.country = country;
       this.customer = customer;
       init();
   }

   public LoggerSnapIn(String snapIn, String user) throws IOException {
       this.fichero = new FileWriter(Constants.ACCESS_LOGS, true);
       this.pw = new PrintWriter(fichero);
       this.snapIn = snapIn;
       this.user = user;
       init();
   }

   private void init() {
       final File folderWeb = new File(Constants.WEB_FOLDER);
       validateIfFolderExists(folderWeb);
       final File folderWav = new File(Constants.LOG_IN_FOLDER);
       validateIfFolderExists(folderWav);
   }

   public void LoiIn(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_" + country + "_" + customer + "_LogIn_" + body);
       fichero.close();
   }

   public void ERROR(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_ERROR_" + body);
       fichero.close();
   }

   public void FATAL(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_FATAL_" + body);
       fichero.close();
   }

   public void WARN(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_WARN_" + body);
       fichero.close();
   }

   public void INFO(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_INFO_" + body);
       fichero.close();
   }

   public void FINE(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_FINE_" + body);
       fichero.close();
   }

   public void FINER(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_FINER_" + body);
       fichero.close();
   }

   public void FINEST(String body) throws IOException {
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date(System.currentTimeMillis());
       pw.println(df.format(date) + "_" + snapIn + "_" + user + "_FINEST_" + body);
       fichero.close();
   }

   private void validateIfFolderExists(final File folder) {
       boolean result = false;
       if (!folder.exists()) {
           try {
               folder.mkdir();
               result = true;
           } catch (SecurityException se) {
               logger.error(se.toString());
           }
           if (result) {
               logger.info("directory "+folder.getName()+" created");
           }
       }
   }
}
