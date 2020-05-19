package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
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
    if (contactToAdd().getGroups().size() > 0) {
      app.contact().create(new ContactData()
              .withFirstname("Abc")
              .withLastname("Def")
              .withAddress("Moon 2")
              .withHomePhone("9838389")
              .withFirstEmail("a@mail.ru"), true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    GroupData group = app.db().groups().iterator().next();
    ContactData contact = contactToAdd();
    Groups before = contact.getGroups();
    int id = contact.getId();
    app.contact().selectContactById(id);
    app.contact().addContactToGroup();
    app.goTo().goToAddedGroupPage();
    ContactData contactInGroup = contact.inGroup(group).withId(id);
    Groups after = contactInGroup.getGroups();
    assertThat(after, equalTo(before.withAdded(group)));
  }

  private ContactData contactToAdd() {
    GroupData group = app.db().groups().iterator().next();
    return app.db().contacts().stream().filter((c) -> !c.getGroups().contains(group)).findFirst().get();
  }
}
