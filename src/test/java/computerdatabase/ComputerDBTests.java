package computerdatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testframework.Context;
import testframework.WebOperations;

public class ComputerDBTests {

	private static final String BASE_URL="https://computer-database.gatling.io/computers"; 
	private static Context _context;
	private static WebOperations _webOps;
	private static ComputerSearchPage _searchPage;
	private static ComputerUpdatePage _updatePage;
	
	@BeforeAll
	public static void setup() {
		_context = new Context( new ComputerDBRepository(), Context.Browser.CHROME, true );
		_webOps = _context.getWebOperations();
		_webOps.goToURL(BASE_URL);
		_webOps.maximizeBrowserWindow();
		_searchPage = new ComputerSearchPage( _webOps );
	}
	
	@AfterAll
	public static void tearDown() {
		_context.quitActiveDriver();
	}
	
	
	@Test
	public void searchComputer()  {
		_searchPage.searchComputersByNameContaining("Apple");
		assertEquals( "13 computers found" , _searchPage.getCountingMessage());
		assertTrue( _searchPage.isComputerInActivePage("Apple Lisa"));
		assertEquals("Apple Lisa", _searchPage.getComputerAtRow(10));
		_updatePage = _searchPage.goToComputerUpdatePage("Apple Lisa");
		assertEquals( "Edit computer" , _webOps.getText("mainMessage"));
		_updatePage.submitComputerForm();
	}

}
