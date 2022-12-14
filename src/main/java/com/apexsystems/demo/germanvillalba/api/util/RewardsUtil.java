package com.apexsystems.demo.germanvillalba.api.util;

public class RewardsUtil {
    public static long calculatePoints(long dollarAmount) {
        long value = dollarAmount;
        long points = 0;
        value -= 50;
        if (value <= 0) {
            return 0;
        }

        if (value <= 50) {
            points += value;
        }
        else {
            points += 50;
            points += 2 * (value - 50);
        }
        return points;
    }
}
