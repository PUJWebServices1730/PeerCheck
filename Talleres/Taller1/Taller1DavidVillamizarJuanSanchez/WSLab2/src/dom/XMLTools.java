package dom;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLTools {

	public static void writeXML(Document document, String nameFile, String DTDFile) 
	{
		try
		{
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer();
		    Properties properties = new Properties();
		    properties.put("method", "xml");
		    properties.put("encoding", "UTF-8"); 
		    properties.put("indent", "yes");
		    
		    if (DTDFile != null)
		    	properties.put(OutputKeys.DOCTYPE_SYSTEM,DTDFile);
		    
		    transformer.setOutputProperties(properties);
		    Source input = new DOMSource(document);
		    File archivo = new File(nameFile);
		    FileOutputStream fos = new FileOutputStream(archivo);

		    if(fos != null) 
		    {
		      Result sortie = new StreamResult(fos);
		      transformer.transform(input, sortie);
		    }
		    
		    fos.flush();
		    fos.close();
		  }
		  catch (FileNotFoundException e) {
		    e.printStackTrace();
		  }
		  catch (FactoryConfigurationError e) {
		    e.printStackTrace();
		  }
		  catch (TransformerException e) {
		    e.printStackTrace();
		  }
		  catch (IOException e) {
		    e.printStackTrace();
		  }
		}
	}
