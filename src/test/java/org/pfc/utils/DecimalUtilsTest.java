package org.pfc.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.pfc.utils.DecimalUtils.isNumeric;
import static org.pfc.utils.DecimalUtils.roundToIntakeDecimal;

class DecimalUtilsTest {
    @Test
    public void testIsNumeric() {
        // 正常系
        assertTrue(isNumeric("123"));             // 整数
        assertTrue(isNumeric("123.45"));          // 浮動小数点数
        assertTrue(isNumeric("-123.45"));         // 負の浮動小数点数
        assertTrue(isNumeric("0"));               // ゼロ
        assertTrue(isNumeric("3.14159"));         // πの近似値

        // 異常系
        assertFalse(isNumeric(null));             // nullは数字でない
        assertFalse(isNumeric(""));               // 空文字列は数字でない
        assertFalse(isNumeric("abc"));            // アルファベットは数字でない
        assertFalse(isNumeric("123abc"));         // 数字と文字の混合も数字でない
        assertFalse(isNumeric("abc123"));         // 数字と文字の混合も数字でない
        assertFalse(isNumeric("12.3.4"));         // 複数の小数点がある場合
        assertFalse(isNumeric("1.2e3x"));         // 科学的記数法に文字が含まれる場合
    }

    /**
     * 小数第二位以下が四捨五入され、少数第一位になること
     */
    @Test
    public void testRoundingUtils() {
        // タンパク質の小数第一位
        assertEquals(7.6, roundToIntakeDecimal(7.57));
        // 脂肪の小数第一位
        assertEquals(0.4, roundToIntakeDecimal(0.438));
        // 炭水化物の小数第一位
        assertEquals(84.2, roundToIntakeDecimal(84.1923));
    }

    /**
     * 小数第三位以下でも小数第一位になって返却されること
     */
    @Test
    public void testRoundingMoreTwoPoint() {
        assertEquals(10.1, roundToIntakeDecimal(10.056));
        assertEquals(0.8, roundToIntakeDecimal(0.792193872));
        assertEquals(108.3, roundToIntakeDecimal(108.2743));
    }
}