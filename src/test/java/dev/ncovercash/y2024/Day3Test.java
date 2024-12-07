package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day3Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day3();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/3/example.txt", "161", "skip"),
      Arguments.of("y2024/3/example2.txt", "skip", "48"),
      Arguments.of("y2024/3/input.txt", "174336360", "88802350")
    );
  }
}
