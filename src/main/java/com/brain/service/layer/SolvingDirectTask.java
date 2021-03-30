package com.brain.service.layer;

import com.brain.util.function.CoefficientUtils;
import com.brain.util.function.legangr.NormMultiplier;
import com.brain.util.ConstantUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolvingDirectTask {

    /**
     * Решение прямой задачи
     * @param n количество слоев
     * @param teta сферическая координата тета (ед. изм. )
     * @param fi сферическая координата fi (ед. изм. )
     * @param mX направление силы электрического потенциала потенциала X (ед. изм. )
     * @param mY направление силы электрического потенциала потенциала Y (ед. изм. )
     * @param mZ направление силы электрического потенциала потенциала Z (ед. изм. )
     * @param R1 значение ограничивающие возникновение импульса (ед. изм. )
     * @param rD растояние от центра шара (сфера описывающая поверхность мозга) (ед. изм. )
     * @return возвращает силу потенцала в точке
     * @throws Exception параметры заданы не верно или расчитать в точке потенциал не получается
     */
    public static double calculate(int n, double teta, double fi, double mX, double mY, double mZ, double R1, double rD) throws Exception {
        double result = 0;

        List<List<Double>> arrHim = new ArrayList<>();

        double temp = (1 / (4 * Math.PI * ConstantUtils.SIGMA_3));
        for (int i = 1; i < n+1; i++){
            arrHim.add(Arrays.asList(
                    temp * divRad(i, rD) * mX / NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, 1)  * NormMultiplier.Y(i, 1, teta, fi) * Math.cos(fi),
                    temp * divRad(i, rD) * mY / NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, -1) * NormMultiplier.Y(i, -1, teta, fi),
                    temp * divRad(i, rD) * mZ / NormMultiplier.calculeteAttachedNormMultiplierLegandra(i, 0)  * NormMultiplier.Y(i, 0, teta, fi) * i
            ));
        }

        for (int i = 1; i < n; i++){
            for (int j = -1; j < 1; j++){
                int mArrH = j > 0 ? 0 : 1;
                if (j == 0){
                    mArrH = 2;
                }
                result += arrHim.get(i-1).get(mArrH) * (2 * i + 1) / (i * (CoefficientUtils.calculateMatrixB(i, 1).get(0).get(0))) *                                                                             //(VariableB.calculateMatrixB(i, 2) //при 2 происходит деление на 0
                        Math.pow(ConstantUtils.R_1/R1, i+1) * (NormMultiplier.Y(i, j, teta, fi) - NormMultiplier.Y(0, 0, teta, fi));
            }
        }

        return result;
    }


    private static double divRad(int i, double r_d){
        return Math.pow(r_d, i-1) / Math.pow(ConstantUtils.R_1, i+1);
    }
}
