package com.brain.function.matrix;

import com.brain.converter.MatrixConverter;

import java.util.List;
import java.util.Objects;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class VariableB {


    // i - начинается с 0
    public static List<List<Double>> calculateMatrixB(int L, int i) throws RuntimeException{

        RealMatrix result = MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(VariableD.calculateMatrixD(L, i))));
        while (i > 1){
            i--;
            result = result.multiply(MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(VariableD.calculateMatrixD(L, i)))));
        }

        return MatrixConverter.convert(result.getData());
    }
}
