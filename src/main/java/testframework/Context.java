package testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Context {
	
	private Repository _repository;
	private WebDriver _driver = null;
	public static enum Browser { CHROME, FIREFOX, EDGE }
	public Browser _currentBrowser;
	
	public Context( Repository repository, Browser browser, boolean autoStart ) {
		_repository = repository;
		_currentBrowser = browser;
		if ( autoStart ) {
			startNewBrowser();
		}
	}
	
	static {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
	}
	
	private void newChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		_driver =  new ChromeDriver( options );	
	}
	
	private void newFirefoxDriver() {
		_driver = new FirefoxDriver();
		
	}

	private void newEdgeDriver() {
		_driver = new EdgeDriver();
		
	}
	
	public void startNewBrowser() {
		switch( _currentBrowser ) {
		case CHROME  :
			newChromeDriver();
			break;
		case FIREFOX :
			newFirefoxDriver();
			break;
		case EDGE    :
			newEdgeDriver();
			break;
		}
	}
	
	public void closeActiveDriver() {
		if ( _driver != null ) {
			try {
				_driver.close();
			}
			catch( Exception e ) {
				
			}
		}	
	}
	
	
	public void quitActiveDriver() {
		if ( _driver != null ) {
			try {
				_driver.quit();
			}
			catch( Exception e ) {
				
			}
		}	
	}
	
	
	public WebOperations getWebOperations() {
		return new WebOperations( _repository , _driver );
	}

	
}
