package advent.of.code._2020;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day2_PasswordDebugger {

    public Day2_PasswordDebugger() {
        first();
        second();
    }

    static void first() {
        Map<String, List<String>> passwordsPerPolicy = getPasswords();
        long validPasswords = countValidPasswords(passwordsPerPolicy, false);
        System.out.println(String.format("Day 2.1. Policy [char-count is between min-max in password], " +
                "answer = %d valid passwords", validPasswords));
    }

    static void second() {
        Map<String, List<String>> passwordsPerPolicy = getPasswords();
        long validPasswords = countValidPasswords(passwordsPerPolicy, true);
        System.out.println(String.format("Day 2.2. Policy [char is on min or max in password], " +
                "answer = %d valid passwords", validPasswords));
    }

    @SuppressWarnings("ConstantConditions")
    private static Map<String, List<String>> getPasswords() {
        return InputReader.readInput("/passwords.txt").toMap(": ");
    }

    private static long countValidPasswords(Map<String, List<String>> passwordsPerPolicy, boolean newPolicy) {
        return passwordsPerPolicy.keySet().stream()
                .map(policy -> passwordsPerPolicy.get(policy).stream()
                        .map(newPolicy ? isValidNewPolicy(policy) : isValidOldPolicy(policy)))
                .flatMap(Function.identity())
                .filter(valid -> valid)
                .count();
    }

    private static Function<String, Boolean> isValidOldPolicy(String policyString) {
        return password -> {
            Policy policy = new Policy(policyString);
            long count = password.chars().filter(character -> character == policy.character).count();
            return count >= policy.min && count <= policy.max;
        };
    }

    private static Function<String, Boolean> isValidNewPolicy(String policyString) {
        return password -> {
            Policy policy = new Policy(policyString);
            return (password.charAt(policy.min) == policy.character && password.charAt(policy.max) != policy.character) ||
                    (password.charAt(policy.min) != policy.character && password.charAt(policy.max) == policy.character);
        };
    }

    static class Policy {
        int min;
        int max;
        char character;

        Policy(String policy) {
            String[] policyParts = policy.split(" ");
            min = Integer.parseInt(policyParts[0].split("-")[0]) - 1;
            max = Integer.parseInt(policyParts[0].split("-")[1]) - 1;
            character = policyParts[1].toCharArray()[0];
        }
    }
}
