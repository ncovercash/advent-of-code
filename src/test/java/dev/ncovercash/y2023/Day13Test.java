package dev.ncovercash.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day13Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day13();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/13/example.txt", "405", "400"),
      Arguments.of("y2023/13/input.txt", "33780", "23479")
    );
  }
}
