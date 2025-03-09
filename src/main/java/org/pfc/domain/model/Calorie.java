package org.pfc.domain.model;

/**
 * PFC摂取量からカロリー変換する列挙型
 */
public enum Calorie {
    /** タンパク質 */
    PROTEIN(4),
    /** 脂質 */
    FAT(9),
    /** 炭水化物 */
    CARBOHYDRATE(4)
    ;

    private final int toCalorie;

    Calorie(int toCalorie) {
        this.toCalorie = toCalorie;
    }

    public int getToCalorie() {
        return toCalorie;
    }
}
