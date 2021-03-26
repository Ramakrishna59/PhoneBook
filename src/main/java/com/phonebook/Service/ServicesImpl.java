package com.phonebook.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phonebook.Entity.Contact;
import com.phonebook.Entity.Repositorys;
import com.phonebook.exception.NoDataFoundException;
@Service
public class ServicesImpl implements Services {
	
	private Repositorys rps;

	public ServicesImpl(Repositorys rps) {
		super();
		this.rps = rps;
	}

	@Override
	public List<Contact> ViewAllContacts() {
		
		return rps.findAll();
	}

	@Override
	public boolean DeleteContactById(Integer ContactId) {
		try {
			rps.deleteById(ContactId);
			return true;
		} catch (Exception e) {
		    throw new NoDataFoundException("No Record Found To Delete");
		}
		
	}

	@Override
	public boolean saveContact(Contact contact) {
		Contact saved = rps.save(contact);
		return saved.getContactId()!=null;
	}

	@Override
	public Contact getContactById(Integer ContactId) {
		Optional<Contact> findById = rps.findById(ContactId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
}
