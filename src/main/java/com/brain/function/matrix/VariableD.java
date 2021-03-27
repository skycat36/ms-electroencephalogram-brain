package com.brain.function.matrix;

import com.brain.util.ConstantService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VariableD {


    // i - начинается с 1
    public static List<List<Double>> calculateMatrixD(int L, int i) throws RuntimeException {
        List<List<Double>> result = new ArrayList<>();
        if (i > ConstantService.SIGMA_I.size() - 1 || i > ConstantService.R_I.size() - 1) throw new RuntimeException("Matrix not be calculeted");
        if (i == 1){
            result.add(Collections.singletonList(1.));
            result.add(Collections.singletonList((L + 1) / (L * Math.pow(ConstantService.R_I.get(i), 2 * L + 1))));
            return result;
        }
        //TODO ConstantService.SIGMA_I большая вероятность деления на 0
        result.add(Arrays.asList(
                L / (2 * L + 1) * (1 + ((L + 1) / L) * (ConstantService.SIGMA_I.get(i - 1) / ConstantService.SIGMA_I.get(i))),
                L / (2 * L + 1) * (1 - (ConstantService.SIGMA_I.get(i - 1) / ConstantService.SIGMA_I.get(i))) * Math.pow(ConstantService.R_I.get(i), 2 * L + 1)));

        result.add(Arrays.asList(
                (L + 1) / (2 * L + 1) * (1 - (ConstantService.SIGMA_I.get(i - 1) / ConstantService.SIGMA_I.get(i))) / Math.pow(ConstantService.R_I.get(i), 2 * L + 1),
                (L + 1) / (2 * L + 1) * (1 + (L / (L + 1)) * (ConstantService.SIGMA_I.get(i - 1) / ConstantService.SIGMA_I.get(i)))
        ));
        return result;
    }
}
