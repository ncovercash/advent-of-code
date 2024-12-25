package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day11Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day11();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/11/example.txt", "55312", "65601038650482"),
      Arguments.of("y2024/11/input.txt", "203953", "242090118578155")
    );
  }
}
