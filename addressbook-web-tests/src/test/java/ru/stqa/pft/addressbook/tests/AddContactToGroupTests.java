package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Abc")
              .withLastname("Def")
              .withAddress("Moon 2")
              .withHomePhone("9838389")
              .withFirstEmail("a@mail.ru"), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create((new GroupData().withName("test1").withHeader("test2").withFooter("test3")));
    }
    ContactData contact = getContactToAddInGroup();
    if (contact == null){
      app.contact().create(new ContactData()
              .withFirstname("test").withLastname("test1"), true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    ContactData contact = getContactToAddInGroup();
    Groups groupsForContact = groupsForContact(contact);
    GroupData group = groupsForContact.iterator().next();
    Groups before = contact.getGroups();
    int id = contact.getId();
    app.contact().selectContactById(id);
    app.contact().addContactToGroup();
    app.goTo().goToAddedGroupPage();

    ContactData recentlyTestedContact = contact;
    contact = app.db().contacts().stream()
            .filter((c) -> c.getId() == recentlyTestedContact.getId()).findFirst().get();
    Groups after = contact.getGroups();
    assertThat(after, equalTo(before.withAdded(group)));
  }

  public Groups groupsForContact(ContactData contact){
    Groups groupForContact = new Groups();
    for (GroupData g : app.db().groups()){
      if (!contact.getGroups().contains(groupForContact)){
        groupForContact.add(g);
      }
    }
    return groupForContact;
  }

  public ContactData getContactToAddInGroup(){
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData c : contacts){
      if (c.getGroups().size() < groups.size()){
        return c;
      }
    }
    return null;
  }
}
