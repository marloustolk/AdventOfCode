package advent.of.code._2020;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day6_QuestionCombiner {

    public Day6_QuestionCombiner(){
        first();
        second();
    }

    static void first() {
        List<List<String>> groups = getDeclarationForms();
        int answer = combineAnswers(groups).stream().reduce(0, Integer::sum);
        System.out.println(String.format("Day 6.1. Combined answers of %d groups, multiplied answer = %s", groups.size(), answer));
    }

    static void second() {
        List<List<String>> groups = getDeclarationForms();
        int answer = combineSameAnswers(groups).stream().reduce(0, Integer::sum);
        System.out.println(String.format("Day 6.2. Searched for same answers in %d groups, multiplied answer = %s", groups.size(), answer));
    }

    @SuppressWarnings("ConstantConditions")
    private static List<List<String>> getDeclarationForms() {
        return InputReader.readInput("/customs-declaration-forms.txt").toListOfLists(" ", "  ");
    }

    private static List<Integer> combineAnswers(List<List<String>> groups) {
        return groups.stream()
                .map(group -> String.join("", group).chars().distinct().count())
                .map(Long::intValue)
                .collect(toList());
}

    private static List<Integer> combineSameAnswers(List<List<String>> groups) {
        return groups.stream()
                .map(group -> group.stream().reduce(getAlphabet(), Day6_QuestionCombiner::findSameCharacters).length())
                .collect(toList());
    }

    private static String getAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz";
    }

    private static String findSameCharacters(String a, String b){
        return a.chars().mapToObj(i->(char)i).map(Object::toString).filter(b::contains).collect(Collectors.joining());
    }
}
