package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day1Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day1();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/1/example.txt", "142", "skip"),
      Arguments.of("y2023/1/example2.txt", "skip", "281"),
      Arguments.of("y2023/1/input.txt", "55172", "54925")
    );
  }
}
