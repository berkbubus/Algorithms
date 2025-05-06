import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class MissionGroundwork {

    /**
     * Given a list of Project objects, prints the schedule of each of them.
     * Uses getEarliestSchedule() and printSchedule() methods of the current project to print its schedule.
     * @param projectList a list of Project objects
     */
    public void printSchedule(List<Project> projectList) {
        for(Project k:projectList){
            k.printSchedule(k.getEarliestSchedule());
        }
    }

    /**
     * TODO: Parse the input XML file and return a list of Project objects
     *
     * @param filename the input XML file
     * @return a list of Project objects
     */
    public List<Project> readXML(String filename) {
        List<Project> projectList = new ArrayList<>();
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
        NodeList projects = doc.getElementsByTagName("Project");
        for(int i=0; i<projects.getLength(); i++)
        {
            Element tasks =(Element) projects.item(i);
            String projectName = tasks.getElementsByTagName("Name").item(0).getTextContent();
            NodeList allTasks = tasks.getElementsByTagName("Task");
            List<Task> listTasks = new ArrayList<>();
            for(int j=0; j<allTasks.getLength(); j++){
                Element task = (Element) allTasks.item(j);
                int id = Integer.parseInt(task.getElementsByTagName("TaskID").item(0).getTextContent());
                String description = task.getElementsByTagName("Description").item(0).getTextContent();
                int duration = Integer.parseInt(task.getElementsByTagName("Duration").item(0).getTextContent());
                List<Integer> dependencies = new ArrayList<>();
                NodeList allDep = task.getElementsByTagName("Dependencies");
                for(int k=0;k< allDep.getLength();k++){
                    if(!allDep.item(k).getTextContent().equals("")){
                        Element f =(Element) allDep.item(k);
                        NodeList last = f.getElementsByTagName("DependsOnTaskID");
                        for(int l=0;l<last.getLength();l++){
                            dependencies.add(Integer.parseInt(last.item(l).getTextContent()));
                        }
                    }
                }
                Task adder = new Task(id,description,duration,dependencies);
                listTasks.add(adder);
            }
            Project adder = new Project(projectName,listTasks);
            projectList.add(adder);
        }
        return projectList;
    }
}
