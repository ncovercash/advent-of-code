package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day7Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day7();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/7/example.txt", "6440", "5905"),
      Arguments.of("y2023/7/input.txt", "248453531", "248781813")
    );
  }
}
