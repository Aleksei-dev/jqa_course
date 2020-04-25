package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("Abc")
              .withLastname("Def")
              .withAddress("Moon 2")
              .withHomePhone("12344554322")
              .withFirstEmail("email@t.com"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData editedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(editedContact.getId())
            .withFirstname("Abo")
            .withLastname("Def")
            .withAddress("Moon 3")
            .withHomePhone("9999999922")
            .withFirstEmail("email@t.com");
    app.contact().modify(contact);
    assertEquals(app.contact().getContactCount(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
  }
}
