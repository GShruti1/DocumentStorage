
package com.document.storage;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentStorageService {

	@Autowired
	DocumentStorageRepository documentStorageRepository;
	
	@Autowired
	StorageService storageService;
	
	
	public DocumentStorage getDocument(String docId) {
		return documentStorageRepository.findByDocId(docId);

	}
	
	public String addDocument(String fileName) {
		DocumentStorage documentStorageObj= new DocumentStorage();
		documentStorageObj.setDocId(generateDocId());
		documentStorageObj.setFileName(fileName);
		DocumentStorage newObj = documentStorageRepository.save(documentStorageObj);
		return newObj.getDocId().toString();
	}

	public String updateDocument(MultipartFile file, String docId) {
		DocumentStorage documentStorage = documentStorageRepository.findByDocId(docId);
		if(documentStorage!=null) {
			storageService.deleteFile(documentStorage.getFileName());
			storageService.store(file);
			documentStorage.setFileName(file.getOriginalFilename());
			DocumentStorage newObj = documentStorageRepository.save(documentStorage);
			return "204 No Content";
		}
		else {
			return "404 Not Found";
		}
		
	}
	
	
	public String deleteDocument(String docId) {
		DocumentStorage documentStorage = documentStorageRepository.findByDocId(docId);
		if(documentStorage!=null) {
			storageService.deleteFile(documentStorage.getFileName());
			documentStorageRepository.deleteById(docId);
			return "204 No Content";
		}
		else return "404 Not Found";
		
	}
	
	
	public String generateDocId() {
		 
		String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
		Random rand=new Random();
		StringBuilder res=new StringBuilder();
		for (int i = 0; i < 20; i++) {
		    int randIndex=rand.nextInt(aToZ.length()); 
		    res.append(aToZ.charAt(randIndex));            
	    }
		return res.toString();
	}

	
}
