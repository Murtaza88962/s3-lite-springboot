package com.murtaza.s3lite.dto;

import java.time.LocalDateTime;

import lombok.Data;


public class ObjectMetaDataDto {

	private String bucketName;

	private String objectKey;

	private String originalFileName;

	private String contentType;

	private Long size;

	private LocalDateTime createdAt;
	
	

	public ObjectMetaDataDto() {
		
	}

	public ObjectMetaDataDto(String bucketName, String objectKey, String originalFileName, String contentType,
			Long size, LocalDateTime createdAt) {
		super();
		this.bucketName = bucketName;
		this.objectKey = objectKey;
		this.originalFileName = originalFileName;
		this.contentType = contentType;
		this.size = size;
		this.createdAt = createdAt;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
