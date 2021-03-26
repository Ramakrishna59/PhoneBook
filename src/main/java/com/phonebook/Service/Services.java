package com.phonebook.Service;

import java.util.List;

import com.phonebook.Entity.Contact;

public interface Services {

	public List<Contact> ViewAllContacts();

	public boolean DeleteContactById(Integer ContactId);

	public boolean saveContact(Contact contact);
	
    public Contact getContactById(Integer ContactId);
    
    
}
