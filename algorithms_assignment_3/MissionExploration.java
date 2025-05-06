import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MissionExploration {

    /**
     * Given a Galaxy object, prints the solar systems within that galaxy.
     * Uses exploreSolarSystems() and printSolarSystems() methods of the Galaxy object.
     *
     * @param galaxy a Galaxy object
     */
    public void printSolarSystems(Galaxy galaxy) {
        galaxy.printSolarSystems(galaxy.exploreSolarSystems());
    }

    /**
     * TODO: Parse the input XML file and return a list of Planet objects.
     *
     * @param filename the input XML file
     * @return a list of Planet objects
     */
    public Galaxy readXML(String filename) {
        List<Planet> planetList = new ArrayList<>();
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
        NodeList planets = doc.getElementsByTagName("Planet");
        for(int i=0; i<planets.getLength(); i++)
        {
            List<String> neighbors = new ArrayList<>();
            Element planet = (Element) planets.item(i);
            for(int j =0;j<planet.getElementsByTagName("PlanetID").getLength();j++){
                neighbors.add(planet.getElementsByTagName("PlanetID").item(j).getTextContent());
            }
            Planet planet1 = new Planet(planet.getElementsByTagName("ID").item(0).getTextContent(),Integer.parseInt(planet.getElementsByTagName("TechnologyLevel").item(0).getTextContent()),neighbors);
            planetList.add(planet1);
        }
        return new Galaxy(planetList);
    }
}
