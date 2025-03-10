package org.pfc.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalorieCalcServiceTest {

    // テスト対象Serviceクラス
    private CalorieCalcService calorieCalcService;

    @BeforeEach
    void setup() {
        calorieCalcService = new CalorieCalcService();
    }

    /**
     * PFC摂取量からトータルカロリーを計算できること
     */
    @Test
    void testCalcTotalCalories() {
        // 各栄養素のカロリーを計算して合計値を検証
        int totalCalories = calorieCalcService.calculateTotalCalories(2.5, 0.3, 37.1);

        // 仮のカロリー計算に基づいた期待値を設定
        int expectedCalories = (int) Math.round(2.5 * 4 + 0.3 * 9 + 37.1 * 4);

        assertEquals(expectedCalories, totalCalories);
    }

    /**
     * タンパク質の摂取量がマイナスである異常ケース
     */
    @Test
    void testInvalidProtein() {
        // 不正なPFC値（タンパク質摂取量が負）で例外が発生するかを確認
        assertThrows(IllegalArgumentException.class, () -> calorieCalcService.calculateTotalCalories(-1.0, 0.3, 37.1),
                "タンパク質摂取量が負の値の場合、IllegalArgumentExceptionが発生すべき");
    }

    /**
     * 脂質の摂取量がマイナスである異常ケース
     */
    @Test
    void testInvalidFat() {
        // 不正なPFC値（脂質摂取量が負）で例外が発生するかを確認
        assertThrows(IllegalArgumentException.class, () -> calorieCalcService.calculateTotalCalories(2.5, -0.3, 37.1),
                "脂質摂取量が負の値の場合、IllegalArgumentExceptionが発生すべき");
    }

    /**
     * 炭水化物の摂取量がマイナスである異常ケース
     */
    @Test
    void testInvalidCarbohydrate() {
        // 不正なPFC値（炭水化物摂取量が負）で例外が発生するかを確認
        assertThrows(IllegalArgumentException.class, () -> calorieCalcService.calculateTotalCalories(2.5, 0.3, -37.1),
                "炭水化物摂取量が負の値の場合、IllegalArgumentExceptionが発生すべき");
    }
}