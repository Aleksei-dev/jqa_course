package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UsersHelper extends HelperBase{
  public UsersHelper(ApplicationManager app) {
    super(app);
  }

  public int selectUserById(int id) {
    wd.findElement(By.cssSelector("a[href='" + "manage_user_edit_page.php?user_id=" + id + "']")).click();
    return id;
  }
}
