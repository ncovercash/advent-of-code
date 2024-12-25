package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day12 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<Character>> farm = InputUtils.getCharacterArray(filename);

    boolean[][] assignedRegions = new boolean[farm.size()][farm.get(0).size()];

    int sum = 0;

    for (int r = 0; r < farm.size(); r++) {
      for (int c = 0; c < farm.get(0).size(); c++) {
        if (!assignedRegions[r][c]) {
          Geometry geometry = calculateRegion(
            farm,
            assignedRegions,
            r,
            c,
            new boolean[farm.size()][farm.get(0).size()]
          );
          sum += geometry.area() * geometry.perimeter();
        }
      }
    }

    return sum + "";
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<Character>> farm = InputUtils.getCharacterArray(filename);

    boolean[][] assignedRegions = new boolean[farm.size()][farm.get(0).size()];

    int sum = 0;

    for (int r = 0; r < farm.size(); r++) {
      for (int c = 0; c < farm.get(0).size(); c++) {
        if (!assignedRegions[r][c]) {
          boolean[][] region = new boolean[farm.size()][farm.get(0).size()];
          Geometry geometry = calculateRegion(
            farm,
            assignedRegions,
            r,
            c,
            region
          );
          int numSides = calculateSides(region);
          sum += geometry.area() * numSides;
        }
      }
    }

    return sum + "";
  }

  private Geometry calculateRegion(
    List<List<Character>> farm,
    boolean[][] assignedRegions,
    int r,
    int c,
    boolean[][] region
  ) {
    if (assignedRegions[r][c]) {
      return new Geometry(0, 0);
    }

    char ch = farm.get(r).get(c);
    assignedRegions[r][c] = true;
    region[r][c] = true;

    Geometry result = new Geometry(1, 0);

    if (r > 0 && farm.get(r - 1).get(c) == ch) {
      result =
        result.add(calculateRegion(farm, assignedRegions, r - 1, c, region));
    } else {
      result = result.addPerimeter();
    }

    if (r < farm.size() - 1 && farm.get(r + 1).get(c) == ch) {
      result =
        result.add(calculateRegion(farm, assignedRegions, r + 1, c, region));
    } else {
      result = result.addPerimeter();
    }

    if (c > 0 && farm.get(r).get(c - 1) == ch) {
      result =
        result.add(calculateRegion(farm, assignedRegions, r, c - 1, region));
    } else {
      result = result.addPerimeter();
    }

    if (c < farm.get(0).size() - 1 && farm.get(r).get(c + 1) == ch) {
      result =
        result.add(calculateRegion(farm, assignedRegions, r, c + 1, region));
    } else {
      result = result.addPerimeter();
    }

    return result;
  }

  private int calculateSides(boolean[][] points) {
    int minR = Integer.MAX_VALUE;
    int maxR = Integer.MIN_VALUE;
    int minC = Integer.MAX_VALUE;
    int maxC = Integer.MIN_VALUE;

    for (int r = 0; r < points.length; r++) {
      for (int c = 0; c < points[0].length; c++) {
        if (points[r][c]) {
          minR = Math.min(minR, r);
          maxR = Math.max(maxR, r);
          minC = Math.min(minC, c);
          maxC = Math.max(maxC, c);
        }
      }
    }

    int count = 0;

    for (int r = minR; r <= maxR; r++) {
      boolean activeTop = false;
      boolean activeBottom = false;

      for (int c = minC; c <= maxC; c++) {
        if (points[r][c]) {
          if (r == 0 || (r > 0 && !points[r - 1][c])) {
            if (!activeTop) {
              activeTop = true;
              count++;
            }
          } else {
            activeTop = false;
          }
          if (
            r == points.length - 1 ||
            (r < points.length - 1 && !points[r + 1][c])
          ) {
            if (!activeBottom) {
              activeBottom = true;
              count++;
            }
          } else {
            activeBottom = false;
          }
        } else {
          activeTop = false;
          activeBottom = false;
        }
      }
    }

    for (int c = minC; c <= maxC; c++) {
      boolean activeLeft = false;
      boolean activeRight = false;

      for (int r = minR; r <= maxR; r++) {
        if (points[r][c]) {
          if (c == 0 || (c > 0 && !points[r][c - 1])) {
            if (!activeLeft) {
              activeLeft = true;
              count++;
            }
          } else {
            activeLeft = false;
          }
          if (
            c == points[0].length - 1 ||
            (c < points[0].length - 1 && !points[r][c + 1])
          ) {
            if (!activeRight) {
              activeRight = true;
              count++;
            }
          } else {
            activeRight = false;
          }
        } else {
          activeLeft = false;
          activeRight = false;
        }
      }
    }

    return count;
  }

  private record Geometry(int area, int perimeter) {
    public Geometry add(Geometry other) {
      return new Geometry(area + other.area, perimeter + other.perimeter);
    }
    public Geometry addPerimeter() {
      return new Geometry(area, perimeter + 1);
    }
  }
}
