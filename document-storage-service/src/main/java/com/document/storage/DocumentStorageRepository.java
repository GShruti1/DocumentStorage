package com.document.storage;


import org.springframework.data.repository.CrudRepository;



public interface DocumentStorageRepository extends CrudRepository<DocumentStorage, String>{

	public DocumentStorage findByDocId(String docId);
		
	


}
