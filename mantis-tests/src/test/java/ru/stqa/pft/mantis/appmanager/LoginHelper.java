package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

  public LoginHelper (ApplicationManager app){
    super(app);
  }

  public void logIn(String username) {
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));
  }

  public void logInWithPass(String password) {
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

}
