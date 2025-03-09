package org.pfc.domain.service;

import org.pfc.domain.model.Calorie;
import org.pfc.domain.model.PFC;

/**
 * CalorieCalc
 * カロリー計算サービスクラス
 */
public class CalorieCalc {

    /** PFC摂取量 */
    private final PFC pfc;

    /**
     * コンストラクタ
     *
     * @param pfc PFC摂取量(g)クラス
     */
    public CalorieCalc(PFC pfc) {
        this.pfc = pfc;
    }

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
     * PFCの総カロリー量を計算するメソッド
     *
     * @return PFCを合わせた総カロリー量(kcal)
     */
    public int calculateTotalCalories() {
        return
                intakeToCalories(pfc.proteinIntake(), Calorie.PROTEIN.getToCalorie())
                        + intakeToCalories(pfc.fatIntake(), Calorie.FAT.getToCalorie())
                        + intakeToCalories(pfc.carbohydrateIntake(), Calorie.CARBOHYDRATE.getToCalorie());
    }
}
