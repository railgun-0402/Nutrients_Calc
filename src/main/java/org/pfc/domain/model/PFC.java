package org.pfc.domain.model;

/**
 * PFC摂取量のrecordクラス
 *
 * @param proteinIntake      タンパク質摂取量(g)
 * @param fatIntake          脂質摂取量(g)
 * @param carbohydrateIntake 炭水化物摂取量(g)
 */
public record PFC(double proteinIntake, double fatIntake, double carbohydrateIntake) {}
