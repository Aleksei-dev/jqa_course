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
              .withMobilePhone("+372888885").withWorkPhone("+4323232").withHomePhone("9838389")
              .withFirstEmail("a@mail.ru").withSecondEmail("bc@d.com").withThirdEmail("efg@alpe.it"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData editedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(editedContact.getId()).withFirstname("Abc").withLastname("Def").withAddress("Moon 3")
            .withMobilePhone("+37288881126").withWorkPhone("+4323232").withHomePhone("9838389")
            .withFirstEmail("a@mail.ru").withSecondEmail("bc@d.com").withThirdEmail("efg@alpe.it");
    app.contact().modify(contact);
    assertEquals(app.contact().getContactCount(), before.size());
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(contact).withAdded(contact)));
  }
}
