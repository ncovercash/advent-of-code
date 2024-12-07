package dev.ncovercash;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InputUtils {

  public static InputStream getClasspathFile(String filename) {
    return InputUtils.class.getClassLoader().getResourceAsStream(filename);
  }

  public static List<String> getLines(String filename) {
    InputStream in = InputUtils.getClasspathFile(filename);

    List<String> lines = new ArrayList<>();
    int lastNonEmpty = 0;

    try (Scanner scanner = new Scanner(in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lines.add(line);

        if (!line.isBlank()) {
          lastNonEmpty = lines.size();
        }
      }
    }

    return lines.subList(0, lastNonEmpty);
  }

  public static String getString(String filename) {
    InputStream in = InputUtils.getClasspathFile(filename);

    StringBuilder builder = new StringBuilder();

    try (Scanner scanner = new Scanner(in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        builder.append(line);
        builder.append("\n");
      }
    }

    return builder.toString().trim();
  }

  public static List<List<String>> getDelimitedLines(
    String filename,
    Pattern delimiter
  ) {
    return getLines(filename)
      .stream()
      .map(delimiter::split)
      .map(List::of)
      .toList();
  }

  public static List<List<Character>> getCharacterArray(String filename) {
    List<String> lines = getLines(filename);

    List<List<Character>> result = new ArrayList<>();
    for (String line : lines) {
      result.add(
        line
          .chars()
          .mapToObj(c -> (char) c)
          .collect(Collectors.toCollection(ArrayList::new))
      );
    }

    return result;
  }

  public static List<List<String>> getLinesByClump(String filename) {
    List<String> lines = InputUtils.getLines(filename);

    List<List<String>> collection = new ArrayList<>();
    List<String> clump = new ArrayList<>();

    for (String line : lines) {
      if (!line.isBlank()) {
        clump.add(line);
      } else {
        collection.add(clump);
        clump = new ArrayList<>();
      }
    }

    collection.add(clump);

    return collection.stream().filter(cl -> !cl.isEmpty()).toList();
  }
}
