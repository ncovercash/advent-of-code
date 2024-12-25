package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day11 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    Map<String, Long> stones = Arrays
      .stream(InputUtils.getString(filename).split(" "))
      .collect(
        Collectors.toMap(k -> k, v -> 1L, (a, b) -> a + b, HashMap::new)
      );

    for (int i = 0; i < 25; i++) {
      stones = iterate(stones);
    }

    return (
      stones.values().stream().reduce(0L, (a, b) -> a + b, (a, b) -> a + b) + ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    Map<String, Long> stones = Arrays
      .stream(InputUtils.getString(filename).split(" "))
      .collect(
        Collectors.toMap(k -> k, v -> 1L, (a, b) -> a + b, HashMap::new)
      );

    for (int i = 0; i < 75; i++) {
      stones = iterate(stones);
    }

    return (
      stones.values().stream().reduce(0L, (a, b) -> a + b, (a, b) -> a + b) + ""
    );
  }

  private static Map<String, Long> iterate(Map<String, Long> stones) {
    Map<String, Long> result = new HashMap<>();

    for (Map.Entry<String, Long> entry : stones.entrySet()) {
      String key = entry.getKey();
      long frequency = entry.getValue();

      if ("0".equals(key)) {
        result.put("1", result.getOrDefault("1", 0L) + frequency);
      } else if (key.length() % 2 == 0) {
        String key1 = key.substring(0, key.length() / 2);
        String key2 = new BigInteger(key.substring(key.length() / 2))
          .toString();

        result.put(key1, result.getOrDefault(key1, 0L) + frequency);
        result.put(key2, result.getOrDefault(key2, 0L) + frequency);
      } else {
        String newKey = new BigInteger(key)
          .multiply(BigInteger.valueOf(2024L))
          .toString();
        result.put(newKey, result.getOrDefault(newKey, 0L) + frequency);
      }
    }

    return result;
  }
}
