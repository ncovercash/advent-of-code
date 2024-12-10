package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day9 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<Character> input = InputUtils.getCharacterArray(filename).get(0);

    List<BlockSpan> disk = new ArrayList<>();

    boolean isFile = true;
    int fileNum = 0;

    for (char c : input) {
      if (isFile) {
        disk.add(new BlockSpan(c - '0', fileNum));
        fileNum++;
      } else {
        disk.add(new BlockSpan(c - '0', null));
      }

      isFile = !isFile;
    }

    int forwardIndex = 0;
    int backwardIndex = disk.size() - 1;

    while (
      backwardIndex > 0 &&
      forwardIndex < disk.size() &&
      forwardIndex < backwardIndex
    ) {
      BlockSpan toDefragment = disk.get(backwardIndex);

      // skip past free spaces
      while (toDefragment.id() == null) {
        backwardIndex--;
        toDefragment = disk.get(backwardIndex);
      }

      BlockSpan toFill = disk.get(forwardIndex);
      // skip past non-free spaces
      while (toFill.id() != null) {
        forwardIndex++;
        toFill = disk.get(forwardIndex);
      }

      int spacesToFill = Math.min(toDefragment.width(), toFill.width());
      // more to move than we can fit
      if (toDefragment.width() > toFill.width()) {
        disk.set(
          backwardIndex,
          new BlockSpan(toDefragment.width() - spacesToFill, toDefragment.id())
        );
        disk.set(
          forwardIndex,
          new BlockSpan(toFill.width(), toDefragment.id())
        );
        // we don't have room for more, so we'll re-run this
        // we also don't care about cleaning up free space at the end
        backwardIndex++;
      } else if (toDefragment.width() < toFill.width()) {
        disk.set(forwardIndex, new BlockSpan(spacesToFill, toDefragment.id()));
        disk.set(backwardIndex, new BlockSpan(toDefragment.width(), null));

        // we have room for more after our block
        disk.add(
          forwardIndex + 1,
          new BlockSpan(toFill.width() - spacesToFill, null)
        );
      } else {
        disk.set(forwardIndex, new BlockSpan(spacesToFill, toDefragment.id()));
        disk.set(backwardIndex, new BlockSpan(spacesToFill, null));
      }

      forwardIndex++;
      backwardIndex--;
    }

    long sum = 0;
    int i = 0;
    for (BlockSpan block : disk) {
      if (block.id() == null) {
        i += block.width();
        continue;
      }

      for (int j = 0; j < block.width(); j++) {
        sum += (i + j) * block.id();
      }
      i += block.width();
    }

    return "" + sum;
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<Character> input = InputUtils.getCharacterArray(filename).get(0);

    List<BlockSpan> disk = new ArrayList<>();

    boolean isFile = true;
    int fileNum = 0;

    for (char c : input) {
      if (isFile) {
        disk.add(new BlockSpan(c - '0', fileNum));
        fileNum++;
      } else {
        disk.add(new BlockSpan(c - '0', null));
      }

      isFile = !isFile;
    }

    int backwardIndex = disk.size() - 1;

    while (backwardIndex > 0) {
      BlockSpan toDefragment = disk.get(backwardIndex);

      // skip past free spaces
      while (toDefragment.id() == null) {
        backwardIndex--;
        toDefragment = disk.get(backwardIndex);
      }

      int forwardIndex = 0;
      BlockSpan toFill = disk.get(forwardIndex);
      // skip past non-free spaces
      try {
        while (toFill.id() != null || toFill.width() < toDefragment.width()) {
          forwardIndex++;
          toFill = disk.get(forwardIndex);
        }
      } catch (IndexOutOfBoundsException e) {
        // i am too lazy to add proper bound checks above, lol
        backwardIndex--;
        continue;
      }

      if (forwardIndex >= backwardIndex) {
        backwardIndex--;
        continue;
      }

      if (toDefragment.width() < toFill.width()) {
        disk.set(
          forwardIndex,
          new BlockSpan(toDefragment.width(), toDefragment.id())
        );
        disk.set(backwardIndex, new BlockSpan(toDefragment.width(), null));

        // we have room for more after our block
        disk.add(
          forwardIndex + 1,
          new BlockSpan(toFill.width() - toDefragment.width(), null)
        );
      } else {
        disk.set(
          forwardIndex,
          new BlockSpan(toDefragment.width(), toDefragment.id())
        );
        disk.set(backwardIndex, new BlockSpan(toDefragment.width(), null));
      }

      backwardIndex--;
    }

    long sum = 0;
    int i = 0;
    for (BlockSpan block : disk) {
      if (block.id() == null) {
        i += block.width();
        continue;
      }

      for (int j = 0; j < block.width(); j++) {
        sum += (i + j) * block.id();
      }
      i += block.width();
    }

    return "" + sum;
  }

  private void printDisk(List<BlockSpan> disk) {
    log.info(
      "{}",
      disk.stream().map(BlockSpan::toString).reduce("", (a, b) -> a + b)
    );
  }

  private record BlockSpan(int width, Integer id) {
    @Override
    public String toString() {
      return (id == null ? "." : id.toString()).repeat(width);
    }
  }
}
