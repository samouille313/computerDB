package testframework;

public interface Repository {
	public void setXPathElement( String element, String xpath );
	public String getXPathElement( String element );
	public void setVar( String variable, String value );
	public String getValue( String variable );
}
