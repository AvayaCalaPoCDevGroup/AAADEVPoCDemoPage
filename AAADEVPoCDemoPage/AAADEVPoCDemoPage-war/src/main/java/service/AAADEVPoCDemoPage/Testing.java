package service.AAADEVPoCDemoPage;

import service.AAADEVPoCDemoPage.Security.AES;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Initializing..");
		String user = "soporte.1";
		System.out.println("User -> " + "DifJ5Utvu7Y2drByFwVGWQ==");
		System.out.println("Encrypt -> " + new AES().decrypt("DifJ5Utvu7Y2drByFwVGWQ=="));
	}

}
