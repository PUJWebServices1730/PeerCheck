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

public class SaxArea extends DefaultHandler {
	/**
	 * La ubicación del archivo XML con las casas
	 */
	public static String pathXML = "./resources/casas.xml";
	
	/**
	 * Los ids de cada casa
	 */
	public static List<String> ids = new ArrayList<>();
	
	/**
	 * Las áreas de cada casa
	 */
	public static List<Float> areas = new ArrayList<>();

	public SaxArea() {
		super();
	}

	public static void main(String args[]) throws Exception {
		XMLReader xr = XMLReaderFactory.createXMLReader();
		SaxArea myHandler = new SaxArea();
		xr.setContentHandler(myHandler);
		xr.setErrorHandler(myHandler);

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
			//Agregar nueva posición en la lista de áreas
			areas.add(0f);
			
			//Guardar el id de la casa
			ids.add(attributes.getValue("id"));
		} else {
			//Obtener el área de este elemento, si tiene
			String area_txt = attributes.getValue("superficie-m2");
			if (area_txt != null) {
				//Agregar el área de este elemento al área total de esta casa
				float area = Float.parseFloat(area_txt);
				int pos = ids.size() - 1;
				float value = areas.get(pos) + area;
				areas.set(pos, value);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("casa")) {
			//Imprimir el área total de la casa que termina
			int pos = ids.size() - 1;
			System.out.println("Casa " + ids.get(pos) + ": " + areas.get(pos) + " m2");
		}
	}
}
