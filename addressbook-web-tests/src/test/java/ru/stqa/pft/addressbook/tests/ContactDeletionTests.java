package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Aleksei", "Kovaltsuk", "Tallinn", "+37250000005", "mail@test.com", "test1"), true);
    }
  app.getContactHelper().selectContact();
  app.getContactHelper().deleteContact();
  app.getContactHelper().alertYes();
//  app.getContactHelper().returnHome();
  }
}
