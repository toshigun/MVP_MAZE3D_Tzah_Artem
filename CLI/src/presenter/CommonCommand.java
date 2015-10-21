package presenter;


/**
 * @author tzah and Artem
 *	Abstract class for command classes
 */
public abstract class CommonCommand implements Command{

	//ensures .bin extension just for file name which have not been provided with
	protected String ensureEx(String s){
		
		if(s.endsWith(".bin"))
			return s;
		else
			return s+".bin";
	}

}
