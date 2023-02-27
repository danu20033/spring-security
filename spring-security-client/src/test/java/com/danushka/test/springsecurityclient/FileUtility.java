package com.danushka.test.springsecurityclient;


import java.io.File;

public class FileUtility {


    public static void main(String[] args) {

        // read location
        String path = "C:/Temp";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile()) {


                String fileType = file.getName();

                if (fileType.endsWith(".txt")) {

                    proessTextFile(file);
                } else if (fileType.endsWith(".xml")) {
                    processXMLFile(file);
                } else {

                    throw new IllegalArgumentException("Unknown file type");
                }


            }


        }


    }

    private static void processXMLFile(File file) {
      System.out.println("Read XML File ..........");

      // read and do the processing

    }

    private static void proessTextFile(File file) {
        System.out.println("Read Text File ..........");
        // read and do the processing
    }

}
