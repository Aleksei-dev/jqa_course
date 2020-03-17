package task2.ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testArea(){
    Point p1 = new Point(9,5);
    Point p2 = new Point(3,9);
    Assert.assertEquals(p1.distance(p2), 7.21);
  }
}
