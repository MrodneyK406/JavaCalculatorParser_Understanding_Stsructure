package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JavaParser {

    public static void main(String[] args) {
        // Specify the path to the Java file you want to analyze
        String filePath = "/home/rodhack406/Desktop/Maven/my-project/src/main/java/com/example/JavaCalculator.java";

        try {
            // Parse the Java file
            CompilationUnit cu = StaticJavaParser.parse(Paths.get(filePath));

            // Print the AST of each class/interface
            System.out.println("Abstract Syntax Tree:");
            printNode(cu, 0);

            // Perform other analysis as needed
            // For example, counting tokens and token types
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Parse the Java file
            int tokenCount = countAllTokens(filePath);

            // Print the number of tokens
            System.out.println("Total Number of tokens in the Java file: " + tokenCount);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Parse the Java file
            Map<String, Integer> tokenCounts = countTokens(filePath);

            // Print the token counts
            System.out.println("Total number of types of Token:");
            for (Map.Entry<String, Integer> entry : tokenCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getClass().getSimpleName());
        node.getChildNodes().forEach(child -> printNode(child, level + 1));
    }

    private static Map<String, Integer> countTokens(String filePath) throws IOException {
        Map<String, Integer> tokenCounts = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+|(?=[^\\w\\d])|(?<=[^\\w\\d])"); // Split by whitespace or non-word characters
            for (String token : tokens) {
                tokenCounts.put(token, tokenCounts.getOrDefault(token, 0) + 1);
            }
        }
        reader.close();
        return tokenCounts;
    }

    private static int countAllTokens(String filePath) throws IOException {
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+|(?=[^\\w\\d])|(?<=[^\\w\\d])"); // Split by whitespace or non-word characters
            count += tokens.length;
        }
        reader.close();
        return count;
    }
}
