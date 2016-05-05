package pl.dom133.dzwonek;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FileXML {

    Application app;

    public FileXML(Application app) {this.app = app;}

    public void createXML() {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Dzwonek");
            doc.appendChild(rootElement);
            // staff elements
            Element list = doc.createElement("List");
            rootElement.appendChild(list);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File(app.getFilesDir(), "list.xml"));
            transformer.transform(source, result);


        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }catch(TransformerException tfe){
            tfe.printStackTrace();
        }
    }

    public String readXML(int value) throws IOException, SAXException, ParserConfigurationException {
            File file = new File(app.getFilesDir(), "list.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            return document.getElementsByTagName("List").item(value).getTextContent();
    }
    public void editXml(String id, String od_time, String do_time)
    {
        try {
            String filepath = app.getFilesDir().getPath()+"/list.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node company = doc.getFirstChild();

            // Get the staff element , it may not working if tag has spaces, or
            // whatever weird characters in front...it's better to use
            // getElementsByTagName() to get it directly.
            // Node staff = company.getFirstChild();

            // Get the staff element by tag name directly
            Node staff = doc.getElementsByTagName("staff").item(0);

            // update staff attribute
            NamedNodeMap attr = staff.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            nodeAttr.setTextContent("2");

            // append a new node to staff
            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode("28"));
            staff.appendChild(age);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }

    public int getNumber() throws IOException, SAXException, ParserConfigurationException {
        File file = new File(app.getFilesDir(), "list.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        return document.getElementsByTagName("List").getLength();
    }
}

