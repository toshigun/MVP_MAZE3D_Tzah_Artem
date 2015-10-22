package presenter;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Properties implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String lastChanged;
	int justChecking;
	
	public Properties(){
		this.lastChanged = ""+System.currentTimeMillis();
		this.justChecking = 1;
		
	}
	
	public Properties(String lastChanged, int justChecking) {
		
		this.lastChanged = lastChanged;
		this.justChecking = justChecking;
	}
	
	
	public String getLastChanged() {
		return lastChanged;
	}
	@XmlElement
	public void setLastChanged(String lastChanged) {
		this.lastChanged = lastChanged;
	}
	
	public int getJustChecking() {
		return justChecking;
	}
	
	@XmlElement
	public void setJustChecking(int justChecking) {
		this.justChecking = justChecking;
	}
	
	
	
	
	
	
	
}
