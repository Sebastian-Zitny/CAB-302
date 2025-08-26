package com.example.jth_mathgame.mathgame;

import java.util.List;

public interface IContactDAO {
    void addContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(Contact contact);
    Contact getContact(int id);
    List<Contact> getAllContacts();
}