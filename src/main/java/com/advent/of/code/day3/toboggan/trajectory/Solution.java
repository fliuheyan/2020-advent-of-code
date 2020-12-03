package com.advent.of.code.day3.toboggan.trajectory;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solution {
    public BigInteger count(int slop, int down) throws Exception {
        int line = 1;
        int count = 0;
        URL input = getClass().getClassLoader().getResource("input3");
        BufferedReader buffer = Files.newBufferedReader(Paths.get(input.toURI()));
        String currentLine = buffer.readLine();
        while ((currentLine = buffer.readLine()) != null) {
            line++;
            if ((line - 1) % down != 0) continue;
            if (currentLine.trim().charAt((slop * (line - 1) / down) % currentLine.length()) == '#') {
                count++;
            }
        }
        return new BigInteger(String.valueOf(count));
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        BigInteger bigInteger = solution.count(1, 1)
                .multiply(solution.count(3, 1))
                .multiply(solution.count(5, 1))
                .multiply(solution.count(7, 1))
                .multiply(solution.count(1, 2));
        System.out.println(bigInteger);
    }
}
