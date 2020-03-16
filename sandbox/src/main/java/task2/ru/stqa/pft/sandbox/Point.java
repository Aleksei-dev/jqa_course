package task2.ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }
 public double distance(Point p2){
    return Math.floor((Math.sqrt(square(p2.x - this.x) + square(p2.y - this.y)))*100)/100;
  }
  /** Функция возведения в квадрат числа. спользуется вместо Math.pow().
  По причине того, что Math.pow() не получилось использовать с ключевым словом this. **/
  static double square(double a){
    double result = a*a;
    return result;
  }
}
