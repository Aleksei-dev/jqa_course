package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Aleksei", "Kovaltsuk", "Tallinn", "+37250000005", "mail@test.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Aleksei", "Kovaltsuk", "Tallinn", "+37250000005", "mail@test.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnHome();
    app.logout();
  }
}
