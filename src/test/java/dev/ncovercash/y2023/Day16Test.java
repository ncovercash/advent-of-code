package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day16Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day16();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/16/example.txt", "46", "51"),
      Arguments.of("y2023/16/input.txt", "6906", "7330")
    );
  }
}
