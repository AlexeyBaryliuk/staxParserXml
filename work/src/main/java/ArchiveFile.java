import java.io.*;
import java.util.Enumeration;

import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipEntry;

import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ArchiveFile {

    private final static String ZIP_DIR   = "xml_new";
    private final static String ZIP_FILE  = "zip/test_zip.zip";

    private final String SLASH_BACK      = "/";
    private final String CHARSET_UTF_8   = "UTF-8";

    public  boolean ACTION_unzip
            = true
            ;
    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveFile.class);

    public ArchiveFile()
    {
        try {
            if (ACTION_unzip)
                Unzip(new File(ZIP_FILE));
            else
                Zip(ZIP_DIR, ZIP_FILE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void createDir(final String dir)
    {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void createFolder(final String dirName)
    {
        if (dirName.endsWith(SLASH_BACK)) {
            createDir(dirName.substring(0, dirName.length() - 1));
        }

    }
    private String checkFolder(final String file_path)
    {
        System.out.println(file_path);
        String dir ="";
        if (!file_path.endsWith(SLASH_BACK) && file_path.contains(SLASH_BACK)) {
           dir = "test/" +  file_path.substring(0, file_path.lastIndexOf(SLASH_BACK));
            createDir(dir);
        }
        return dir;
    }
    private String checkFolder(final String file_path, String unzipFolder)
    {
        System.out.println(file_path);
        String dir ="";
        if (!file_path.endsWith(SLASH_BACK) && file_path.contains(SLASH_BACK)) {
            dir += unzipFolder +  file_path.substring(0, file_path.lastIndexOf(SLASH_BACK));
            createDir(dir);
        }
        return dir;
    }

    private String Unzip(final File file) throws Exception
    {
        String pathToFile = "";
        String unzipFolder = "test/";
        String path ="";
                ZipFile zipFile = new ZipFile(file);
        Enumeration<?> entries = zipFile.getEntries();
        System.out.println("++++++++++++++++" + entries.hasMoreElements());
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            System.out.println("********************* = " + entry.getSize());
            String entryName = entry.getName();

            if (entryName.endsWith(SLASH_BACK)) {
                System.out.println("Create the directory <" + entryName + ">");
                createFolder (entryName);
                continue;
            } else
                path = checkFolder(entryName,unzipFolder);
            System.out.println("Path = " + path);
            System.out.println("Reading the file <" + entryName + ">");
            InputStream fis = zipFile.getInputStream(entry);
            System.out.println("///////////////// = " + fis.available());
            pathToFile = unzipFolder + entryName;
            FileOutputStream fos = new FileOutputStream(pathToFile);

            copyData(fis,fos);

            fis.close();
            fos.close();
        }
        zipFile.close() ;
        System.out.println("Zip file has unzipped!");
        return pathToFile;
    }

    private void Zip(String source_dir, String zip_file) throws Exception
    {
        checkFolder(zip_file);
        // Cоздание объекта ZipOutputStream из FileOutputStream
        FileOutputStream fout = new FileOutputStream(zip_file);
        ZipOutputStream zout = new ZipOutputStream(fout);
        // Определение кодировки
//        zout.setEncoding(CHARSET_UTF_8);

        // Создание объекта File object архивируемой директории
        File fileSource = new File(source_dir);

        addDirectory(zout, fileSource);

        // Закрываем ZipOutputStream
        zout.close();

        System.out.println("Zip file has created!");
    }

    private void addDirectory(ZipOutputStream zout, File fileSource) throws Exception
    {
//        if (!fileSource.exists()){
//            if(fileSource.mkdir()) {
//                LOGGER.debug("Directory {} successfully created.", fileSource.getAbsolutePath());
//
//            } else { LOGGER.debug("Directory {} not created.", fileSource.getAbsolutePath());
//            }
//
//        }
        File[] files = fileSource.listFiles();
        for(int i = 0; i < files.length; i++) {
            // Если file является директорией, то рекурсивно вызываем
            // метод addDirectory
            if(files[i].isDirectory()) {
                addDirectory(zout, files[i]);
                continue;
            }
            System.out.println("File has added <" + files[i].getName() + ">");

            FileInputStream fis = new FileInputStream(files[i]);

            zout.putNextEntry(new ZipEntry(files[i].getPath()));

            byte[] buffer = new byte[4048];
            int length;

            while((length = fis.read(buffer)) > 0)
                zout.write(buffer, 0, length);
            // Закрываем ZipOutputStream и InputStream
            zout.closeEntry();
            fis.close();
        }
    }
    private static void copyData(InputStream in, OutputStream out) throws Exception {
        int b;
        while ((b = in.read()) > 0) {

            out.write(b);

        }
    }
    public static void main(String[] args) throws Exception {
        ArchiveFile archiveFile = new ArchiveFile();
        String d = archiveFile.Unzip(new File(ZIP_FILE));
        System.out.println("---------------------------" + d);
        System.exit(0);
    }
}
