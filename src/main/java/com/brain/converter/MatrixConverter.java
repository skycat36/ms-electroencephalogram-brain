package com.brain.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MatrixConverter {

    public static double[][] convert(List<List<Double>> arr){
        if (Objects.isNull(arr)){
            return null;
        }

        double[][] result = new double[arr.size()][];
        for (int i = 0; i < arr.size(); i++){
            result[i] = arr.get(i).stream().mapToDouble(e -> e).toArray();
        }
        return result;
    }

    public static double[][] convertToVector(List<Double> arr){
        if (Objects.isNull(arr)){
            return null;
        }

        double[][] result = new double[arr.size()][];
        for (int i = 0; i < arr.size(); i++){
            result[i] = new double[1];
            result[i][0] = arr.get(i);
        }
        return result;
    }

    public static List<Double> convertMatrixToVector(List<List<Double>> matrix){
        if (Objects.isNull(matrix)){
            return null;
        }
        return matrix.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public static List<List<Double>> convert(double[][] arr){
        if (Objects.isNull(arr)){
            return null;
        }

        List<List<Double>> result = new ArrayList<>();
        for (double[] doubles : arr) {
            result.add(Arrays.stream(doubles).boxed().collect(Collectors.toList()));
        }
        return result;
    }
}
