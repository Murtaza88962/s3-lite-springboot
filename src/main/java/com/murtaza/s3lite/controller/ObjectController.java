package com.murtaza.s3lite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.murtaza.s3lite.dto.UploadResponseDto;
import com.murtaza.s3lite.exception.StorageException;
import com.murtaza.s3lite.service.ObjectService;

@RestController
@RequestMapping(path = "/file")
public class ObjectController {
	
	
	@Autowired
	ObjectService objectService;
	
	
	@PostMapping(path = "/upload")
	public ResponseEntity<UploadResponseDto> upload(@RequestParam String bucketName , @RequestParam MultipartFile file) throws StorageException{
		UploadResponseDto dto = objectService.uploadFile(bucketName, file);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);	
	}
	
	@GetMapping(path = "/download/{bucketName}/{objectKey}")
	public ResponseEntity<byte[]> download(@PathVariable String bucketName , @PathVariable String objectKey) throws  StorageException {
		byte[] content = objectService.downloadFile(bucketName, objectKey);
		return new ResponseEntity<>(content , HttpStatus.OK);
 	}

}
