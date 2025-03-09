package org.pfc.application;

import org.pfc.domain.model.PFC;
import org.pfc.domain.service.CalorieCalc;
import org.pfc.utils.DecimalUtils;

/**
 * FoodCalorieCalc
 * 摂取量からカロリーを計算するユースケース
 */
public class FoodCalorieCalc {
    /** PFC摂取量のmodel */
    private final PFC pfc;

    /** コンストラクタ */
    public FoodCalorieCalc(double proteinIntake, double fatIntake, double carbohydrateIntake) {
        // バリデーションチェック
        validateNutrientIntake(proteinIntake);
        validateNutrientIntake(fatIntake);
        validateNutrientIntake(carbohydrateIntake);

        // 小数第一位に四捨五入
        proteinIntake = DecimalUtils.roundToIntakeDecimal(proteinIntake);
        fatIntake = DecimalUtils.roundToIntakeDecimal(fatIntake);
        carbohydrateIntake = DecimalUtils.roundToIntakeDecimal(carbohydrateIntake);

        this.pfc = new PFC(proteinIntake, fatIntake, carbohydrateIntake);
    }

    /**
     * 摂取量が0以上であるかを確認するメソッド
     *
     * @param intake 摂取量
     * @throws IllegalArgumentException 摂取量が負の場合
     */
    private void validateNutrientIntake(double intake) {
        if (intake < 0) {
            throw new IllegalArgumentException("摂取量がマイナス値になっています: " + intake);
        }
    }

    /**
     * PFC摂取量から総カロリーに変換する計算を実行
     *
     * @return int 総カロリー量
     */
    public int calcTotalCalorie() {
        CalorieCalc calorieCalc = new CalorieCalc(pfc);
        return calorieCalc.calculateTotalCalories();
    }
}
