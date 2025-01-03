package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day20Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day20();
  }

  static List<Arguments> testCases() {
    return List.of(
      // line 1 is # of steps to save
      Arguments.of("y2024/20/example.txt", "1", "285"),
      Arguments.of("y2024/20/input.txt", "1485", "1027501")
    );
  }
}
