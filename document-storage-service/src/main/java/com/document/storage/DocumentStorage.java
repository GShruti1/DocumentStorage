package com.document.storage;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DocumentStorage {
	
	@Id
	String docId; 
	String fileName;
	//String content;
	
	public DocumentStorage() {
		
	}
	
	public DocumentStorage(String docId, String fileName) {
		super();
		this.docId = docId;
		this.fileName = fileName;
		
	}
	public String getDocId() {
		return docId;
	}
	
	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
