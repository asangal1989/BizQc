package utility_executer;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import global_utility.global_variables;
import gs_utilities.runner;
import templates.template_testcase;

public class executer extends global_variables{
	reader_writer.excelWriter writer=new reader_writer.excelWriter();
	template_testcase testcase=new template_testcase();
	public void executer_selector(HashMap<String, runner> executable) throws IOException
	{				
		Properties prop=new Properties();
		InputStream in=new FileInputStream(path_lib_properties+"path_testcase.properties");
		prop.load(in);
		
		for(String templete_ID:executable.keySet())
		{
			int Status=0;
			runner gs_runner = executable.get(templete_ID);
			ArrayList<Integer> serial_Number=new ArrayList<Integer>();
			serial_Number.add(gs_runner.getSerialNumber());
			String client = gs_runner.getClientName();
			String url=gs_runner.getTemplateUrl();			
			String templateNumber=templete_ID;
			float tax=gs_runner.getTax();
			String Environment=gs_runner.getEnvironment();
			String Browser=gs_runner.getBrowser();
			boolean flag=gs_runner.getFlag();
			String City=gs_runner.getCity();
			String Address=gs_runner.getAddress();
			testinfo=reports.createTest(client);		
			qc_testinfo=qc_reports.createTest(client);
			log_system.info("******************** Executing Start for "+templateNumber+" ********************************");
			log_system.info("******************** Client Name "+client+" ****************************");
			log_system.info("********************  Open Browser **************************");
			Status=testcase.OpenBrowser(Environment,Browser,client);
			log_system.info("Status of Open Browser "+Status);
			log_system.info("Open URL "+ url);
			Status=testcase.GetURL(url,client);
			log_system.info("Status of get url "+Status);
			log_system.info("********************  Verify SSL ************************ ");
			Status=testcase.VerifySSL(client);
			log_system.info("Status of Verify SSL "+Status);
			log_system.info("********************  Consol Error ************************ ");
			Status=testcase.ConsolError(client);
			log_system.info("Status of Consol Error "+Status);
			log_system.info("********************  Verify Menu ************************ ");
			Status=testcase.VerifyMenu(url,client);
			log_system.info("Status of Verify Menu "+Status);
			log_system.info("********************  Verify Career ************************ ");
			Status=testcase.VerifyCareer(client);
			log_system.info("Status of Verify Career "+Status);
			log_system.info("********************  Verify Gallery ************************ ");			
			Status=testcase.VerifyGallery(client);
			log_system.info("Status of Verify Gallery "+Status);
			log_system.info("********************  Verify Menu Item ************************ ");
			Status=testcase.VerifyMenuItem(client);
			log_system.info("Status of Verify Menu Item "+Status);
			log_system.info("******************** Add Product ************************ ");
			Status=testcase.AddProduct(client);
			log_system.info("Status of Add Product "+Status);
			log_system.info("********************  Verify Add Product ************************ ");
			Status=testcase.AddProduct(client);
			log_system.info("Status of Add Product "+Status);
			log_system.info("********************  Verify Add Product ************************ ");
			Status=testcase.AddProduct(client);
			log_system.info("Status of Add Product "+Status);
			log_system.info("********************  Verify Add Product ************************ ");
			Status=testcase.AddProduct(client);
			log_system.info("Status of Add Product "+Status);
			log_system.info("********************  Verify Summary ************************ ");
			Status=testcase.VerifySummary(client, "ByID", "menu_ordersection", String.valueOf(tax));
			log_system.info("Status of Verify Summary "+Status);
			log_system.info("******************** Add Edit Product ************************ ");
			Status=testcase.AddEditProduct(client);
			log_system.info("Status of Add Edit Product "+Status);
			log_system.info("******************** Add Edit Product ************************ ");
			Status=testcase.AddEditProduct(client);
			log_system.info("Status of Add Edit Product "+Status);
			log_system.info("******************** Add Edit Product ************************ ");
			Status=testcase.AddEditProduct(client);
			log_system.info("Status of Add Edit Product "+Status);
			log_system.info("********************  Verify Summary ************************ ");
			Status=testcase.VerifySummary(client, "ByID", "menu_ordersection", String.valueOf(tax));
			log_system.info("Status of Verify Summary "+Status);
			log_system.info("******************** Remove Edit Product ************************ ");
			Status=testcase.RemoveEditProduct(client);
			log_system.info("Status of Remove Edit Product "+Status);
			log_system.info("******************** Remove Edit Product ************************ ");
			Status=testcase.RemoveEditProduct(client);
			log_system.info("Status of Remove Edit Product "+Status);
			log_system.info("******************** Remove Edit Product ************************ ");
			Status=testcase.RemoveEditProduct(client);
			log_system.info("Status of Remove Edit Product "+Status);
			log_system.info("******************** Verify Summary ************************ ");
			Status=testcase.VerifySummary(client, "ByID", "menu_ordersection", String.valueOf(tax));
			log_system.info("Status of Verify Summary "+Status);
			log_system.info("******************** Delete Product ************************ ");
			Status=testcase.DeleteProduct(client, "NA");
			log_system.info("Status of Delete Product "+Status);
			log_system.info("******************** Delete All Product ************************ ");
			Status=testcase.DeleteProduct(client, "all");
			log_system.info("Status of Delete All Product "+Status);
			log_system.info("******************** Verify Summary ************************ ");
			Status=testcase.VerifySummary(client, "ByID", "menu_ordersection", String.valueOf(tax));
			log_system.info("Status of verify Summary "+Status);
			log_system.info("******************** Reserve A Table ************************ ");
			Status=testcase.ReserveATable(client);
			log_system.info("Status of Reserve A Table "+Status);			
			log_system.info("******************** verify timeslot for reserve a table ************************ ");
			Status=testcase.verifyTimeforReserveATable(client,City,"30");
			log_system.info("Status of Time for Reserve A Table "+Status);
			log_system.info("******************** verify Delivery ************************ ");
			Status=testcase.VerifyDelivery(client,Address);
			log_system.info("Status of Verify Delivery "+Status);	
			log_system.info("******************** Verify Delivery Time ************************ ");
			Status=testcase.VerifyDeliveryTime(client, City,"30");
			log_system.info("Status of Verify Delivery Time "+Status);
			log_system.info("******************** Closing Browser ************************ ");			
			testcase.CloseBrowser(client);			
			log_system.info("Execution Complete for template "+ templateNumber);
			log_system.info("Execution Complete for client "+ client);
			reports.flush();
			qc_reports.flush();
			flag=true;
			writer.updateFlag(path_lib_runner+prop.getProperty("microsite_Runner_FileName"), prop.getProperty("microsite_Runner_SheetName"), serial_Number,flag);
		}
		
	}
}