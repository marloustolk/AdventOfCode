package advent.of.code._2020;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day5_SeatDecoder {

    public Day5_SeatDecoder(){
        first();
        second();
    }

    static void first() {
        List<BoardingPass> boardingPasses = getBoardingPasses().stream().map(BoardingPass::new).collect(toList());
        BoardingPass boardingPass = getHighestSeatId(boardingPasses);
        System.out.println(String.format("Day 5.1. %s, answer = %s is highest seatId", boardingPass, boardingPass.seatId));
    }

    static void second() {
        int myPass = findMissingBoardingPass();
        System.out.println(String.format("Day 5.2. [number is not in list, but +1 and -1 are], answer = %s", myPass));
    }

    @SuppressWarnings("ConstantConditions")
    private static List<String> getBoardingPasses() {
        return InputReader.readInput("/boarding-passes.txt").getInput();
    }

    private static BoardingPass getHighestSeatId(List<BoardingPass> boardingPasses) {
        return boardingPasses.stream()
                .max(Comparator.comparingInt(pass -> pass.seatId))
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int findMissingBoardingPass() {
        List<Integer> boardingPasses = getBoardingPasses().stream().map(string -> new BoardingPass(string).seatId).collect(toList());
        return IntStream.range(0, 838)
                .filter(i -> !boardingPasses.contains(i))
                .filter(i -> boardingPasses.contains(i + 1) && boardingPasses.contains(i - 1))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    static class BoardingPass {

        int row;

        int column;

        int seatId;

        public BoardingPass(String pass){
            convert(pass);
        }

        void convert(String pass) {
            row = Integer.parseInt(pass.substring(0, 7)
                    .replaceAll("F", "0")
                    .replaceAll("B", "1"), 2);
            column = Integer.parseInt(pass.substring(7)
                    .replaceAll("L", "0")
                    .replaceAll("R", "1"), 2);
            seatId = row * 8 + column;
        }

        @Override
        public String toString() {
            return "BoardingPass [" +
                    "row=" + row +
                    ", column=" + column +
                    ", seatId=" + seatId +
                    ']';
        }
    }
}
