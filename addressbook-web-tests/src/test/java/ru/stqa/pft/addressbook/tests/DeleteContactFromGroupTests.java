package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    Groups contactGroup = contact.getGroups();
    app.contact().selectGroupFromList("test2");
    if(contactGroup.size() == 0){
      app.contact().selectGroupFromList("[all]");
      app.contact().selectContactById(app.db().contacts().iterator().next().getId());
      app.contact().addContactToGroup();
      app.contact().goToGroupAddedPage(app.db().groups().iterator().next().getId());
      app.contact().selectGroupFromList("test2");
    }
    app.contact().selectContactById(app.db().contacts().iterator().next().getId());
    app.contact().removeContactFromGroup();
    app.contact().goToGroupAddedPage(app.db().groups().iterator().next().getId());
  }
}
