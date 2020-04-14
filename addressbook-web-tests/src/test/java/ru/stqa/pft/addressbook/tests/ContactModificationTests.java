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
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("Abc").withLastname("Def").withAddress("Moon 2")
              .withMobile("+372888885").withMailbox("mymail@mail.com"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData editedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(editedContact.getId()).withFirstname("Abc").withLastname("Def").withAddress("Moon 3")
            .withMobile("+37288881126").withMailbox("mymail@mail.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(contact).withAdded(contact)));
  }
}
