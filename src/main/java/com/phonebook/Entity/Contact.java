package com.phonebook.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="CONTACT_DTLS")
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
@Id
@SequenceGenerator(name="abc",sequenceName = "id_seq",allocationSize = 1)
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "abc")
@Column(name="CONTACT_ID")
private Integer ContactId;

@Column(name="CONTACT_NAME")
private String ContactName;

@Column(name="CONTACT_NUMBER")
private String ContactNumber;

@Column(name="CONTACT_EMAIL")
private String ContactEmail;
}
