package com.phonebook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.Entity.Contact;
import com.phonebook.Service.ServicesImpl;
import com.phonebook.constants.AppConstants;
import com.phonebook.exception.NoDataFoundException;
import com.phonebook.props.AppProperties;

@RestController
@RequestMapping("/api/contact")
public class RestControllers {
	Logger log=LoggerFactory.getLogger(RestControllers.class);
private ServicesImpl s;
 
private AppProperties prop;

public RestControllers(ServicesImpl s , AppProperties prop) {

	this.s = s;
	this.prop = prop;
}

@PostMapping
public ResponseEntity<String> saveContact(@RequestBody Contact contact){
	log.debug("*** saveContact() Execution started ***");
	try {
		boolean saveContact = s.saveContact(contact);
		if(saveContact) {
			log.info("*** saveContact() -contact saved ***");
			String success = prop.getMessages().get(AppConstants.SAVE_CONTACT_SUCCESS);
		return new ResponseEntity<>(success,HttpStatus.CREATED);
		}
	} catch (Exception e) {
		log.error("*** Exception Occured :*** "+e.getMessage());
	}
	log.info("*** saveContact() -Contact Not Saved ***");
	String failed = prop.getMessages().get(AppConstants.SAVE_CONTACT_FAILED);
	return new ResponseEntity<>(failed,HttpStatus.INTERNAL_SERVER_ERROR);
}

@GetMapping
public ResponseEntity<List<Contact>> getAllContacts(){
	log.debug("*** viewAllContacts() -Execution started ***");
	List<Contact> viewAllContacts=null;
	try {
		viewAllContacts = s.ViewAllContacts();
		if(viewAllContacts.isEmpty()) {
			log.info("** viewAllContacts() -Records Not Available **");
		}
	} catch (Exception e) {
		log.error("*** Exception Occured :**"+e.getMessage());
	}
	log.debug("** Execution Ended **");
	return new ResponseEntity<>(viewAllContacts,HttpStatus.OK);
}

@GetMapping("/{ContactId}")
public ResponseEntity<Contact> getContactById(@PathVariable Integer ContactId){		
	log.debug("** getContactById() -Execution Started **");
	Contact contactById=null;
	try {
		contactById = s.getContactById(ContactId);
		if(contactById==null) {
			log.info("**getContactById() -No Data Found **");
			throw new NoDataFoundException("No Contact Found");
		}
	} catch (Exception e) {
		log.error("** Exception Occured **:"+e.getMessage());
	}
	 
	return new ResponseEntity<>(contactById,HttpStatus.OK);
}

@DeleteMapping("/{ContactId}")
public ResponseEntity<String> deleteContactById(@PathVariable Integer ContactId){
	log.debug("** deleteContactById() - Execution Started **");
	try {
		boolean deleteContactById = s.DeleteContactById(ContactId);
		if(deleteContactById) {
			log.info("** deleteContactById()  - Record Deleted **");
			String success = prop.getMessages().get("deleteContactSuccess");
		  return new ResponseEntity<>(success,HttpStatus.OK);		  
		}				
	} catch (Exception e) {
		log.error("** Exception Occured **"+e.getMessage());
	}
	log.debug("** deleteContactById() - Execution Ended");
	String failed = prop.getMessages().get("deleteContactFailed");
	return new ResponseEntity<>(failed,HttpStatus.INTERNAL_SERVER_ERROR);
}

}
