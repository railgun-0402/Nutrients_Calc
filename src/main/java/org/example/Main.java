package org.example;

import org.example.calorie_calc.NutrientCalculation;

public class Main {
    public static void main(String[] args) {
        try {
            // 白米の栄養素
            NutrientCalculation riceNutrient = new NutrientCalculation(2.5, 0.3, 37.1);
            // 納豆の栄養素
            NutrientCalculation nattoNutrient = new NutrientCalculation(16.5, 10.0, 12.1);

            // 各栄養素のカロリー計算
            int totalCalories = riceNutrient.calculateTotalCalories() + nattoNutrient.calculateTotalCalories();

            // 結果の表示
            System.out.println("納豆ご飯の総カロリー: " + totalCalories + " kcal"); // 具体的な合計カロリーを表示

        } catch (IllegalArgumentException e) {
            System.err.println("不正な引数が渡されました: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("想定外のエラーが発生しました: " + e.getMessage());
        }
    }
}
