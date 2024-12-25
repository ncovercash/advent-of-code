package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day13Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day13();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/13/example.txt", "480", "875318608908"),
      Arguments.of("y2024/13/input.txt", "37686", "77204516023437")
    );
  }
}
