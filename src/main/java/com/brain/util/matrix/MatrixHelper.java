package com.brain.util.matrix;

import com.brain.converter.MatrixConverter;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.List;
import java.util.Objects;

public class MatrixHelper {

    public static RealMatrix inverse(List<List<Double>> matrix){
        RealMatrix result = MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(matrix)));
        return new LUDecomposition(result).getSolver().getInverse();
    }

    public static RealMatrix multiply(List<Double> vector, RealMatrix realMatrix){
        RealMatrix result = MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convertToVector(vector)));
        return result.multiply(realMatrix);
    }
}
