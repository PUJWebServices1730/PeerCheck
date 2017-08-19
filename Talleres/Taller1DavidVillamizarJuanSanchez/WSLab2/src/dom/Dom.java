package dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Dom {

	public static String pathXML = "./resources/casas.xml";

	public static void main(String[] args) throws Exception {
		crearCatalogo();
	}

	public static void crearCatalogo() throws Exception {
		//Inicializar la fabrica de DOM
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		//Crear un nuevo documento en memoria
		Document document = db.newDocument();

		//Crear el elemento root
		Element catalogo = document.createElement("catalogo");
		
		//Inicializar arreglos con los datos
		Element[] articulos = new Element[4];
		Element[] precios = new Element[4];
		String precios_txt[] = {"4000","5","8000","20"};
		String nombres_txt[] = {"jabon","cepillo","Shampoo","crema humectante"};
		String monedas_txt[] = {"peso","dollar",null,"euro"};

		//Crear y agregar los artículos con sus atributos
		for (int i = 0; i < articulos.length; ++i) {
			articulos[i] = document.createElement("articulo");
			articulos[i].setAttribute("nombre", nombres_txt[i]);
			catalogo.appendChild(articulos[i]);
		}
		
		//Crear y agregar los precios con sus atributos y texto
		for (int i = 0; i < precios.length; ++i) {
			precios[i] = document.createElement("precio");
			if (monedas_txt[i] != null) {
				precios[i].setAttribute("moneda", monedas_txt[i]);
			}
			precios[i].appendChild(document.createTextNode(precios_txt[i]));
			articulos[i].appendChild(precios[i]);
		}
		
		//Agregar catalogo armado al documento
		document.appendChild(catalogo);

		//Escribir documento en archivo
		XMLTools.writeXML(document, "./catalogo.xml", null);
	}
}
