package com.murtaza.s3lite.service;

import java.nio.file.Path;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.murtaza.s3lite.dto.UploadResponseDto;
import com.murtaza.s3lite.entity.ObjectMetadata;
import com.murtaza.s3lite.exception.StorageException;
import com.murtaza.s3lite.repository.ObjectMetaDataRepository;
import com.murtaza.s3lite.service.impl.LocalStorageService;
import com.murtaza.s3lite.util.ObjectKeyGenerator;

@Service
public class ObjectService {
	
	@Value("${storage.path-path}")
	private String rootpath;
	
	@Autowired
	ObjectMetaDataRepository objectMetaDataRepository;
	
	@Autowired
	LocalStorageService localStorageService;
	
	
	private UploadResponseDto mapToUploadResponse(ObjectMetadata metadata) {
	    UploadResponseDto dto = new UploadResponseDto();
	    dto.setBucketName(metadata.getBucketName());
	    dto.setObjectKey(metadata.getObjectKey());
	    dto.setOriginalFileName(metadata.getOriginalFileName());
	    dto.setContentType(metadata.getContentType());
	    dto.setSize(metadata.getSize());
	    dto.setCreatedAt(metadata.getCreatedAt());
	    return dto;
	}

	
	
	
	public UploadResponseDto uploadFile(String bucketName, MultipartFile file) throws StorageException{
		
		if(file.isEmpty())throw new StorageException("File_Name_NULL");
		if(file.getOriginalFilename()==null)throw new StorageException("Original_Name_NULL");
		
		
		if(bucketName == null || bucketName.isBlank()) {
			bucketName="default";}
		String objectKey = ObjectKeyGenerator.keyGenerator();
		Path path = Path.of(rootpath, bucketName,objectKey);
		String storagePath = path.toString();
		
		localStorageService.upload(file, storagePath);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setBucketName(bucketName);
		metadata.setObjectKey(objectKey);
		metadata.setStoragePath(storagePath);
		metadata.setContentType(file.getContentType());
        metadata.setCreatedAt(LocalDateTime.now());
        metadata.setOriginalFileName(file.getOriginalFilename());
        metadata.setSize(file.getSize());
        objectMetaDataRepository.save(metadata);
        
        
        return mapToUploadResponse(metadata);
        
        
	}
	
	public byte[] downloadFile(String bucketName , String objectKey) throws StorageException {
		if(bucketName == null || bucketName.isBlank()) {
			bucketName="default";}
		
		ObjectMetadata metadata = objectMetaDataRepository.findByBucketNameAndObjectKey(bucketName, objectKey)
				.orElseThrow(()->new StorageException("Object_Not_Found"));
		
		byte[] content = localStorageService.download(metadata.getStoragePath());
		return content;
	}
	
	public String deletFile(String bucketName , String objectKey) throws StorageException{
		if(bucketName == null || bucketName.isBlank()) {
			bucketName="default";}
		
		ObjectMetadata metadata = objectMetaDataRepository.findByBucketNameAndObjectKey(bucketName, objectKey)
				.orElseThrow(()->new StorageException("Object_Not_Found"));
		
		localStorageService.delete(metadata.getStoragePath());
		objectMetaDataRepository.delete(metadata);
		return "File Deleted Successfully";
		
		
		
		
	}
	
	
	
	
	
}

