package presenter;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PropertiesCenter {
	
	Properties p;
	
	public void setToXMLFile(Properties p){
		
		File file = new File("properties.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Properties.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(p, file);
			jaxbMarshaller.marshal(p, System.out);
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public Properties fetchPropertiesFromFile(File f){
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Properties.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Properties p = (Properties) jaxbUnmarshaller.unmarshal(f);
			return p;
		

		  } catch (JAXBException e) {
			  
			e.printStackTrace();
			return new Properties();
		  }

		
		
	}
	
	
}
