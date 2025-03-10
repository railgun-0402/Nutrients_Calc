package org.pfc.domain.service;

import org.pfc.domain.model.Calorie;
import org.pfc.domain.model.PFC;

/**
 * CalorieCalc
 * カロリー計算サービスクラス
 */
public class CalorieCalcService {

    /**
     * 摂取量をカロリーに変換する (カロリーは整数)
     * @param intake 摂取量
     * @param caloriesPerGram カロリー変換数値
     * @return 変換後カロリー(kcal)
     */
    private int intakeToCalories(double intake, int caloriesPerGram) {
        if (intake < 0) {
            throw new IllegalArgumentException("摂取量がマイナス値になっています: " + intake);
        }
        return (int) Math.round(intake * caloriesPerGram);
    }

    /**
     * PFCの摂取量をカロリーに変換する
     *
     * @param proteinIntake      タンパク質摂取量(g)
     * @param fatIntake          脂質摂取量(g)
     * @param carbohydrateIntake 炭水化物摂取量(g)
     * @return 総カロリー量(kcal)
     */
    public int calculateTotalCalories(double proteinIntake, double fatIntake, double carbohydrateIntake) {
        PFC pfc = new PFC(proteinIntake, fatIntake, carbohydrateIntake);
        return intakeToCalories(pfc.proteinIntake(), Calorie.PROTEIN.getToCalorie()) +
                intakeToCalories(pfc.fatIntake(), Calorie.FAT.getToCalorie()) +
                intakeToCalories(pfc.carbohydrateIntake(), Calorie.CARBOHYDRATE.getToCalorie());
    }
}
