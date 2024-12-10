package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day5Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day5();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/5/example.txt", "143", "123"),
      Arguments.of("y2024/5/input.txt", "6267", "5184")
    );
  }
}
