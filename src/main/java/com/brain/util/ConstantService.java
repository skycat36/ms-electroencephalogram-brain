package com.brain.util;

import java.util.Arrays;
import java.util.List;

public class ConstantService {


    /**
     * sihma_i - Удельная электропроводность скальпа черепа и церебральной жидкости слоев
     * sihma_4 - Удельная электропроводность мозга
     * sihma_1 - Удельная электропроводность скальпа
     */
    public static final List<Double> SIGMA_I = Arrays.asList(-1., 0.33, 0.004, 0.27, 0.);

    /**
     * R_i - радиусы сферы слоев мозга
     * R_4 - радиус мозга
     * R_1 - поверхность скальпа
     */
    public static final List<Double> R_I = Arrays.asList(-1., 78., 7., 5., 0.);


}
