package computerdatabase;

import testframework.WebOperations;

public class ComputerCreationPage  {
	
	private WebOperations _webOps = null;
	
	public ComputerCreationPage( WebOperations webOps ) {
		_webOps = webOps;
	}
	

	public void fillComputerForm( String name, String introDate, String disDate, String company ) {
		if ( name != null ) {
			_webOps.type("txtName", name , false);
		}
		if ( introDate != null ) {
			_webOps.type("txtDateIntro", introDate , false);
		}
		if ( disDate != null ) {
			_webOps.type("txtDateDis", disDate , false);	
		}
		if ( company != null ) {
			_webOps.selectOptionByText("selectCompany", company);
		}
		
	}
	
	public void submitComputerForm() {
		_webOps.click("btnCreateComputer");
	}
	
	public void createComputer(  String name, String introDate, String disDate, String company ) {
		fillComputerForm(name, introDate, disDate, company);
		submitComputerForm();
	}
	
	public ComputerSearchPage cancelCreation( ) {
		_webOps.click("btnCancel");
		return new ComputerSearchPage(_webOps);
	}
	
	public boolean verifyHelpMessage( String helpMessage ) {
		boolean result = false;
		return result;
	}

}
