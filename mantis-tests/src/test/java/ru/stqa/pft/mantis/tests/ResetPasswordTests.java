package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    app.login().logIn("administrator");
    app.login().logInWithPass("root");
    app.navigationHelper().manage();
    app.navigationHelper().manageUsers();
    app.usersHelper().selectUserById(getUser().getId());
    app.usersHelper().resetPasswordBtn();
    String user = getUser().getName();
    String email = getUser().getEmail();
    String password = "test";
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String resetPasswordLink = findResetPasswordLink(mailMessages, email);
    app.registration().finish(resetPasswordLink, password);
    HttpSession session = app.newSession();
    assertTrue(session.login(user, password));
  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public UserData getUser(){
    Users users = app.db().users();
    return users.iterator().next();
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
