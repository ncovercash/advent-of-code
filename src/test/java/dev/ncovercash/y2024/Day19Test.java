package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day19Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day19();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/19/example.txt", "6", "16"),
      Arguments.of("y2024/19/input.txt", "238", "635018909726691")
    );
  }
}
