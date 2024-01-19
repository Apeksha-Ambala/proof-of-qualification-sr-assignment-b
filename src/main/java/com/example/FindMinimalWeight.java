package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main Class for finding minimal weight in a weighted graph.
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */

/** Takes Input from file and process it to find minimal weight in a weighted graph */
public class FindMinimalWeight {
  @SuppressWarnings({"unchecked"})
  public static void main(String[] args) {
    BufferedReader reader;
    HashMap<Integer, String> map = new HashMap<>();
    HashMap<String, Float> resultMap;

    int arrayLength = 0;

    try {
      String path = Paths.get("").toAbsolutePath().toString();
      String filePath = path + "/src/main/resources/input.txt";

      // Fetch number of lines from file
      LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath));
      lineNumberReader.skip(Long.MAX_VALUE);
      int lineCount = lineNumberReader.getLineNumber();
      lineNumberReader.close();

      // Initialize ArrayList
      ArrayList<Edge>[] inputGraph = new ArrayList[lineCount];
      for (int i = 0; i < inputGraph.length; i++) {
        inputGraph[i] = new ArrayList<>();
      }

      reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();

      // Read data from file
      while (line != null) {
        int startIndex, endIndex;
        line = line.trim().replaceAll("\\s{2,}", " ");

        String[] splitedInput = splitString(line);

        String startNode = splitedInput[0].replaceAll("\"", "");
        String weight = splitedInput[1];
        String endNode = splitedInput[2].replaceAll("\"", "");

        // Check if startTag has value in map
        if (!map.containsValue(startNode)) {
          map.put(arrayLength, startNode);
          startIndex = arrayLength;
          arrayLength++;
        } else {
          startIndex = getKeyFromMap(map, startNode);
        }

        // Check if endTag has value in map
        if (!map.containsValue(endNode)) {
          map.put(arrayLength, endNode);
          endIndex = arrayLength;
          arrayLength++;
        } else {
          endIndex = getKeyFromMap(map, endNode);
        }

        inputGraph[startIndex].add(new Edge(startIndex, Double.parseDouble(weight), endIndex));

        // read next line
        line = reader.readLine();
      }

      reader.close();

      String sourceNodeValue = map.get(inputGraph[0].get(0).getStartTag());

      resultMap = getMinimalWeight(inputGraph, map);

      for (String key : resultMap.keySet()) {
        System.out.println(
            "Minimal weight from source ("
                + sourceNodeValue
                + ") to vertex ("
                + key
                + "): "
                + resultMap.get(key));
      }

      for (Integer key : map.keySet()) {
        if (!resultMap.containsKey(map.get(key))) {
          System.out.println(
              "Path from source ("
                  + sourceNodeValue
                  + ") doesn't exist to vertex ("
                  + map.get(key)
                  + ")");
        }
      }

    } catch (Exception e) {
      System.out.println("Exception occurred. Aborting.");
      e.printStackTrace();

      System.exit(0);
    }
  }

  /**
   * This Method is used to get the minimal weight
   *
   * @param graph - constructed graph
   * @param map - map
   * @return hashmap containing minimal weights
   */
  private static HashMap<String, Float> getMinimalWeight(
      ArrayList<Edge>[] graph, HashMap<Integer, String> map) {
    HashMap<String, Float> resultMap = new HashMap<>();
    boolean[] visitedNodes = new boolean[graph.length];
    int mainNode = 0;

    PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
    priorityQueue.add(new Pair(mainNode, 0));

    while (priorityQueue.size() > 0) {
      Pair topPair = priorityQueue.remove();

      // checking if vertex already visited
      if (visitedNodes[topPair.getVertex()]) {
        continue;
      }
      // marking true if  vertex not visited
      visitedNodes[topPair.getVertex()] = true;

      // Printing the vertex and weight to reach that vertex

      String calcWeight = String.valueOf((topPair.getWeightTillNow() / 100));

      //            System.out.println(
      //                "Vertex :"
      //                    + " "
      //                    + map.get(topPair.getVertex())
      //                    + " & "
      //                    + "Weight so far :"
      //                    + " "
      //                    +calcWeight);
      resultMap.put(map.get(topPair.getVertex()), (float) (topPair.getWeightTillNow() / 100));

      // adding all the unvisited neighbor of vertex in queue
      for (Edge edge : graph[topPair.getVertex()]) {
        if (!visitedNodes[edge.getEndTag()]) {
          priorityQueue.add(
              new Pair(edge.getEndTag(), (int) (topPair.getWeightTillNow() + edge.getWeight())));
        }
      }
    }
    return resultMap;
  }

  /**
   * This Method is used to get key from map
   *
   * @param map - input map
   * @param value - value of key
   * @return key where value matches
   */
  private static int getKeyFromMap(Map<Integer, String> map, String value) {
    for (int key : map.keySet()) {
      if (map.get(key).equals(value)) {
        return key;
      }
    }
    return -1;
  }

  /**
   * This Method is used to split the string
   *
   * @param line - input line
   * @return split string in array
   */
  private static String[] splitString(String line) {
    List<String> list = new ArrayList<String>();
    Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
    while (m.find()) list.add(m.group(1));

    return list.toArray(new String[0]);
  }
}
