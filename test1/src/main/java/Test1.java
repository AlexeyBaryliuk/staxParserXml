import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import model.Projects;


import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public void writeToXml(List<Projects> projects) throws IOException, XMLStreamException {

        String PATH_TO_PROJECTS = "xml/projects.xml";

        File file = new File(PATH_TO_PROJECTS);

        try (OutputStream os = Files.newOutputStream(file.toPath())) {
            XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = null;
            try {
                writer = outputFactory.createXMLStreamWriter(os, "utf-8");
                writeProjectsElem(writer, projects);
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }

    private void writeProjectsElem(XMLStreamWriter streamWriter, List<Projects> projects) throws XMLStreamException {

        IndentingXMLStreamWriter writer = null;
        writer = new IndentingXMLStreamWriter(streamWriter);

        writer.writeStartDocument("utf-8", "1.0");
//        writer.writeComment("Describes list of projects");

        writer.writeStartElement("projects");
        for (Projects project : projects)
            writeProjectElem(writer, project);
        writer.writeEndElement();

        writer.writeEndDocument();
    }

    private void writeProjectElem(XMLStreamWriter writer, Projects project) throws XMLStreamException {
        writer.writeStartElement("project");
        writer.writeAttribute("ProjectId", project.getProjectId().toString());

        writer.writeStartElement("description");
        writer.writeCharacters(project.getDescription());
        writer.writeEndElement();

        writer.writeStartElement("dateAdded");
        System.out.println(project.getDateAdded().toString());
        writer.writeCharacters(project.getDateAdded().toString());
        writer.writeEndElement();

        writer.writeEndElement();
    }

    public static void main(String[] args) throws IOException, XMLStreamException {
        List<Projects> projects = new ArrayList<>();

        projects.add(new Projects(1, "2", LocalDate.now()));
        projects.add(new Projects(2, "3", LocalDate.now()));

        Test1 test1 = new Test1();
        test1.writeToXml(projects);
    }
}