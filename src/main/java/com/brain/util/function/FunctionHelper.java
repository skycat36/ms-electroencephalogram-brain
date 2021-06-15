package com.brain.util.function;

import com.brain.util.minimization.point.PointMinimization;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionHelper {

    public static List<Double> calcFunkList(List<Function<PointMinimization, Double>> funkList, PointMinimization point){
        return funkList.stream().map(func -> func.apply(point)).collect(Collectors.toList());
    }

    public static List<List<Double>> calcFunkMatrix(List<List<Function<PointMinimization, Double>>> funkMatrix, PointMinimization point){
        List<List<Double>> result = new ArrayList<>();
        for (List<Function<PointMinimization, Double>> functionList: funkMatrix){
            result.add(functionList.stream().map(f -> f.apply(point)).collect(Collectors.toList()));
        }
        return result;
    }

    public static double iterateByArrayFunction(List<List<Double>> expU, double start, double area1, double area2, double step, BiFunction<Double, Double, Double> func) {
        double result = 0;
        int k = 0;
        for (double i = start; i < area1; i += step) {
            List<Double> temp = expU.get(k);
            k++;
            int m=0;
            for (double j = start; j < area2; j += step) {
                result += temp.get(m) - func.apply(i, j);
                m++;
            }
        }
        return result;
    }
}
