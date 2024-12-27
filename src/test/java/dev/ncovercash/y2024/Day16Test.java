package dev.ncovercash.y2024;

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
      Arguments.of("y2024/16/example0.txt", "3009", "skip"),
      Arguments.of("y2024/16/example1.txt", "7036", "skip"),
      Arguments.of("y2024/16/example2.txt", "11048", "skip"),
      Arguments.of("y2024/16/input.txt", "85432", "skip")
    );
  }
}
