package com.document.storage;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class DocumentStorageController {
	
	@Autowired
	DocumentStorageService documentStorageService;
	
	@Autowired
	StorageService storageService;
	
	
	@GetMapping("/storage/documents/{docId}")
	public ResponseEntity<Resource> getDocument(@PathVariable String docId) {
		DocumentStorage documentStorageObj = documentStorageService.getDocument(docId);
		if(documentStorageObj!=null) {
			Resource file = storageService.loadAsResource(documentStorageObj.getFileName());
			return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"/"
						+ "'").body(file);
		}else {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "404 Not Found").body(null);
		}
		
		//return  result;
	}
	
	
	@PostMapping("/storage/documents") 
	public String addDocument(@RequestParam("file") MultipartFile file ) 
    {	storageService.store(file);
		String docId = documentStorageService.addDocument(file.getOriginalFilename());
		return docId;
    }
	
	@PutMapping("/storage/documents/{docId}")
	public String updateDocument(@RequestParam("file") MultipartFile file, @PathVariable String docId) {
		String result = documentStorageService.updateDocument(file,docId);
		return result;
	}
	
	@DeleteMapping("/storage/documents/{docId}")
	public String deleteTopic(@PathVariable String docId ) {
		String result = documentStorageService.deleteDocument(docId);
		return result;
		
	}
	
	
}
