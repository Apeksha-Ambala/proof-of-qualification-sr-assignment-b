package com.example;

import lombok.Getter;
import lombok.Setter;

/**
 * This Class contains variables required to pair
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
public class Pair implements Comparable<Pair> {
  private int vertex;
  private Double weightTillNow;

  Pair(int vertex, double weightTillNow) {
    this.vertex = vertex;
    this.weightTillNow = weightTillNow;
  }

  public int compareTo(Pair o) {
    return (int) (this.weightTillNow - o.weightTillNow);
  }
}
