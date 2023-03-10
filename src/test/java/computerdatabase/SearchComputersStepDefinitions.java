package computerdatabase;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import testframework.Context;
import testframework.WebOperations;
import static org.junit.jupiter.api.Assertions.*;


public class SearchComputersStepDefinitions {
	
	private static final String BASE_URL="https://computer-database.gatling.io/computers"; 
	private static Context _context;
	private static WebOperations _webOps;
	private static ComputerSearchPage _searchPage;
	
	private static final int DEMO_SPEED_PERIOD = 1000;
	private static final int TEST_SPEED_PERIOD = 0;
	
	private static int SPEED_PERIOD_MS = TEST_SPEED_PERIOD;
	
	@Given("I load the computer database homepage")
	public void i_load_the_computer_database_homepage() {
		_webOps.goToURL(BASE_URL);
		_searchPage = new ComputerSearchPage( _webOps );
		_webOps.waitFor(SPEED_PERIOD_MS);
	}

	@When("I search computers with {string} keyword")
	public void i_search_computers_with_keyword(String keyword) {
	   _searchPage.searchComputersByNameContaining(keyword);
	   _webOps.waitFor(SPEED_PERIOD_MS);
	}

	@Then("I should see a list of computer names all containing {string}")
	public void i_should_see_a_list_of_computer_names_all_containing(String keyword) {
	    assertTrue(_searchPage.areAllComputerNamesInPageContains( keyword ));
	    _webOps.waitFor(SPEED_PERIOD_MS);
	}

	@Then("I should see {string} as counting message")
	public void i_should_see_as_counting_message(String message ) {
	    assertEquals( message, _searchPage.getCountingMessage());
	    _webOps.waitFor(SPEED_PERIOD_MS);
	}


	@Then("I should see only one computer named {string}")
	public void i_should_see_only_one_computer_named(String name) {
		// Si on considère qu'on a pas besoin d'un mot clé alors
		// on peut utiliser directement la couche technique _webOps
		assertTrue( _webOps.getElements("computerLinks").size() == 1 );
	    assertTrue( _searchPage.isComputerInActivePage(name) );
	    _webOps.waitFor(SPEED_PERIOD_MS);
	}

	@Then("I should see {string} as pagination label")
	public void i_should_see_as_pagination_label(String message) {
	    assertEquals( message , _webOps.getText("pagination"));
	    _webOps.waitFor(SPEED_PERIOD_MS);
	}
	
	@Then("I should see {string} in the message panel")
	public void i_should_see_in_the_message_panel(String message) {
		assertEquals( message , _webOps.getText("panelMessage"));
		_webOps.waitFor(SPEED_PERIOD_MS);
	}
	
	
	@Then("I should be able to find the {string} computer using next link")
	public void i_should_be_able_to_find_the_computer_using_next_link(String computerName ) {
	    assertTrue( _searchPage.canFindComputerByNameUsingNextPage(computerName));
	    _webOps.waitFor(SPEED_PERIOD_MS);
	}

	@Then("I should see {string} as first computer in the list")
	public void i_should_see_as_first_computer_in_the_list(String computerName ) {
	    assertEquals( computerName, _searchPage.getComputerAtRow(1));
	    _webOps.waitFor(SPEED_PERIOD_MS);
	}

	@When("I sort the current list by name")
	public void i_sort_the_current_list_by_name() {
		_searchPage.sortListByName();
		_webOps.waitFor(SPEED_PERIOD_MS);
	}
	
	// On utilise les annotations Cucumber JUNIT Like !
	@BeforeAll
	public static void setup() {
		_context = new Context( new ComputerDBRepository(), Context.Browser.CHROME, true );
		_webOps = _context.getWebOperations();
		_webOps.maximizeBrowserWindow();
	}
	
	@AfterAll
	public static void tearDown() {
		_context.quitActiveDriver();
	}
	
	
}
