package org.pfc.utils;

/**
 * DecimalUtils
 * どの計算にも共通で使用しそうなロジックをまとめたUtilsクラスです
 */
public class DecimalUtils {
    /**
     * 本来は摂取量がnull or 数値以外 ではないかバリデーションチェックが必要
     * 今回はMainクラスから呼び出すので、テストコードでケースをご紹介します
     *
     * @param str 摂取量(g)
     * @return 数値かどうか
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 摂取量を四捨五入するメソッド
     * ※小数第二位を四捨五入する
     *
     * @param intake 四捨五入したい数値(摂取量 g)
     * @return double 小数第二位に四捨五入した数値 (少数第一位)
     */
    public static double roundToIntakeDecimal(double intake) {
        return Math.round(intake * 10.0) / 10.0;
    }
}
