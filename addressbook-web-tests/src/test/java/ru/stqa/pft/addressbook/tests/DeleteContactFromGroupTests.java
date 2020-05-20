package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("Abc")
              .withLastname("Def")
              .withAddress("Moon 2")
              .withHomePhone("9838389")
              .withFirstEmail("a@mail.ru"), true);
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create((new GroupData().withName("test1").withHeader("test2").withFooter("test3")));
    }
    Contacts contactsInGroups = getContactsInGroups();
    if(contactsInGroups.size() == 0){
      ContactData contact = app.contact().create(new ContactData()
      .withFirstname("test").withLastname("test1").inGroup(app.db().groups().iterator().next()), true);
      contactsInGroups.add(contact);
    }
  }

  @Test
  public void  testDeleteContactFromGroup(){
    app.goTo().homePage();
    ContactData contact = contactInGroup();
    GroupData group = contact.getGroups().iterator().next();
    int id = contact.getId();
    Groups before = contact.getGroups();
    app.contact().selectGroupFromList(group.getName());
    app.contact().selectContactById(id);
    app.contact().removeContactFromGroup();
    app.contact().goToGroupAddedPage(group.getId());

    ContactData recentlyTestedContact = contact;
    contact = app.db().contacts().stream()
            .filter((c) -> c.getId() == recentlyTestedContact.getId()).findFirst().get();
    Groups after = contact.getGroups();
    assertThat(after, equalTo(before.without(group)));
  }

  public Contacts getContactsInGroups(){
    Contacts contacts = app.db().contacts();
    Contacts contactsInGroups = contacts.stream()
            .filter((c) -> c.getGroups().size() > 0).collect(toCollection(Contacts::new));
    return contactsInGroups;
  }

  public ContactData contactInGroup(){
    Contacts contactsInGroups = getContactsInGroups();
    ContactData contact = contactsInGroups.iterator().next();
    return contact;
  }
}
