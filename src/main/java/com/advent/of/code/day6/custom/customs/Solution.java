package com.advent.of.code.day6.custom.customs;

import java.io.*;
import java.util.*;

public class Solution {
    public int calculateTotalYesAnswerAccordingGroups() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("input6")));
        String line;
        List<Set<String>> result = new ArrayList<>();
        Set<String> groupInput = new HashSet<>();
        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) {
                result.add(groupInput);
                groupInput = new HashSet<>();
            } else {
                groupInput.addAll(Arrays.asList(line.split("")));
            }
        }
        result.add(groupInput);
        return result.stream().map(Set::size).reduce(0, Integer::sum);
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();

        System.out.println(solution.calculateTotalYesAnswerAccordingGroups());
    }
}
