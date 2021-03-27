package com.brain.function.legangr;

public class NormMultiplier {


    /**
     * Описывает распространение электрических потенциалов по сфере мозга
     */
    public static double Y(int L, int m, double teta, double fi) throws Exception {
        double result = calculeteAttachedNormMultiplierLegandra(L, m);

        if (m >= 0){
            result *= PolynomLejandra.mergePolinomLegandra(Math.cos(teta), L, m) * Math.cos(m * fi);
        } else {
            result *= PolynomLejandra.mergePolinomLegandra(Math.cos(teta), L, Math.abs(m)) * Math.sin(Math.abs(m) * fi);
        }
        return result;
    }

    /**
     * Присоединенные функции лежандра
     */
    public static double calculeteAttachedNormMultiplierLegandra(int L, int m){
        int ksi = m == 0 ? 1 : 0;
        return Math.sqrt((2 * L + 1) / ( 2 * Math.PI * (1 + ksi)) * calculateFactDelLegandra(L, m));
    }

    /**
     * Посчитать нормировочные множетили
     */
    private static double calculateFactDelLegandra(int L, int m){
        if (L - Math.abs(m) < 0) {
            throw new RuntimeException("NaN");
        }

        double result = 1;
        for (int i = L - Math.abs(m); i < L + Math.abs(m); i++){
            result /= i == 0 ? 1 : i;
        }
        return result;
    }

}
