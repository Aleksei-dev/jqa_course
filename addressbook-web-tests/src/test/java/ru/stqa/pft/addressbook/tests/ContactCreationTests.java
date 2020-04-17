package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().all().size() == 0){
      app.group().create((new GroupData().withName("test2").withHeader("test3").withFooter("test4")));
    }
  }

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/Jellyfish.jpg");
    ContactData contact = new ContactData()
            .withFirstname("Abc").withLastname("Def").withAddress("Moon 2")
            .withMobilePhone("+372000005").withWorkPhone("+4323232").withHomePhone("9838389")
            .withFirstEmail("a@mail.ru").withSecondEmail("bc@d.com").withThirdEmail("efg@alpe.it")
            .withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/Jellyfish.jpg");
    ContactData contact = new ContactData()
            .withFirstname("Abc'").withLastname("Def").withAddress("Moon 2")
            .withMobilePhone("+372000005").withWorkPhone("+4323232").withHomePhone("9838389")
            .withFirstEmail("a@mail.ru").withSecondEmail("bc@d.com").withThirdEmail("efg@alpe.it").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}