package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manage(){
    click(By.xpath("//span[contains(text(),\"Manage\")]"));
  }

  public void manageUsers(){
    click(By.xpath("//a[contains(text(),\"Manage Users\")]"));
  }
}
