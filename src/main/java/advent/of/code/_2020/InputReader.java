package advent.of.code._2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputReader {

    static Input readInput(String fileName) {
        Input input = new Input();
        try {
            File myObj = new File("src/main/resources/puzzle-input/_2020" + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                input.add(myReader.nextLine());
            }
            myReader.close();
            return input;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    static class Input {
        private final List<String> input = new ArrayList<>();

        void add(String line) {
            input.add(line);
        }

        public List<String> getInput() {
            return input;
        }

        public List<Integer> toNumbers() {
            return input.stream()
                    .map(line -> Arrays.stream(line.split(" ")))
                    .flatMap(Function.identity())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }

        public Map<String, List<String>> toMap(String delimiterKeyValue) {
            return input.stream()
                    .map(s -> s.split(delimiterKeyValue))
                    .collect(Collectors.groupingBy(policy -> policy[0],
                            Collectors.mapping(password -> password[1], Collectors.toList())));
        }

        public List<Map<String, String>> toListOfMaps(String delimiterKeyValue, String delimiterEntry, String delimiterMap) {
            return Arrays.stream(String.join(delimiterEntry, input).split(delimiterMap))
                    .map(person -> Arrays.stream(person.split(delimiterEntry))
                            .collect(Collectors.toMap(
                                    field -> field.split(delimiterKeyValue)[0],
                                    field -> field.split(delimiterKeyValue)[1])))
                    .collect(Collectors.toList());
        }
    }
}
