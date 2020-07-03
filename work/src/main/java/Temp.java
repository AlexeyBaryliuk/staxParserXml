import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Temp {

    private final static String zipDir   = "xml";
    private final static String zipFile  = "test_zip.zip";
// /home/alexey/IdeaProjects/stax/stax_git_new/staxParserXml
//    private final String SLASH_BACK      = "/";
//    private final String CHARSET_UTF_8   = "UTF-8";

    public static void main(String[] args) {

try (
    ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
    ByteArrayOutputStream baos = new ByteArrayOutputStream()
) {
        ZipEntry entry;
        String name;
        long size;
        while ((entry = zin.getNextEntry()) != null) {
            copyData(zin, baos);
            name = entry.getName(); // получим название файла
            size = entry.getSize(); // получим его размер в байтах
            System.out.printf("Название: %s \t размер: %d \n", name, size);

        }
    } catch (Exception ex) {

        System.out.println(ex.getMessage());
    }
}

    private static void copyData(InputStream in, OutputStream out) throws Exception {
Integer count = 0;
        while (in.available() > 0) {
            count++;
            System.out.println("++++++++++++++++ = " + in.available() + "   Count = " + count);
            out.write(in.read());

        }
    }
}





















//    public static void main(String[] args) {
//
//
//        System.out.println("-- = " + zipFile);
//        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile))) {
//            System.out.println("++ = " + zin.read());
//            ZipEntry entry;
//            String name;
//            long size;
//            while ((entry = zin.getNextEntry()) != null) {
//
//                name = entry.getName(); // получим название файла
//                size = entry.getSize();  // получим его размер в байтах
//                System.out.printf("File name: %s \t File size: %d \n", name, size);
//
//                // распаковка
//                FileOutputStream fout = new FileOutputStream("xml/new" + name);
//                for (int c = zin.read(); c != -1; c = zin.read()) {
//                    fout.write(c);
//                }
//                fout.flush();
//                zin.closeEntry();
//                fout.close();
//            }
//        } catch (Exception ex) {
//
//            System.out.println(ex.getMessage());
//        }
//    }
//}

