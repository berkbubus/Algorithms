import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {
        Map map = new HashMap<String,Malware>();
        File file = new File(filename);
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = fac.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NodeList malwares = doc.getElementsByTagName("row");
        for(int i=0; i<malwares.getLength(); i++)
        {
            Node malware = malwares.item(i);
            Element virus = (Element) malware;
            String title = virus.getElementsByTagName("title").item(0).getTextContent();
            String hash = virus.getElementsByTagName("hash").item(0).getTextContent();
            int level = Integer.parseInt(virus.getElementsByTagName("level").item(0).getTextContent());
            Malware corona = new Malware(title,level,hash);
            map.put(hash,corona);
        }
        return map;
    }
}
