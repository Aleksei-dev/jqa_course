package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
  }

  @Test
  public void  testDeleteContactFromGroup(){
    app.goTo().homePage();
    ContactData contact = contactToRemove();
    GroupData group = app.db().groups().iterator().next();
    int id = contact.getId();
    Groups before = contact.getGroups();
    app.contact().selectGroupFromList(group.getName());
    app.contact().selectContactById(id);
    app.contact().removeContactFromGroup();
    app.contact().goToGroupAddedPage(group.getId());

    ContactData contactInTest = contact;
    contact = app.db().contacts().stream()
            .filter((c) -> c.getId() == contactInTest.getId()).findFirst().get();
    Groups after = contact.getGroups();
    assertThat(after, equalTo(before.without(group)));
  }

  private ContactData contactToRemove() {
    GroupData group = app.db().groups().iterator().next();
    int contactId = app.db().contacts().iterator().next().getId();
    if(group.getContacts().size() == 0){
      app.contact().selectContactById(contactId);
      app.contact().addContactToGroup();
      app.goTo().goToAddedGroupPage();
      app.goTo().homePage();
    }
    return app.db().contacts().stream().filter((c) -> c.getGroups().contains(group)).findFirst().get();
  }
}
