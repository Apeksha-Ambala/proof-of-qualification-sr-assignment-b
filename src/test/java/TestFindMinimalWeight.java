import com.example.Edge;
import com.example.FindMinimalWeight;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a test class for finding minimal weight in a weighted graph.
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */
@PrepareForTest
public class TestFindMinimalWeight {

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testMinimalWeightOfGraph() throws Exception {
    String expectedResult = "{banana=0.6, plum=0.9, apple=0.0, cherry=0.8, pear=0.1, mango=0.3}";
    HashMap<Integer, String> map = new HashMap<>();
    HashMap<String, Float> resultMap = new HashMap<>();

    ArrayList<Edge>[] inputGraph = new ArrayList[7];
    for (int i = 0; i < inputGraph.length; i++) {
      inputGraph[i] = new ArrayList<>();
    }

    map.put(0, "apple");
    map.put(1, "pear");
    map.put(2, "banana");
    map.put(3, "cherry");
    map.put(4, "plum");
    map.put(5, "mango");

    inputGraph[0].add(new Edge(0, 0.1, 1));
    inputGraph[0].add(new Edge(0, 0.3, 5));

    inputGraph[1].add(new Edge(1, 0.5, 2));
    inputGraph[1].add(new Edge(1, 0.7, 3));
    inputGraph[1].add(new Edge(1, 0.8, 4));

    inputGraph[2].add(new Edge(2, 0.9, 4));
    inputGraph[2].add(new Edge(2, 0.6, 5));

    FindMinimalWeight findMinimalWeight = PowerMockito.spy(new FindMinimalWeight());

    resultMap = Whitebox.invokeMethod(findMinimalWeight, "getMinimalWeight", inputGraph, map);

    // System.out.println("resultMap=" + resultMap);

    Assertions.assertNotNull(resultMap);
    Assertions.assertEquals(expectedResult, resultMap.toString());
  }
}
