package advent.of.code._2020;

import java.util.Arrays;
import java.util.List;

public class Day3_TreeCounter {

    public Day3_TreeCounter(){
        first();
        second();
    }

    static void first() {
        List<String> treeMap = getTreeMap();
        Slope slope = new Slope(3, 1);
        int result = countTrees(treeMap, slope);
        System.out.println(String.format("Day 3.1. %s, answer = encounter %d trees", slope, result));
    }

    static void second() {
        List<String> treeMap = getTreeMap();
        List<Slope> slopes = Arrays.asList(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2));
        Integer answer = slopes.stream()
                .map(slope -> {
                    int count = countTrees(treeMap, slope);
                    System.out.println(String.format(" - %s, answer = encounter %d trees", slope, count));
                    return count;
                })
                .reduce(1, (a, b) -> a * b);
        System.out.println(String.format("Day 3.2. Multiplied trees -> answer = %d", answer));
    }

    private static int countTrees(List<String> treeMap, Slope slope) {
        int trees = 0;
        int count = 1;
        for (int i = slope.down; i < treeMap.size(); i = i + slope.down) {
            int place = slope.right * count % treeMap.get(i).length();
            if (treeMap.get(i).charAt(place) == '#'){
                trees++;
            }
            count++;
        }
        return trees;
    }

    @SuppressWarnings("ConstantConditions")
    private static List<String> getTreeMap() {
        return InputReader.readInput("/tree-map.txt").getInput();
    }

    static class Slope {
        int right;
        int down;

        public Slope(int right, int down) {
            this.right = right;
            this.down = down;
        }

        @Override
        public String toString() {
            return "Slope [" +
                    "right=" + right +
                    ", down=" + down +
                    ']';
        }
    }
}
