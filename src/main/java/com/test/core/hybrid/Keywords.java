package com.test.core.hybrid;

import com.test.core.hybrid.util.Xls_Reader;

public class Keywords {
	
	
	public static void main(String args[]){
		
		
		/*
		System.out.println("Directory is" + System.getProperty("user.dir"));
		
		GenericKeywords app = new GenericKeywords();
		app.openBrowser("Chrome");
		app.navigate("url");
		app.click("gmailLink_xpath");
		app.click("signin_xpath");
		app.input("email_id", "vikas.dhir10");
		app.click("nextButton_id");
		*/
		String testUnderExecution = "GmailTest";
		GenericKeywords app = new GenericKeywords();
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"//data//SuiteA.xlsx");
		int rows = xls.getRowCount("Keywords");
		System.out.println(rows);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String tcid = xls.getCellData("Keywords", "TC ID", rNum);
			String keyword = xls.getCellData("Keywords", "Keyword", rNum);
			String object = xls.getCellData("Keywords", "Object", rNum);
			String data = xls.getCellData("Keywords", "Data", rNum);
			System.out.println(tcid+"---"+keyword+"---"+object+"---"+data);
		}
		
		
	}
	
	
	

}
