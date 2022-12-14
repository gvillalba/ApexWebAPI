package com.apexsystems.demo.germanvillalba.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.apexsystems.demo.germanvillalba.api.util.RewardsUtil.calculatePoints;

public class RewardsUtilTest {

    @Test
    public void calculatePoints_withZeroDollarsTest(){
        long dollarAmount = 0;
        long expectedPoints = 0;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_withSmallDollarAmountTest(){
        long dollarAmount = 20;
        long expectedPoints = 0;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_with50DollarsTest(){
        long dollarAmount = 50;
        long expectedPoints = 0;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_with51DollarsTest(){
        long dollarAmount = 51;
        long expectedPoints = 1;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_with70DollarsTest(){
        long dollarAmount = 70;
        long expectedPoints = 20;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_with100DollarsTest(){
        long dollarAmount = 100;
        long expectedPoints = 50;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_with101DollarsTest(){
        long dollarAmount = 101;
        long expectedPoints = 52;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }

    @Test
    public void calculatePoints_with120DollarsTest(){
        long dollarAmount = 120;
        long expectedPoints = 90;
        long actualPoints = calculatePoints(dollarAmount);
        Assertions.assertSame(actualPoints,expectedPoints);
    }
}
