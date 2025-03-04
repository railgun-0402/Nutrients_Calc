package org.example.calorie_calc;

import org.example.domain.Calorie;
import org.example.utils.DecimalUtils;

/**
 * NutrientCalculationクラス
 * PFCの摂取量からカロリー計算する
 */
public class NutrientCalculation {
    /** タンパク質摂取量(g) */
    private final double proteinIntake;
    /** 脂質摂取量(g) */
    private final double fatIntake;
    /** 炭水化物摂取量(g) */
    private final double carbohydrateIntake;

    /**
     * コンストラクタ
     *
     * @param proteinIntake タンパク質摂取量(g)
     * @param fatIntake 脂質摂取量(g)
     * @param carbohydrateIntake 炭水化物摂取量(g)
     */
    public NutrientCalculation(double proteinIntake, double fatIntake, double carbohydrateIntake) {
        // 摂取量がマイナスの場合は例外
        validateNutrientIntake(proteinIntake);
        validateNutrientIntake(fatIntake);
        validateNutrientIntake(carbohydrateIntake);

        // 小数第一位に四捨五入
        this.proteinIntake = DecimalUtils.roundToIntakeDecimal(proteinIntake);
        this.fatIntake = DecimalUtils.roundToIntakeDecimal(fatIntake);
        this.carbohydrateIntake = DecimalUtils.roundToIntakeDecimal(carbohydrateIntake);
    }

    /**
     * 摂取量が0以上であるかを確認するメソッド
     *
     * @param intake 摂取量
     * @throws IllegalArgumentException 摂取量が負の場合
     */
    private void validateNutrientIntake(double intake) {
        if (intake < 0) {
            throw new IllegalArgumentException("intake cannot be negative: " + intake);
        }
    }

    /**
     * 摂取量をカロリーに変換する (カロリーは整数)
     * @param intake 摂取量
     * @param caloriesPerGram カロリー変換数値
     * @return 変換後カロリー(kcal)
     */
    private int intakeToCalories(double intake, int caloriesPerGram) {
        return (int) Math.round(intake * caloriesPerGram);
    }

    /**
     * PFCの総カロリー量を計算するメソッド
     *
     * @return PFCを合わせた総カロリー量(kcal)
     */
    public int calculateTotalCalories() {
        return
                intakeToCalories(proteinIntake, Calorie.PROTEIN.getToCalorie())
                + intakeToCalories(fatIntake, Calorie.FAT.getToCalorie())
                + intakeToCalories(carbohydrateIntake, Calorie.CARBOHYDRATE.getToCalorie());
    }

    public double getProteinIntake() {
        return proteinIntake;
    }

    public double getFatIntake() {
        return fatIntake;
    }

    public double getCarbohydrateIntake() {
        return carbohydrateIntake;
    }
}
