package advent.of.code._2020;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day4_PassportValidator {

    public Day4_PassportValidator(){
        first();
        second();
    }

    static void first() {
        List<Map<String, String>> passports = getPassports();
        long validPassports = countValidPassports(passports, false);
        System.out.println(String.format("Day 4.1. Passport [check fields, country-id is optional], answer = %d valid passports", validPassports));
    }

    static void second() {
        List<Map<String, String>> passports = getPassports();
        long validPassports = countValidPassports(passports, true );
        System.out.println(String.format("Day 4.2. Passport [validate fields, except country-id], answer = %d valid passports", validPassports));
    }

    @SuppressWarnings("ConstantConditions")
    private static List<Map<String, String>> getPassports() {
        return InputReader.readInput("/passports.txt").toListOfMaps(":", " ", "  ");
    }

    private static long countValidPassports(List<Map<String, String>> passports, boolean validateField) {
        return passports.stream()
                .map(fields -> Arrays.stream(PassportField.values())
                        .filter(field -> field != PassportField.COUNTRY_ID)
                        .allMatch(field -> fields.containsKey(field.name)
                                && (validateField ? field.validator.apply(fields.get(field.name)) : true)))
                .filter(valid -> valid)
                .count();
    }

    enum PassportField {
        BIRTH_YEAR("byr", field -> (Integer.parseInt(field) >= 1920) && (Integer.parseInt(field) <= 2002)),
        ISSUE_YEAR("iyr", field -> (Integer.parseInt(field) >= 2010) && (Integer.parseInt(field) <= 2020)),
        EXPIRATION_YEAR("eyr", field -> (Integer.parseInt(field) >= 2020) && (Integer.parseInt(field) <= 2030)),
        HEIGHT("hgt", field -> field.matches("^(1[5-8][0-9]cm)|(19[0-3]cm)$") || field.matches("^([56][0-9]in)|(7[0-6]in)$")),
        HAIR_COLOR("hcl", field -> field.matches("^#[0-9a-f]{6}$")),
        EYE_COLOR("ecl", field -> Arrays.asList("amb","blu","brn","gry","grn","hzl","oth").contains(field)),
        PASSPORT_ID("pid", field -> field.matches("^[0-9]{9}$")),
        COUNTRY_ID("cid", field -> true);

        String name;
        Function<String, Boolean> validator;

        PassportField(String name, Function<String, Boolean> validator) {
            this.name = name;
            this.validator = validator;
        }

    }
}
