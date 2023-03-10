package computerdatabase;

import testframework.PageRepository;

public class ComputerDBRepository extends PageRepository {
	
	public ComputerDBRepository()
	{
		super();
		// Home Page Web Components
		setXPathElement("btnFilter", "//input[ @id='searchsubmit']");
		setXPathElement("txtFilter", "//input[ @id='searchbox']");
		setXPathElement("btnAddComputer", "//a[ @id='add']");
		setXPathElement("pagination", "//div[@id='pagination']//li[@class='current']/a");
		setXPathElement("btnNext", "//a[ starts-with( . , 'Next')]");
		setXPathElement("btnPrevious", "//a[ starts-with( . , 'Previous')]");
		setXPathElement("computerLink", "//tbody/tr/td/a[.='${name}']");
		setXPathElement("computerLinks", "//tbody/tr/td/a");
		setXPathElement("alertMessage", "//div[ contains( @class , 'alert-message')]");
		setXPathElement("mainMessage", "//section[@id='main']/h1[position()=1]");
		setXPathElement("tobBarMessage", "//header[@class='topbar']/h1/a");
		setXPathElement("sortByNameLink", "//table/thead/tr/th/a[ contains( . , 'Computer name') ]");
		setXPathElement("sortByIntroducedLink", "//table/thead/tr/th/a[ contains( . , 'Introduced') ]");
		setXPathElement("sortByDiscontinuedLink", "//table/thead/tr/th/a[ contains( . , 'Discontinued') ]");
		setXPathElement("sortByCompanyLink", "//table/thead/tr/th/a[ contains( . , 'Company') ]");
		setXPathElement("panelMessage","//section[@id='main']/div[@class='well']/em");
		
		//Computer Creation Page Web Components
		setXPathElement("txtName", "//input[ @id='name']");
		setXPathElement("txtDateIntro", "//input[ @id='introduced']");
		setXPathElement("txtDateDis", "//input[ @id='discontinued']");
		setXPathElement("selectCompany", "//select[ @id='company']");
		setXPathElement("btnCreateComputer", "//input[ @value='Create this computer']");
		setXPathElement("btnCancel", "//a[ contains( . , 'Cancel') ]");
		setXPathElement("helpMessage", "//span[ @class = 'help-inline' ]");
		
		//Computer Update Page
		setXPathElement("btnSaveComputer", "//input[ @value='Save this computer']");
		setXPathElement("btnDeleteComputer", "//input[ @value='Delete this computer']");
		
	}
	
}
