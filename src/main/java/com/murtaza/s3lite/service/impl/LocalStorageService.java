package com.murtaza.s3lite.service.impl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.murtaza.s3lite.exception.StorageException;

@Service
public class LocalStorageService {

	@Value("${storage.path-path}")
	private String rootpath;
	
	
	 public void upload(MultipartFile file, String path) {
	        try {
	            Path filePath = Path.of(path);

	            // 1. Create parent directories if not present
	            Files.createDirectories(filePath.getParent());

	            // 2. Write file bytes
	            Files.write(
	                filePath,
	                file.getBytes(),
	                StandardOpenOption.CREATE_NEW
	            );

	        } catch (IOException e) {
	            throw new RuntimeException("Failed to store file", e);
	        }
	    }
	 
	 public byte[] download(String storagePath) throws StorageException {
		 try {
			 Path filepath = Path.of(storagePath);
			 
			 if(!Files.exists(filepath)) throw new StorageException("File_Not_Found");
			 byte[] fileContent=Files.readAllBytes(filepath);
				return fileContent;
			} catch (IOException e) {
				throw new RuntimeException("Failed to download file", e);
			}
			 
		 
	 }
	 
	 
	 public void delete(String storagePath) throws StorageException{
		 try {
		 Path filepath = Path.of(storagePath);
		 if(!Files.exists(filepath)) throw new StorageException("File_Not_Found");
		 Files.delete(filepath);
		 }catch(IOException e) {
			 throw new RuntimeException("Failed to delete file" , e);
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	}