package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("Abc").withLastname("Def").withAddress("Moon 2")
              .withMobile("+372888885").withMailbox("mymail@mail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData editedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(editedContact.getId()).withFirstname("Abc").withLastname("Def").withAddress("Moon 2")
            .withMobile("+37288881125").withMailbox("mymail@mail.com").withGroup("test1");
    app.contact().modify(editedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(editedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
