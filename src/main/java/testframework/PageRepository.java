package testframework;

import java.util.HashMap;
import java.util.Map;

public class PageRepository implements Repository {
	
	private Map<String,String> components = new HashMap<>();
	private Map<String,String> variables = new HashMap<>();
	
	@Override
	public void setXPathElement(String element, String xpath) {
		components.put( element , xpath);
	}

	@Override
	public String getXPathElement(String element) {
		String xpath = components.get( element ); 
		int posVar = xpath.indexOf("${"); 
		if ( posVar >= 0 ) {
			for( String var : variables.keySet() ) {
				xpath = xpath.replace("${"+var+"}", variables.get(var));
			}
		}
		return xpath;
	}
	
	

	@Override
	public void setVar(String variable, String value) {
		variables.put( variable, value );
	}

	@Override
	public String getValue(String variable) {
		return variables.get( variable );
	}

	
}
