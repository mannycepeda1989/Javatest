package com.baeldung.file;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileClassDemoUnitTest {

    private File makeDirectory(String directoryName) {
        File directory = new File(directoryName);
        directory.mkdir();
        if (directory.isDirectory()) {
            return directory;
        }
        throw new RuntimeException("Directory not created for " + directoryName);
    }

    
    private void removeDirectory(File directory) {
        // make sure you don't delete your home directory here
        if (directory.getPath().equals(System.getProperty("user.home"))) {
            return;
        }

        // remove directory and its files from system
        if (directory != null && directory.exists()) {
            // delete all files inside the directory
            File[] filesInDirectory = directory.listFiles();
            if (filesInDirectory != null) {
                List<File> files = Arrays.asList(filesInDirectory);
                files.forEach(f -> deleteFile(f));
            }

            // finally delete the directory itself
            deleteFile(directory);
        }
    }

    private void deleteFile(File fileToDelete) {
        if (fileToDelete != null && fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }


    @Test
    public void givenFileCreated_whenCreateNewFileInvoked_thenFileExistsOnSystem() throws IOException {
        File parentDirectory = makeDirectory("filesDirectory");

        File childFile = new File(parentDirectory, "file1.txt");
        childFile.createNewFile();

        assertTrue(childFile.exists());
        assertTrue(childFile.isFile());

        removeDirectory(parentDirectory);
    }

    @Test
    public void givenFileCreated_whenCreateNewFileInvoked_thenMetadataIsAsExpected() throws IOException {

        // different Operating systems have different separator characters
        String separatorCharacter = System.getProperty("file.separator");

        File parentDirectory = makeDirectory("filesDirectory");

        File childFile = new File(parentDirectory, "file1.txt");
        childFile.createNewFile();

        assertTrue(childFile.getName().equals("file1.txt"));
        assertTrue(childFile.getParentFile().getName().equals(parentDirectory.getName()));
        assertTrue(childFile.getPath().equals(parentDirectory.getPath() + separatorCharacter + "file1.txt"));

        removeDirectory(parentDirectory);
    }


    @Test(expected = FileNotFoundException.class)
    public void givenReadOnlyFileCreated_whenCreateNewFileInvoked_thenFileCannotBeWrittenTo() throws IOException {
        File parentDirectory = makeDirectory("filesDirectory");

        File childFile = new File(parentDirectory, "file1.txt");
        childFile.createNewFile();

        childFile.setWritable(false);

        FileOutputStream fos = new FileOutputStream(childFile);
        fos.write("Hello World".getBytes()); // write operation
        fos.flush();
        fos.close();

        removeDirectory(parentDirectory);
    }

    @Test(expected = FileNotFoundException.class)
    public void givenWriteOnlyFileCreated_whenCreateNewFileInvoked_thenFileCannotBeReadFrom() throws IOException {
        File parentDirectory = makeDirectory("filesDirectory");

        File childFile = new File(parentDirectory, "file1.txt");
        childFile.createNewFile();

        childFile.setReadable(false);

        FileInputStream fis = new FileInputStream(childFile);
        fis.read(); // read operation
        fis.close();

        removeDirectory(parentDirectory);
    }

    @Test
    public void givenFilesCreatedInDirectory_whenCreateNewFileInvoked_thenTheyCanBeListedAsExpected() throws IOException {
        File directory = makeDirectory("filtersDirectory");

        File csvFile = new File(directory, "csvFile.csv");
        csvFile.createNewFile();

        File txtFile = new File(directory, "txtFile.txt");
        txtFile.createNewFile();

        //normal listing
        assertEquals(2, directory.list().length);

        //filtered listing
        FilenameFilter csvFilter = (dir, ext) -> ext.endsWith(".csv");
        assertEquals(1, directory.list(csvFilter).length);

        removeDirectory(directory);
    }

    @Test
    public void givenDirectoryIsCreated_whenMkdirInvoked_thenDirectoryCanBeRenamed() {

        File source = makeDirectory("source");
        File destination = makeDirectory("destination");

        source.renameTo(destination);

        assertFalse(source.isDirectory());
        assertTrue(destination.isDirectory());

        removeDirectory(destination);
    }

    @Test
    public void givenDataIsWrittenToFile_whenWriteIsInvoked_thenFreeSpaceOnSystemDecreases() throws IOException {

        String name = System.getProperty("user.home") + System.getProperty("file.separator") + "test";
        File testDir = makeDirectory(name);
        File sample = new File(testDir, "sample.txt");

        long freeSpaceBeforeWrite = testDir.getFreeSpace();

        //write sample text to file
        try (FileOutputStream out = new FileOutputStream(sample)) {
            for (int i = 1; i <= 100; i++) {
                String sampleText = "Sample line number " + i + "\n";
                out.write(sampleText.getBytes());
            }
        }

        long freeSpaceAfterWrite = testDir.getFreeSpace();

        assertTrue(freeSpaceAfterWrite < freeSpaceBeforeWrite);

        removeDirectory(testDir);
    }

}