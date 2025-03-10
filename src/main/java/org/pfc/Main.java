package org.pfc;

import org.pfc.application.FoodCalorieCalc;
import org.pfc.domain.service.CalorieCalcService;

/**
 * Main
 * 今回はMainクラスから実行します
 * 本来は画面(Presentation層)として、アプリケーション層に値を渡すのが正しい形
 */
public class Main {
    public static void main(String[] args) {
        try {
            // カロリー計算のインスタンス作成
            CalorieCalcService calorieCalcService = new CalorieCalcService();
            FoodCalorieCalc foodCalorieCalc = new FoodCalorieCalc(calorieCalcService);

            // 白米の栄養素
            int riceCalories = foodCalorieCalc.calcTotalCalorie(2.5, 0.3, 37.1);
            // 納豆の栄養素
            int nattoCalories = foodCalorieCalc.calcTotalCalorie(16.5, 10.0, 12.1);

            // 各栄養素のカロリー計算
            int totalCalories = riceCalories + nattoCalories;

            // 結果の表示
            System.out.println("納豆ご飯の総カロリー: " + totalCalories + " kcal"); // 具体的な合計カロリーを表示

        } catch (IllegalArgumentException e) {
            System.err.println("不正な引数が渡されました: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("想定外のエラーが発生しました: " + e.getMessage());
        }
    }
}