package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
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
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    int groupsBefore = contact.getGroups().size();
    int groupId = app.db().groups().iterator().next().getId();
    app.contact().selectContactById(app.db().contacts().iterator().next().getId());
    if (contact.getGroups().size() == 0) {
      app.contact().addContactToGroup();
      app.goTo().goToAddedGroupPage();
    }
    if (contact.getGroups().iterator().next().getId() != app.db().groups().iterator().next().getId()) {
      app.contact().addContactToGroup();
      app.goTo().goToAddedGroupPage();
    }
    else if (contact.getGroups().iterator().next().getId() == app.db().groups().iterator().next().getId()) {
      app.goTo().groupPage();
      app.group().create((new GroupData().withName("test1").withHeader("test2").withFooter("test3")));
      app.goTo().homePage();
      app.contact().selectContactById(app.db().contacts().iterator().next().getId());
      app.contact().addContactToGroup();
      app.goTo().goToAddedGroupPage();
    }
    int groupsAfter = contact.getGroups().size();
    assertTrue(groupsAfter == groupsBefore + 1);
  }
}
