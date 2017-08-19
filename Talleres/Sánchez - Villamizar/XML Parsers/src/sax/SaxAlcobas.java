package sax;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SaxAlcobas extends DefaultHandler {
	/**
	 * La ubicación del archivo XML con las casas
	 */
	public static String pathXML = "./resources/casas.xml";
	
	/**
	 * Los ids de cada casa
	 */
	public static List<String> ids = new ArrayList<>();
	
	/**
	 * El número de habitaciones para cada casa
	 */
	public static List<Integer> habitaciones = new ArrayList<>();
	
	public SaxAlcobas ()
	{
		super();
	}
	
	public static void main (String args[]) throws Exception	
	{
		XMLReader xr = XMLReaderFactory.createXMLReader();
		SaxAlcobas handler = new SaxAlcobas();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
			
		FileReader fileReader = new FileReader(pathXML);
		xr.parse(new InputSource(fileReader));
	}

	@Override
	public void startDocument() throws SAXException {
		//Imprimir encabezado
		System.out.println("Casas:");
		System.out.println("------");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals("casa")) {
			//Agregar nueva posición en la lista de números de habitaciones
			habitaciones.add(0);
			
			//Guardar el id de la casa
			ids.add(attributes.getValue("id"));
		}
		
		if (localName.equals("habitacion")) {
			//Contar una habitación más en la última casa agregada
			int pos = habitaciones.size() - 1;
			int value = habitaciones.get(pos);
			habitaciones.set(pos, ++value);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (localName.equals("casa")) {
			//Imprimir número de habitaciones de la casa que termina
			int pos = ids.size() - 1;
			System.out.println("Casa " + ids.get(pos) + ": " + habitaciones.get(pos) + " habitaciones");
		}
	}
	
}
