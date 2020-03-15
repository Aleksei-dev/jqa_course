package task2.ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }
 public double distance(Point p2){
    return Math.sqrt(square(p2.x - this.x) + square(p2.y - this.y));
  }
  /* Функция возведения в квадрат числа. Используется вместо Math.pow().
  По причине того, что Math.pow() не получилось использовать с ключевым словом this. */
  static double square(double a){
    double result = a*a;
    return result;
  }
}
