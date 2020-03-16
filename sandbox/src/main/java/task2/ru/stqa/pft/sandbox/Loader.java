package task2.ru.stqa.pft.sandbox;

public class Loader {
  public static void main (String[] args) {
    Point p1 = new Point(8, 5);
    Point p2 = new Point(3, 1);

    System.out.println("Hello. The distance between two dots is " + p1.distance(p2) + " centimeters!");
  }

}
