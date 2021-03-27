package com.brain.function.legangr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PolynomLejandra {


    private static final List<List<Integer>> arrParamPolinom = new ArrayList();
    static {

//        n=0
        arrParamPolinom.add(Collections.singletonList(1));

//        n=1
        arrParamPolinom.add(Collections.singletonList(1));

//        n=2
        arrParamPolinom.add(Arrays.asList(3, 1));

//        n=3
        arrParamPolinom.add(Arrays.asList(5, 3));

//        n=4
        arrParamPolinom.add(Arrays.asList(35, 30, 3));

//        n=5
        arrParamPolinom.add(Arrays.asList(63, 70, 15));

//        n=6
        arrParamPolinom.add(Arrays.asList(231, 315, 105, 5));

//        n=7
        arrParamPolinom.add(Arrays.asList(429, 693, 315, 35));

//        n=8
        arrParamPolinom.add(Arrays.asList(6_425, 12_012, 6_930, 1_260, 35));

//        n=9
        arrParamPolinom.add(Arrays.asList(12_155, 25_740, 18_018, 4_620, 315));

//        n=10
        arrParamPolinom.add(Arrays.asList(46_189, 109_395, 90_090, 30_030, 3_465, 63));

//        n=11
        arrParamPolinom.add(Arrays.asList(88_179, 230_945, 218_790, 90_090, 15_015, 693));

//        n=12
        arrParamPolinom.add(Arrays.asList(676_039, 1_939_938, 2_078_505, 1_021_020, 225_225, 18_018, 231));

//        n=13
        arrParamPolinom.add(Arrays.asList(1_300_075, 4_056_234, 4_849_845, 2_771_340, 765_765, 90_090, 3_003));

//        n=14
        arrParamPolinom.add(Arrays.asList(5_014_575, 16_900_975, 22_309_287, 14_549_535, 4_849_845, 765_765, 45_045, 429));

//        n=15
        arrParamPolinom.add(Arrays.asList(9_694_845, 35_102_025, 50_702_925, 37_182_145, 14_549_535, 2_909_907, 255_255, 6_435));


//        n=16
        arrParamPolinom.add(Arrays.asList(300_540_195, 1_163_381_400, 1_825_305_300, 1_487_285_800, 669_278_610, 162_954_792, 19_399_380, 875_160, 6_435));

//        n=
//        arrParamPolinom.add(Arrays.asList(583_401_555, 2_404_321_560., 4_071_834_900., 3_650_610_600., 1_859_107_250, 535_422_888, 81_477_396, 5_542_680, 109_395));
    }

    private static final List<Integer> arrDivPolinom = new ArrayList<>();
    static {
//        n=0
        arrDivPolinom.add(1);

//        n=1
        arrDivPolinom.add(1);

//        n=2
        arrDivPolinom.add(2);

//        n=3
        arrDivPolinom.add(2);

//        n=4
        arrDivPolinom.add(8);

//        n=5
        arrDivPolinom.add(8);

//        n=6
        arrDivPolinom.add(16);

//        n=7
        arrDivPolinom.add(16);

//        n=8
        arrDivPolinom.add(128);

//        n=9
        arrDivPolinom.add(128);

//        n=10
        arrDivPolinom.add(256);

//        n=11
        arrDivPolinom.add(256);

//        n=12
        arrDivPolinom.add(1024);

//        n=13
        arrDivPolinom.add(1024);

//        n=14
        arrDivPolinom.add(2048);

//        n=15
        arrDivPolinom.add(2048);

//        n=16
        arrDivPolinom.add(32768);

//        n=17
        arrDivPolinom.add(32768);
    }

    private static final List<FunkLegandr> arrFunkPolinom;
    static {
        arrFunkPolinom = new ArrayList<>();
        //n=0
        arrFunkPolinom.add((x)-> 1);

        //n=1
        arrFunkPolinom.add((x)-> x);

        //n=2
        arrFunkPolinom.add((x)-> (3 * Math.pow(x, 2) - 1) / 2);

        //n=3
        arrFunkPolinom.add((x)-> (5 * Math.pow(x, 2) - 3) / (x*2));

        //n=4
        arrFunkPolinom.add((x)-> (35 * Math.pow(x, 4) - 30 * Math.pow(x, 2) + 3) / 8);

        //n=5
        arrFunkPolinom.add((x)-> (63 * Math.pow(x, 4) - 70 * Math.pow(x, 2) + 15) / (x/8));

        //n=6
        arrFunkPolinom.add((x)-> (231 * Math.pow(x, 6) - 315 * Math.pow(x, 4) + 105 * Math.pow(x, 2) - 5) / 16);

        //n=7
        arrFunkPolinom.add((x)-> (429 * Math.pow(x, 6) - 693 * Math.pow(x, 4) + 315 * Math.pow(x, 2) - 35) / (x/16));

        //n=8
        arrFunkPolinom.add((x)-> (6_425 * Math.pow(x, 8) - 12_012 * Math.pow(x, 6) + 6_930 * Math.pow(x, 4) - 1_260 * Math.pow(x, 2) + 35) / 128);

        //n=9
        arrFunkPolinom.add((x)-> (12_155 * Math.pow(x, 8) - 25_740 * Math.pow(x, 6) + 18_018 * Math.pow(x, 4) - 4_620 * Math.pow(x, 2) + 315) / (x/128));

        //n=10
        arrFunkPolinom.add((x)-> (46_189 * Math.pow(x, 10) - 109_395 * Math.pow(x, 8) + 90_090 * Math.pow(x, 6) - 30_030 * Math.pow(x, 4)  + 3_465 * Math.pow(x, 2) - 63) / 256);

        //n=11
        arrFunkPolinom.add((x)-> (88_179 * Math.pow(x, 10) - 230_945 * Math.pow(x, 8) + 218_790 * Math.pow(x, 6) - 90_090 * Math.pow(x, 4)  + 15_015 * Math.pow(x, 2) - 693) / (x/256));

        //n=12
        arrFunkPolinom.add((x)-> (676_039 * Math.pow(x, 12) - 1_939_938 * Math.pow(x, 10) + 2_078_505 * Math.pow(x, 8) - 1_021_020 * Math.pow(x, 6)  +
                225_225 * Math.pow(x, 4) - 18_018 * Math.pow(x, 2) + 231) / 1_024);

        //n=13
        arrFunkPolinom.add((x)-> (1_300_075 * Math.pow(x, 12) - 4_056_234 * Math.pow(x, 10) + 4_849_845 * Math.pow(x, 8) - 2_771_340 * Math.pow(x, 6)  +
                765_765 * Math.pow(x, 4) - 90_090 * Math.pow(x, 2) + 3_003) / (x/1_024));

        //n=14
        arrFunkPolinom.add((x)-> (5_014_575 * Math.pow(x, 14) - 16_900_975 * Math.pow(x, 12) + 22_309_287 * Math.pow(x, 10) - 14_549_535 * Math.pow(x, 8)  +
                4_849_845 * Math.pow(x, 6) - 765_765 * Math.pow(x, 4) + 45_045 * Math.pow(x, 2) - 429) / 2_048);

        //n=15
        arrFunkPolinom.add((x)-> (9_694_845 * Math.pow(x, 14) - 35_102_025 * Math.pow(x, 12) + 50_702_925 * Math.pow(x, 10) - 37_182_145 * Math.pow(x, 8)  +
                14_549_535 * Math.pow(x, 6) - 2_909_907 * Math.pow(x, 4) + 255_255 * Math.pow(x, 2) - 6_435) / (x/2_048));

        //n=16
        arrFunkPolinom.add((x)-> (300540 * Math.pow(x, 16) - 1163381400 * Math.pow(x, 14) + 1825305300 * Math.pow(x, 12) - 1487285800 * Math.pow(x, 10)  +
                669278610 * Math.pow(x, 8) - 162954792 * Math.pow(x, 6) + 19399380 * Math.pow(x, 4) - 875160 * Math.pow(x, 2) + 6435) / 32768);

        //n=17
        arrFunkPolinom.add((x)-> (583_401_555 * Math.pow(x, 16) - 2_404_321_560. * Math.pow(x, 14) + 4_071_834_900. * Math.pow(x, 12) - 3_650_610_600. * Math.pow(x, 10)  +
                1_859_107_250 * Math.pow(x, 8) - 535_422_888 * Math.pow(x, 6) + 81_477_396 * Math.pow(x, 4) - 5_542_680 * Math.pow(x, 2) + 109_395) / 32_768);

//        //n=18
//        arrFunkPolinom.add((x)-> 1);
//
//        //n=19
//        arrFunkPolinom.add((x)-> 1);
//
//        //n=20
//        arrFunkPolinom.add((x)-> 1);

    }

    /**
     * Полином Лежандра
     * @return значение полинома
     * @throws Exception параметры заданы не верно или расчитать в точке полином невозможно
     */
    public static double calculatePolinomProizN(double x, int n, int m) throws Exception {
        if (n > arrParamPolinom.size()) throw new Exception("Scale can't be bigger " + arrFunkPolinom.size());

        double result = 1;

        if (n == 0 || n < m) return 0;

        result = result / arrDivPolinom.get(n);

        if (n == 1) return result;

        double temp = 0;
        double pow = n;
        int zn = 1;
        for (int i = 0; pow-m>=0; i++){

            temp += zn * arrParamPolinom.get(n).get(i) * calcOper(pow, m) * Math.pow(x, pow - m);
            pow -= 2;
            zn *= -1;
        }

        return result * temp;
    }

    private static double calcOper(double pov,int m){
        double result = 1;
        while (m > 0){
            m--;
            result *= pov;
            pov--;
        }
        return result;
    }

    /**
     * Производная полинома Лежандра
     * @return значение производной полинома
     * @throws Exception параметры заданы не верно или расчитать в точке производную полинома невозможно
     */
    public double calculateDerivativePalindromeLeander(double x, int n, int m)  throws Exception {
        if (n > arrParamPolinom.size()) throw new Exception("Scale can't be bigger " + arrFunkPolinom.size());

        double multiplierA = n / (1.0 - x * x);
        return multiplierA * (calculatePolinomProizN(x, n - 1, m) -  x * calculatePolinomProizN(x, n, m));
    }

    /**
     * Присоединённые многочлены Лежандра
     * @return значение полинома
     * @throws Exception параметры заданы не верно или расчитать в точке полином невозможно
     */
    public static double mergePolinomLegandra(double x, int n, int m) throws Exception {
        return Math.pow(-1, m) * Math.pow(1 - Math.pow(x, 2), m / 2.0) * calculatePolinomProizN(x, n, m);
    }
}
