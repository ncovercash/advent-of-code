package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day4Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day4();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/4/example.txt", "18", "9"),
      Arguments.of("y2024/4/input.txt", "2517", "1960")
    );
  }
}
