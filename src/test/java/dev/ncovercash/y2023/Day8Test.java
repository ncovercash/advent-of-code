package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day8Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day8();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/8/example1a.txt", "2", "skip"),
      Arguments.of("y2023/8/example1b.txt", "6", "skip"),
      Arguments.of("y2023/8/example2.txt", "skip", "6"),
      Arguments.of("y2023/8/input.txt", "17873", "15746133679061")
    );
  }
}
