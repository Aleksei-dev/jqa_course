package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Aleksei", "Kovaltsuk", "Tallinn", "+37250000005", "mail@test.com", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHome();
    app.logout();
  }

}