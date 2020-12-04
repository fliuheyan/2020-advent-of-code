package com.advent.of.code.day4.passport.processing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
    public List<String> validatePassports() throws Exception {
        List<String> fields = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        List<List<String>> inputStringList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("input4")));
        String line;
        List<String> tempList = new ArrayList<>();
        boolean isAllFieldValid = true;
        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) {
                if (isAllFieldValid) {
                    inputStringList.add(tempList);
                }
                isAllFieldValid = true;
                tempList = new ArrayList<>();
            } else {
                for (String field : line.split(" ")) {
                    if (isValidField(field)) {
                        tempList.add(field.split(":")[0]);
                    } else {
                        isAllFieldValid = false;
                        break;
                    }
                }
            }
        }
        if (isAllFieldValid) {
            inputStringList.add(tempList);
        }
        return inputStringList.stream()
                .map(list -> list.containsAll(fields) ? "valid" : "invalid")
                .collect(Collectors.toList());
    }

    public enum EyeColor {
        amb, blu, brn, gry, grn, hzl, oth
    }

    private boolean isValidField(String input) {
        String[] split = input.split(":");
        String type = split[0];
        String value = split[1];
        switch (type) {
            case "byr":
                return is4Digits(value) && between(value, 1920, 2002);
            case "iyr":
                return is4Digits(value) && between(value, 2010, 2020);
            case "eyr":
                return is4Digits(value) && between(value, 2020, 2030);
            case "hgt":
                return isMatchedHGT(value);
            case "hcl":
                return value.matches("^#([0-9]|[a-z]){6}$");
            case "ecl":
                return Arrays.stream(EyeColor.values()).anyMatch(x -> x.name().equals(value));
            case "pid":
                return value.matches("[0-9]{9}");
            case "cid":
                return true;
            default:
                return false;
        }
    }

    private boolean isMatchedHGT(String input) {
        String patternCM = "(\\d+)cm";
        String patternIN = "(\\d+)in";
        Matcher matcherCM = Pattern.compile(patternCM).matcher(input);
        Matcher matcherIN = Pattern.compile(patternIN).matcher(input);
        if (matcherCM.find()) {
            int hgt = Integer.parseInt(matcherCM.group(1));
            return hgt >= 150 && hgt <= 193;
        } else if (matcherIN.find()) {
            int hgt = Integer.parseInt(matcherIN.group(1));
            return hgt >= 59 && hgt <= 76;
        }
        return false;
    }

    private boolean between(String input, int from, int to) {
        int integer = Integer.parseInt(input);
        return integer >= from && integer <= to;
    }

    private boolean is4Digits(String input) {
        return input.matches("([0-9]|[a-z]){4}");
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        List<String> result = solution.validatePassports();
        System.out.println(result.stream().filter(str -> str.equals("valid")).count());
    }
}
