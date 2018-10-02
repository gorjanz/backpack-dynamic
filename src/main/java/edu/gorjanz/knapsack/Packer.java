package edu.gorjanz.knapsack;

import edu.gorjanz.knapsack.util.ParsingUtils;
import edu.gorjanz.knapsack.domain.PackingCombination;
import edu.gorjanz.knapsack.exception.APIException;
import edu.gorjanz.knapsack.service.DPSolver;
import edu.gorjanz.knapsack.service.Solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Packer {

    public static void main(String[] args) {
        try {
            System.out.println(Packer.pack(args[0]));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String pack(String absFilePath) throws APIException {
        try {
            Solver solver = new DPSolver();

            return String.join("\n", processFile(absFilePath).stream()
                    .map(solver::solve)
                    .collect(Collectors.toList()));

        } catch (IOException e) {
            e.printStackTrace();
            throw new APIException("File does not exist or content is corrupt...");
        }
    }

    private static List<PackingCombination> processFile(String filePath) throws IOException, APIException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<PackingCombination> testCases = new ArrayList<>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            PackingCombination scenario = ParsingUtils.parseCombination(line);
            testCases.add(scenario);
        }

        return testCases;
    }
}
