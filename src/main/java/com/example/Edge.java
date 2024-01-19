package com.example;

/**
 * This Class contains variables required to edge
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Edge {
  private int startTag;
  private double weight;
  private int endTag;

  public Edge(int startTag, double weight, int endTag) {
    this.startTag = startTag;
    this.endTag = endTag;
    this.weight = weight * 100;
  }
}
