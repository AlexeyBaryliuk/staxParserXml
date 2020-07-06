import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveByte {
    private final static String zipDir = "xml/projects.xml";
    private final static String zipFile = "test_zip2.zip";


    public static void main(String[] args) throws IOException {

        byte[] bytes = Files.readAllBytes(Paths.get(zipDir));


        File file = new File("projects.xml");

        try {

            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            System.out.println("Write bytes to file.");
//            printContent(file);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        FileOutputStream zipFile = new FileOutputStream("archive.zip");
        ZipOutputStream zip = new ZipOutputStream(zipFile);
        zip.putNextEntry(new ZipEntry("languages.xml"));
        Files.copy(file.toPath(), zip);

// закрываем архив
        zip.close();

    }
}
