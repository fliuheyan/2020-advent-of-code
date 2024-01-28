package com.advent.of.code.day5.binary.boarding;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
    private static final int ROW = 128;
    private static final int COLUMN = 8;

    public int getMaxSeatNumber() throws Exception {
        Stream<String> stringStream = Files.lines(Paths.get(getClass().getClassLoader().getResource("input5").toURI()));
        return stringStream.map(this::countNumber).max(Comparator.comparing(Integer::intValue)).orElse(0);
    }

    public int getMySeatNumber() throws Exception {
        List<Integer> seatNumbers = Files.lines(Paths.get(getClass().getClassLoader().getResource("input5").toURI()))
                .map(this::countNumber)
                .sorted().collect(Collectors.toList());
        int difference = seatNumbers.get(0);
        return seatNumbers.get(IntStream.range(0, seatNumbers.size()).filter(index -> seatNumbers.get(index) - index != difference)
                .sorted().findFirst().getAsInt()) - 1;
    }

    private int countNumber(String input) {
        String rowString = input.substring(0, 7);
        String columnString = input.substring(7);
        return calculateRow(rowString) * COLUMN + calculateColumn(columnString);
    }

    private int calculateColumn(String columnString) {
        int begin = 0;
        int end = COLUMN - 1;
        int result = 0;
        for (int i = 0; i < columnString.length(); i++) {
            char c = columnString.charAt(i);
            if (c == 'L') {
                end = (end + begin - 1) / 2;
                result = begin;
            }
            if (c == 'R') {
                begin = (end + begin + 1) / 2;
                result = end;
            }
        }
        return result;
    }

    private int calculateRow(String rowString) {
        int begin = 0;
        int end = ROW - 1;
        int result = 0;
        for (int i = 0; i < rowString.length(); i++) {
            char c = rowString.charAt(i);
            if (c == 'F') {
                end = (end + begin - 1) / 2;
                result = begin;
            }
            if (c == 'B') {
                begin = (end + begin + 1) / 2;
                result = end;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        System.out.println(solution.getMySeatNumber());
    }
}
