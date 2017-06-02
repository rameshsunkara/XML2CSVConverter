package svknmrr;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

public class XML2Csv {

	public static void main(String args[]) throws Exception {

		ClassLoader classLoader = new XML2Csv().getClass().getClassLoader();
		File xmlSource = new File(classLoader.getResource("data.xml").getFile());
		File stylesheet = new File(classLoader.getResource("style.xsl").getFile());

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlSource);

		StreamSource stylesource = new StreamSource(stylesheet);
		Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
		Source source = new DOMSource(document);
		StringWriter sw = new StringWriter();
		Result outputTarget = new StreamResult(sw);
		transformer.transform(source, outputTarget);
		System.out.println(sw.toString());
	}
	
	/*
	 * try {
	    StringReader reader = new StringReader("<xml>blabla</xml>");
	    StringWriter writer = new StringWriter();
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer(
	            new javax.xml.transform.stream.StreamSource("styler.xsl"));
	
	    transformer.transform(
	            new javax.xml.transform.stream.StreamSource(reader), 
	            new javax.xml.transform.stream.StreamResult(writer));
	
	    String result = writer.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	 */
}
