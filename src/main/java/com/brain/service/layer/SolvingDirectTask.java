package com.brain.service.layer;

import com.brain.util.function.CoefficientUtils;

public class SolvingDirectTask {

    /**
     * Решение прямой задачи
     * @param n количество слоев
     * @param teta сферическая координата тета (ед. изм. )
     * @param fi сферическая координата fi (ед. изм. )
     * @param mX направление силы электрического потенциала потенциала X (ед. изм. )
     * @param mY направление силы электрического потенциала потенциала Y (ед. изм. )
     * @param mZ направление силы электрического потенциала потенциала Z (ед. изм. )
     * @param rD растояние от центра шара (сфера описывающая поверхность мозга) (ед. изм. )
     * @return возвращает силу потенцала в точке
     * @throws Exception параметры заданы не верно или расчитать в точке потенциал не получается
     */
    public static double calculate(int n, double teta, double fi, double teta0, double fi0, double mX, double mY, double mZ, double rD) throws Exception {
        return   mX * CoefficientUtils.coefficientSum(n, rD, (L) ->     CoefficientUtils.coefficientAL(teta, fi, teta0, fi0, L)) +
                 mY * CoefficientUtils.coefficientSum(n, rD, (L) ->     CoefficientUtils.coefficientCL(teta, fi, teta0, fi0, L)) +
                 mZ * CoefficientUtils.coefficientSum(n, rD, (L) -> L * CoefficientUtils.coefficientBL(teta, fi, teta0, fi0, L));
    }
}
