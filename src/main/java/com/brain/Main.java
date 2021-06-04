package com.brain;

import com.brain.service.layer.SolvingDirectTask;
import com.brain.service.layer.SolvingInverseTask;
import com.brain.service.layer.inverse.BitStepInverseResolveProcessor;
import com.brain.service.layer.inverse.NewtonInverseResolveProcessor;
import com.brain.util.minimization.point.Point4D;
import com.brain.util.minimization.point.ResultPoint;
import com.brain.util.minimization.point.ExpPoint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String... args) throws Exception {

        StringBuffer writeData = new StringBuffer();
        int n = 3;
        double R1 = 9.5;
        double step = Math.PI / 3;

        List<ExpPoint<Double>> arrTetaFI = new ArrayList<>();
        for (double i = 0; i < Math.PI; i+=step){
            for (double j = 0; j < 2 * Math.PI; j+=step){

                double directResult = SolvingDirectTask.calculate(n, i, j, 1, 1, 10,10, 30, 1);
                writeData.append(String.format("DirectTask  -- Teta[i]: %s, Fi[j]: %s |  rez: %s \n", i, j, directResult));
                arrTetaFI.add(new ExpPoint<>(i, j,directResult));
            }
        }

        Point4D point4D = new Point4D(10, 15, 35, 1);

        ResultPoint inverseResult = new SolvingInverseTask(new NewtonInverseResolveProcessor())
                .calculate(arrTetaFI, n,1, 1, R1, 0.00001, point4D);
        
        writeData.append(String.format("InverseTask -- rez --  mX: %s  | mY: %s | mZ: %s | rD: %s | potential: %s | iterations: %s\n\n",
                inverseResult.getPointX(), inverseResult.getPointY(), inverseResult.getPointZ(), inverseResult.getPointW(),
                inverseResult.getPotential(), inverseResult.getIterations()));

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
