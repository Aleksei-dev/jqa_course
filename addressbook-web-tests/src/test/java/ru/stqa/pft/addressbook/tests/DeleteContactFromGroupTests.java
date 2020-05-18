package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
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
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    Groups contactGroup = contact.getGroups();
    int id = app.db().contacts().iterator().next().getId();
    Groups before = contact.getGroups();
    app.contact().selectGroupFromList("test1");
    if(contactGroup.size() == 0){
      app.contact().selectGroupFromList("[all]");
      app.contact().selectContactById(id);
      app.contact().addContactToGroup();
      app.contact().goToGroupAddedPage(app.db().groups().iterator().next().getId());
      app.contact().selectGroupFromList("test1");
    }
    app.contact().selectContactById(id);
    app.contact().removeContactFromGroup();
    app.contact().goToGroupAddedPage(app.db().groups().iterator().next().getId());
    ContactData contactAfterGroupDeleted = app.db().contacts().iterator().next().withId(id);
    Groups after = contactAfterGroupDeleted.getGroups();
    assertThat(after, equalTo(before.without(group)));
  }
}
