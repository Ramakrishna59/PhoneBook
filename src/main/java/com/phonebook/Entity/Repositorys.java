package com.phonebook.Entity;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorys extends JpaRepository<Contact, Serializable>{

}
