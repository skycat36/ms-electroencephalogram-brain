package com.brain.util.function;

import com.brain.converter.MatrixConverter;
import com.brain.util.ConstantUtils;
import com.brain.util.function.legangr.NormMultiplier;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CoefficientUtils {

    // i - начинается с 0
    public static List<List<Double>> calculateMatrixB(int L, int i) throws RuntimeException {

        RealMatrix result = MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(calculateMatrixD(L, i))));
        while (i > 1){
            i--;
            result = result.multiply(MatrixUtils.createRealMatrix(Objects.requireNonNull(MatrixConverter.convert(calculateMatrixD(L, i)))));
        }
        return MatrixConverter.convert(result.getData());
    }


    // i - начинается с 1
    public static List<List<Double>> calculateMatrixD(int L, int i) throws RuntimeException {
        List<List<Double>> result = new ArrayList<>();
        if (i > ConstantUtils.SIGMA_I.size() - 1 || i > ConstantUtils.R_I.size() - 1) throw new RuntimeException("Matrix not be calculeted");
        if (i == 1){
            result.add(Collections.singletonList(1.));
            result.add(Collections.singletonList((L + 1) / (L * Math.pow(ConstantUtils.R_I.get(i), 2 * L + 1))));
            return result;
        }

        result.add(Arrays.asList(
                L / (2 * L + 1) * (1 + ((L + 1) / L) * (ConstantUtils.SIGMA_I.get(i - 1) / ConstantUtils.SIGMA_I.get(i))),
                L / (2 * L + 1) * (1 - (ConstantUtils.SIGMA_I.get(i - 1) / ConstantUtils.SIGMA_I.get(i))) * Math.pow(ConstantUtils.R_I.get(i), 2 * L + 1)));

        result.add(Arrays.asList(
                (L + 1) / (2 * L + 1) * (1 - (ConstantUtils.SIGMA_I.get(i - 1) / ConstantUtils.SIGMA_I.get(i))) / Math.pow(ConstantUtils.R_I.get(i), 2 * L + 1),
                (L + 1) / (2 * L + 1) * (1 + (L / (L + 1)) * (ConstantUtils.SIGMA_I.get(i - 1) / ConstantUtils.SIGMA_I.get(i)))
        ));
        return result;
    }

    public static double coefficientAL(double teta, double fi, double teta0, double fi0, int L) throws RuntimeException {
        double temp = (2 * L + 1) / ((4 * Math.PI * ConstantUtils.SIGMA_3) * Math.pow(ConstantUtils.R_1, L + 1) *
                L * CoefficientUtils.calculateMatrixB(L, 1).get(0).get(0));
        return temp * (NormMultiplier.Y(L, 1, teta, fi) - NormMultiplier.Y(L, 1, teta0, fi0));
    }

    public static double coefficientBL(double teta, double fi, double teta0, double fi0, int L) throws RuntimeException {
        double temp = (2 * L + 1) / ((4 * Math.PI * ConstantUtils.SIGMA_3) * Math.pow(ConstantUtils.R_1, L + 1) *
                CoefficientUtils.calculateMatrixB(L, 1).get(0).get(0));
        return temp * (NormMultiplier.Y(L, 0, teta, fi) - NormMultiplier.Y(L, 0, teta0, fi0));
    }

    public static double coefficientCL(double teta, double fi, double teta0, double fi0, int L) throws RuntimeException {
        double temp = (2 * L + 1) / ((4 * Math.PI * ConstantUtils.SIGMA_3) * Math.pow(ConstantUtils.R_1, L + 1) *
                L * CoefficientUtils.calculateMatrixB(L, 1).get(0).get(0));
        return temp * (NormMultiplier.Y(L, -1, teta, fi) - NormMultiplier.Y(L, 1, teta0, fi0));
    }

    public static double coefficientEL(double expU, int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) {

        double result = (mX * coefficientSum(n, rD, (L) -> coefficientAL(teta, fi, teta0, fi0, L)) -
                        mY * coefficientSum(n, rD, (L) -> coefficientCL(teta, fi, teta0, fi0, L)) -
                        mZ * coefficientSum(n, rD, (L) -> coefficientBL(teta, fi, teta0, fi0, L)));

        return 2 * (expU - result);
    }

    public static double coefficientTL(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double R1, double rD) {

        Function<Integer, Double> subFunction = (L) -> (-L - 1) / Math.pow(R1, L + 2);

        return  (mX * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                mY * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> subFunction.apply(L) * L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L)));
    }

    public static double coefficientSum(int n, double rD, Function<Integer, Double> coefficientFunc) throws RuntimeException {
        return coefficientSum(1, n, rD, coefficientFunc);
    }

    public static double coefficientSum(int i, int n, double rD, Function<Integer, Double> coefficientFunc) throws RuntimeException {
        double result = .0;
        for (; i < n; i++){
            result += Math.pow(rD, i - 1) * coefficientFunc.apply(i);
        }
        return result;
    }

    public static List<Double> listSum(List<? extends Number> list1, List<? extends Number> list2) throws RuntimeException {
        if (list1.size() != list2.size()) throw new RuntimeException("Arrays must be equals size!");
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++){
            result.add(list1.get(i).doubleValue() + list2.get(i).doubleValue());
        }
        return result;
    }

    public static List<Double> listMul(List<? extends Number> list1, Number number) throws RuntimeException {
        return list1.stream().map(num -> num.doubleValue() * number.doubleValue()).collect(Collectors.toList());
    }
}
