package org.example.calorie_calc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NutrientCalculationTest {

    /**
     * PFC摂取量から、白米の総カロリー数が計算できることを確認するテスト
     */
    @Test
    public void testCalculateTotalCaloriesForRice() {
        NutrientCalculation riceNutrient = new NutrientCalculation(2.5, 0.3, 37.1);
        // 白米の総カロリーは次のように計算されます
        // タンパク質：2.5g × 4kcal/g = 10kcal
        // 脂質：0.3g × 9kcal/g = 2.7kcal
        // 炭水化物：37.1g × 4kcal/g = 148.4kcal
        // 合計 = 10 + 2.7 + 148.4 = 161.1kcal -> 四捨五入で161kcal
        assertEquals(161, riceNutrient.calculateTotalCalories());
    }

    /**
     * PFC摂取量から、納豆の総カロリー数が計算できることを確認するテスト
     */
    @Test
    public void testCalculateTotalCaloriesForNatto() {
        NutrientCalculation nattoNutrient = new NutrientCalculation(16.5, 10.0, 12.1);
        // 納豆の総カロリーは次のように計算されます
        // タンパク質：16.5g × 4kcal/g = 66kcal
        // 脂質：10.0g × 9kcal/g = 90kcal
        // 炭水化物：12.1g × 4kcal/g = 48.4kcal
        // 合計 = 66 + 90 + 48.4 = 204.4kcal -> 四捨五入で204kcal
        assertEquals(204, nattoNutrient.calculateTotalCalories());
    }

    /**
     * (おまけ)PFC摂取量から、バナナの総カロリー数が計算できることを確認するテスト
     */
    @Test
    public void testCalculateTotalCaloriesForBanana() {
        NutrientCalculation bananaNutrient = new NutrientCalculation(3.8, 0.4, 78.5);
        // バナナ100gの総カロリーは次のように計算されます
        // タンパク質：3.8g × 4kcal/g = 15.2kcal
        // 脂質：0.4g × 9kcal/g = 3.6kcal
        // 炭水化物：78.5g × 4kcal/g = 313.6kcal
        // 合計 = 15 + 5 + 314 = 333kcal(四捨五入済)
        assertEquals(333, bananaNutrient.calculateTotalCalories());
    }

    /**
     * 摂取量(g)の少数第二位が四捨五入できること
     */
    @Test
    public void testRoundingIntake() {
        NutrientCalculation nutrient = new NutrientCalculation(2.57, 0.39, 37.18);
        // タンパク質の小数第一位
        assertEquals(2.6, nutrient.getProteinIntake());
        // 脂肪の小数第一位
        assertEquals(0.4, nutrient.getFatIntake());
        // 炭水化物の小数第一位
        assertEquals(37.2, nutrient.getCarbohydrateIntake());
    }

    /**
     * 小数第三位以下でも小数第一位になって返却されること
     */
    @Test
    public void testRoundingMoreTwoPoint() {
        NutrientCalculation nutrient = new NutrientCalculation(2.555, 0.355, 37.155);
        // タンパク質の小数第一位
        assertEquals(2.6, nutrient.getProteinIntake());
        // 脂肪の小数第一位
        assertEquals(0.4, nutrient.getFatIntake());
        // 炭水化物の小数第一位
        assertEquals(37.2, nutrient.getCarbohydrateIntake());
    }

    /**
     * 摂取量がマイナスの場合は例外が発生すること
     */
    @Test
    public void testCheckMinusIntake() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class, () -> new NutrientCalculation(-2.5, 0.355, 37.155));
        assertEquals("intake cannot be negative: -2.5", expected.getMessage());
    }
}
