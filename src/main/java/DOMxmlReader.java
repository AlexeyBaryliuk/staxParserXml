import model.Language;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMxmlReader {

    public static void main(String[] args) {
        String filepath = "xml/languages.xml";
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
            // получаем узлы с именем model.Language
            // теперь XML полностью загружен в память
            // в виде объекта Document
            NodeList nodeList = document.getElementsByTagName("model.Language");

            // создадим из него список объектов model.Language
            List<Language> langList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                langList.add(getLanguage(nodeList.item(i)));
            }

            // печатаем в консоль информацию по каждому объекту model.Language
            for (Language lang : langList) {
                System.out.println(lang.toString());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    // создаем из узла документа объект model.Language
    private static Language getLanguage(Node node) {
        Language lang = new Language();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            lang.setName(getTagValue("name", element));
            lang.setAge(Integer.parseInt(getTagValue("age", element)));
        }

        return lang;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
