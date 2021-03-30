package com.brain;

import com.brain.service.layer.SolvingDirectTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {


    public static void main(String... args) throws Exception {

        StringBuffer writeData = new StringBuffer();
        int n = 8;
        double R1 = 9.5;
        double temp = Math.PI / 3;

        for (double i = 0; i < Math.PI; i+=temp){
            for (double j = 0; j < 2*Math.PI; j+=temp){
                writeData.append(String.format("Teta[i]: %s, Fi[j]: %s |  rez: %s \n",
                        i,
                        j,
                        SolvingDirectTask.calculate(n, i, j, 0.1,0.1,0.1, R1, 2)));
            }
        }

        writeUsingFiles("E:\\magister\\ms-electroencephalogram-brain\\src\\main\\resources", "FileWriter.txt", writeData.toString());
    }

    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static void writeUsingFiles(String path, String fileName, String data) {
        try {
            Files.write(Paths.get(path + "\\" + fileName), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readToFile(String path, String fileName, String data){
        File file = new File(path + "\\" + fileName);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
