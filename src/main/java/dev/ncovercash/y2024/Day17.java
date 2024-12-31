package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day17 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> input = InputUtils.getLines(filename);

    long regA = Long.parseLong(input.get(0).split(": ")[1]);
    long regB = Long.parseLong(input.get(1).split(": ")[1]);
    long regC = Long.parseLong(input.get(2).split(": ")[1]);

    List<Integer> program = Arrays
      .stream(input.get(4).split(": ")[1].split(","))
      .map(Integer::parseInt)
      .toList();

    List<Integer> output = execute(regA, regB, regC, program);

    return output
      .stream()
      .map(i -> i.toString())
      .collect(Collectors.joining(","));
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> input = InputUtils.getLines(filename);

    long regB = Long.parseLong(input.get(1).split(": ")[1]);
    long regC = Long.parseLong(input.get(2).split(": ")[1]);

    List<Integer> program = Arrays
      .stream(input.get(4).split(": ")[1].split(","))
      .map(Integer::parseInt)
      .toList();

    // our goal is to calculate every possible way to get this possibility
    Set<RegisterA>[] possibilities = new Set[8];
    for (int i = 0; i < 8; i++) {
      possibilities[i] = new HashSet<>();
    }

    // we can shift by max 8, so 11 bits should be sufficient to compute all possibilities
    for (int i = 0; i < 2048; i++) {
      RegisterA register = new RegisterA(i);
      // there is a way to calculate this more efficiently, but this should be sufficient.
      // e.g. my input had output = (i % 8) ^ 3 ^ ((i >> (i ^ 5)) % 8)
      possibilities[(int) (long) execute(i, regB, regC, program).get(0)].add(
          register.stripForInitialCalculation()
        );
    }

    log.info(
      "Calculated possibilities for each possible value, found the following solutions:"
    );
    for (int i = 0; i < 8; i++) {
      log.info(
        "Output {} ({}): {}",
        i,
        possibilities[i].size(),
        possibilities[i].stream()
          .map(RegisterA::getValue)
          .map(l -> Long.toString(l))
          .collect(Collectors.joining(", "))
      );
    }

    List<RegisterA> solutions = new ArrayList<>();
    solutions.addAll(possibilities[program.get(0)]);

    for (int i = 1; i < program.size(); i++) {
      solutions = solutions.stream().map(RegisterA::downshift).toList();

      int desiredValue = program.get(i);
      solutions =
        solutions
          .stream()
          .flatMap(solution ->
            possibilities[desiredValue].stream()
              .filter(possibility -> possibility.isCompatibleWith(solution))
              .map(possibility -> possibility.combine(solution))
          )
          .toList();
    }

    log.info(
      "Found {} solutions that start with desired output",
      solutions.size()
    );

    List<Long> values = solutions
      .stream()
      .map(RegisterA::normalize)
      .map(RegisterA::getValue)
      .filter(value ->
        execute(value, regB, regC, program).size() == program.size()
      )
      .toList();

    log.info(
      "Found {} solutions that exactly equal desired output",
      values.size()
    );

    return values.stream().mapToLong(l -> l).min().orElseThrow() + "";
  }

  private record RegisterA(Map<Integer, Boolean> bits) {
    public RegisterA() {
      this(new HashMap<>());
    }

    public RegisterA(int value) {
      this();
      if (value >= 2048) {
        throw new IllegalArgumentException("Value too large: " + value);
      }
      for (int i = 0; i < 11; i++) {
        bits.put(i, (value & (1 << i)) != 0);
      }
    }

    public int getBitAtPosition(int index) {
      return Boolean.TRUE.equals(bits.getOrDefault(index, false)) ? 1 : 0;
    }

    public int getOctalAtPosition(int index) {
      return (
        this.getBitAtPosition(index) +
        2 *
        this.getBitAtPosition(index + 1) +
        4 *
        this.getBitAtPosition(index + 2)
      );
    }

    public long getValue() {
      return bits
        .entrySet()
        .stream()
        .filter(Map.Entry::getValue)
        .filter(e -> e.getKey() >= 0)
        .mapToLong(e -> 1L << e.getKey())
        .sum();
    }

    public RegisterA downshift() {
      Map<Integer, Boolean> newBits = new HashMap<>();
      bits.forEach((k, v) -> newBits.put(k - 3, v));
      return new RegisterA(newBits);
    }

    public RegisterA normalize() {
      int lowest = bits.keySet().stream().min(Integer::compareTo).orElse(0);
      if (lowest < 0) {
        Map<Integer, Boolean> newBits = new HashMap<>();
        bits.forEach((k, v) -> newBits.put(k - lowest, v));
        return new RegisterA(newBits);
      } else {
        return this;
      }
    }

    public boolean isCompatibleWith(RegisterA that) {
      return that.bits
        .entrySet()
        .stream()
        .allMatch(e ->
          !this.bits.containsKey(e.getKey()) ||
          this.bits.get(e.getKey()).equals(e.getValue())
        );
    }

    public RegisterA combine(RegisterA that) {
      Map<Integer, Boolean> newBits = new HashMap<>(this.bits);
      that.bits.forEach(newBits::put);
      return new RegisterA(newBits);
    }

    // for cases created via the int constructor, we want to keep
    // only the bits used for calculation
    public RegisterA stripForInitialCalculation() {
      int baseValue = this.getOctalAtPosition(0);
      int targetPosition = baseValue ^ 5;

      return new RegisterA(
        this.bits.entrySet()
          .stream()
          .filter(e ->
            e.getKey() == 0 ||
            e.getKey() == 1 ||
            e.getKey() == 2 ||
            e.getKey() == targetPosition ||
            e.getKey() == targetPosition + 1 ||
            e.getKey() == targetPosition + 2
          )
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
      );
    }
  }

  private List<Integer> execute(
    long regA,
    long regB,
    long regC,
    List<Integer> program
  ) {
    int pc = 0;

    List<Integer> output = new ArrayList<>();

    while (pc < program.size()) {
      int opcode = program.get(pc);
      int operand = program.get(pc + 1);

      boolean jumped = false;

      switch (opcode) {
        case 0 -> regA =
          (long) (regA / Math.pow(2, combo(operand, regA, regB, regC)));
        case 1 -> regB ^= operand;
        case 2 -> regB = combo(operand, regA, regB, regC) % 8;
        case 3 -> {
          if (regA != 0) {
            pc = operand;
            jumped = true;
          }
        }
        case 4 -> regB = regB ^ regC;
        case 5 -> output.add((int) (combo(operand, regA, regB, regC) % 8));
        case 6 -> regB =
          (long) (regA / Math.pow(2, combo(operand, regA, regB, regC)));
        case 7 -> regC =
          (long) (regA / Math.pow(2, combo(operand, regA, regB, regC)));
      }

      if (!jumped) {
        pc += 2;
      }
    }

    return output;
  }

  private long combo(int operand, long regA, long regB, long regC) {
    return switch (operand) {
      case 0, 1, 2, 3 -> operand;
      case 4 -> regA;
      case 5 -> regB;
      case 6 -> regC;
      default -> throw new IllegalArgumentException(
        "Invalid operand: " + operand
      );
    };
  }
}
