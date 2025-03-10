package org.pfc.application;

import org.pfc.domain.service.CalorieCalcService;
import org.pfc.utils.DecimalUtils;

/**
 * FoodCalorieCalc
 * 摂取量からカロリーを計算するユースケース
 */
public class FoodCalorieCalc {
    /** カロリー計算サービス */
    private final CalorieCalcService calorieCalcService;

    /** コンストラクタ */
    public FoodCalorieCalc(CalorieCalcService calorieCalcService) {
        this.calorieCalcService = calorieCalcService;
    }

    /**
     * 摂取量から総カロリーを計算するメソッド
     *
     * @param proteinIntake      タンパク質摂取量(g)
     * @param fatIntake          脂質摂取量(g)
     * @param carbohydrateIntake 炭水化物摂取量(g)
     * @return int 総カロリー量
     */
    public int calcTotalCalorie(double proteinIntake, double fatIntake, double carbohydrateIntake) {
        // バリデーションチェック
        validateNutrientIntake(proteinIntake);
        validateNutrientIntake(fatIntake);
        validateNutrientIntake(carbohydrateIntake);

        // 小数第一位に四捨五入
        proteinIntake = DecimalUtils.roundToIntakeDecimal(proteinIntake);
        fatIntake = DecimalUtils.roundToIntakeDecimal(fatIntake);
        carbohydrateIntake = DecimalUtils.roundToIntakeDecimal(carbohydrateIntake);

        // カロリー計算の実行
        return calorieCalcService.calculateTotalCalories(proteinIntake, fatIntake, carbohydrateIntake);
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
}
