package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String mobile;
  private final String mailbox;
  private String group;

  public ContactData(String firstname, String lastname, String address, String mobile, String mailbox, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.mailbox = mailbox;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getMailbox() {
    return mailbox;
  }

  public String getGroup() {
    return group;
  }
}
