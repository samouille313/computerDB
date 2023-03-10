package testframework;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class WebOperations {

	private static int ENABLE_TIMEOUT_SEC = 3;
	private static int POLLING_PERIOD_MS = 100;
	private Repository _repository;
	private WebDriver _driver;

	public WebOperations(Repository repository, WebDriver driver) {
		_driver = driver;
		_repository = repository;
	}

	public WebDriver getWebDriver() {
		return _driver;
	}

	public WebElement waitAndFindEnabledElement(String component) {
		String xpath = _repository.getXPathElement(component);
		//System.out.println("xpath="+xpath);
		WebElement we = null;
		try {
			FluentWait<WebDriver> wait = new FluentWait<>(_driver);
			wait.withTimeout(Duration.ofSeconds(ENABLE_TIMEOUT_SEC)).ignoring(NoSuchElementException.class)
					.pollingEvery(Duration.ofMillis(POLLING_PERIOD_MS)).until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver driver) {
							return driver.findElement(By.xpath(xpath)).isEnabled();
						}
					});
			we = _driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			System.err.println("Cannot find web component with xpath=" + xpath);
		}
		return we;
	}

	public WebElement waitForVisibilityOfElement(String component) {
		String xpath = _repository.getXPathElement(component);
		WebElement we = null;
		try {
			FluentWait<WebDriver> wait = new FluentWait<>(_driver);
			wait.withTimeout(Duration.ofSeconds(ENABLE_TIMEOUT_SEC)).ignoring(NoSuchElementException.class)
					.pollingEvery(Duration.ofMillis(POLLING_PERIOD_MS))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			we = _driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
		}
		return we;
	}

	public WebElement waitForInvisibilityOfElement(String component) {
		String xpath = _repository.getXPathElement(component);
		WebElement we = null;
		try {
			FluentWait<WebDriver> wait = new FluentWait<>(_driver);
			wait.withTimeout(Duration.ofSeconds(ENABLE_TIMEOUT_SEC)).ignoring(NoSuchElementException.class)
					.pollingEvery(Duration.ofMillis(POLLING_PERIOD_MS))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			we = _driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
		}
		return we;
	}

	public List<WebElement> getElements(String components) {
		String xpath = "";
		List<WebElement> elementsList = null;
		try {
			xpath = _repository.getXPathElement(components);
			elementsList = _driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			System.err.println("Cannot find web component(s) with xpath=" + xpath);
		}
		return elementsList;
	}

	public void setRepository(Repository repository) {
		_repository = repository;
	}

	public Repository getRepository() {
		return _repository;
	}

	public void click(String component) {
		waitAndFindEnabledElement(component).click();
	}

	public void doubleClick(String component) {
		Actions actions = new Actions(_driver);
		actions.doubleClick(waitAndFindEnabledElement(component)).build().perform();
	}

	public void rightClick(String component) {
		Actions actions = new Actions(_driver);
		actions.contextClick(waitAndFindEnabledElement(component)).build().perform();
	}

	public void type(String component, String text, boolean clearTextBefore) {
		if (clearTextBefore) {
			waitAndFindEnabledElement(component).clear();
		}
		waitAndFindEnabledElement(component).sendKeys(text);
	}

	public void selectOptionByText(String component, String text) {
		Select list = new Select(waitAndFindEnabledElement(component));
		list.selectByVisibleText(text);
	}

	public boolean isComponentTextEqualsTo(String component, String text) {
		return waitAndFindEnabledElement(component).getText().equals(text);
	}


	public void goToURL(String url) {
		_driver.get(url);
	}

	public void maximizeBrowserWindow() {
		_driver.manage().window().maximize();
	}

	public void waitFor(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {

		}
	}

	public String getText(String component) {
		String text = null;
		WebElement we = waitForVisibilityOfElement(component);
		if (we != null) {
			text = we.getText();
		}
		return text;
	}

	public boolean isTextPresentOnPage(String text, String... tags) {
		if (tags.length == 0) {
			return _driver.findElements(By.xpath("//*[ contains( . , '" + text + "')]")).size() > 0;
		} else
			for (String tag : tags) {
				if (_driver.findElements(By.xpath("//" + tag + "[ contains( . , '" + text + "')]")).size() > 0) {
					return true;
				}
			}
		return false;
	}
	
	public String gatAttributeValue( String component, String attributeName ) {
		String value = null;
		try {
			value = waitForVisibilityOfElement(component).getAttribute(attributeName);
		}
		catch ( Exception e ) {}
		return value;
	}
	
	public boolean isComponentDisplayedNow( String component ) {
		String xpath = _repository.getXPathElement(component);
		boolean result = false;
		try {
		  result = _driver.findElement(By.xpath(xpath)).isDisplayed();
		} catch( Exception e ) {
			
		}
		return result;
	}
	
	
	public boolean isComponentEnabledNow( String component ) {
		String xpath = _repository.getXPathElement(component);
		boolean result = false;
		try {
		  result = _driver.findElement(By.xpath(xpath)).isEnabled();
		} catch( Exception e ) {
			
		}
		return result;
	}
	
	
}
