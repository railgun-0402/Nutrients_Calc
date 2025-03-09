package org.pfc.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodCalorieCalcTest {
    /**
     * PFC摂取量から、白米の総カロリー数が計算できることを確認するテスト
     */
    @Test
    public void testCalculateTotalCaloriesForRice() {
        FoodCalorieCalc riceNutrient = new FoodCalorieCalc(2.5, 0.3, 37.1);
        // 白米の総カロリーは次のように計算されます
        // タンパク質：2.5g × 4kcal/g = 10kcal
        // 脂質：0.3g × 9kcal/g = 2.7kcal
        // 炭水化物：37.1g × 4kcal/g = 148.4kcal
        // 合計 = 10 + 2.7 + 148.4 = 161.1kcal -> 四捨五入で161kcal
        assertEquals(161, riceNutrient.calcTotalCalorie());
    }

    /**
     * PFC摂取量から、納豆の総カロリー数が計算できることを確認するテスト
     */
    @Test
    public void testCalculateTotalCaloriesForNatto() {
        FoodCalorieCalc nattoNutrient = new FoodCalorieCalc(16.5, 10.0, 12.1);
        // 納豆の総カロリーは次のように計算されます
        // タンパク質：16.5g × 4kcal/g = 66kcal
        // 脂質：10.0g × 9kcal/g = 90kcal
        // 炭水化物：12.1g × 4kcal/g = 48.4kcal
        // 合計 = 66 + 90 + 48.4 = 204.4kcal -> 四捨五入で204kcal
        assertEquals(204, nattoNutrient.calcTotalCalorie());
    }

    /**
     * (おまけ)PFC摂取量から、バナナの総カロリー数が計算できることを確認するテスト
     */
    @Test
    public void testCalculateTotalCaloriesForBanana() {
        FoodCalorieCalc bananaNutrient = new FoodCalorieCalc(3.8, 0.4, 78.5);
        // バナナ100gの総カロリーは次のように計算されます
        // タンパク質：3.8g × 4kcal/g = 15.2kcal
        // 脂質：0.4g × 9kcal/g = 3.6kcal
        // 炭水化物：78.5g × 4kcal/g = 313.6kcal
        // 合計 = 15 + 5 + 314 = 333kcal(四捨五入済)
        assertEquals(333, bananaNutrient.calcTotalCalorie());
    }

    /**
     * タンパク質の摂取量がマイナスの場合は例外が発生すること
     */
    @Test
    public void testCheckMinusProtein() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class, () -> new FoodCalorieCalc(-2.5, 0.355, 37.155));
        assertEquals("摂取量がマイナス値になっています: -2.5", expected.getMessage());
    }

    /**
     * 脂質の摂取量がマイナスの場合は例外が発生すること
     */
    @Test
    public void testCheckMinusFat() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class, () -> new FoodCalorieCalc(2.5, -0.355, 37.155));
        assertEquals("摂取量がマイナス値になっています: -0.355", expected.getMessage());
    }

    /**
     * 炭水化物の摂取量がマイナスの場合は例外が発生すること
     */
    @Test
    public void testCheckMinusCarbohydrate() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class, () -> new FoodCalorieCalc(2.5, 0.355, -37.155));
        assertEquals("摂取量がマイナス値になっています: -37.155", expected.getMessage());
    }
}