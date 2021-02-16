package de.task;

/**
 * @author RI
 *
 */
public class Interval implements Comparable<Interval> {

  private final int start;
  private final int end;

  public Interval(int start, int end) {

    if (start > end) {
      throw new IllegalArgumentException("start > end");
    }

    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return String.format("[%s,%s]", start, end);
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  @Override
  public int compareTo(Interval o) {
    return IntervalUtils.naturalOrder.compare(this, o);
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Interval)) {
      return false;
    }

    Interval interval = (Interval) obj;
    return this.compareTo(interval) == 0;
  }

}
