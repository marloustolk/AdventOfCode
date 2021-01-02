package advent.of.code._2020;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day1_ExpenseFinder {

    private static final int SUM = 2020;

    public Day1_ExpenseFinder(){
        first();
        second();
    }

    static void first() {
        List<Integer> expenseReport = getExpenseReport();
        List<Integer> expenses = findExpenses(expenseReport, 2);
        Integer answer = multiply(expenses);
        System.out.println(String.format("Day 1.1. Sum of %s = %s, multiplied answer = %d", expenses, SUM, answer));
    }

    static void second() {
        List<Integer> expenseReport = getExpenseReport();
        List<Integer> expenses = findExpenses(expenseReport, 3);
        Integer answer = multiply(expenses);
        System.out.println(String.format("Day 1.1. Sum of %s = %s, multiplied answer = %d", expenses, SUM, answer));
    }

    @SuppressWarnings("ConstantConditions")
    private static List<Integer> getExpenseReport() {
        return InputReader.readInput("/expense-report.txt").toNumbers();
    }

    private static List<Integer> findExpenses(List<Integer> elementList, int elementCount){
        for (Integer element1 : elementList){
            for (Integer element2 : removeUsedElementsFromList(elementList, element1)){
                if (elementCount == 3){
                    for (Integer element3 : removeUsedElementsFromList(elementList, element2)){
                        if (element1 + element2 + element3 == SUM){
                            return Arrays.asList(element1, element2, element3);
                        }
                    }
                } else {
                    if (element1 + element2 == 2020){
                        return Arrays.asList(element1, element2);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<Integer> removeUsedElementsFromList(List<Integer> elementList, Integer element) {
        return elementList.subList(elementList.indexOf(element), elementList.size() - 1);
    }

    private static Integer multiply(List<Integer> result) {
        return result.stream().reduce(1, (a, b) -> a * b);
    }
}
