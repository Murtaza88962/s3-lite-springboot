package com.murtaza.s3lite.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "object_metadata",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bucket_name", "object_key"})
    }
)
public class ObjectMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bucket_name", nullable = false)
    private String bucketName;

    @Column(name = "object_key", nullable = false)
    private String objectKey;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(nullable = false)
    private Long size;

    @Column(name = "storage_path", nullable = false)
    private String storagePath;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    
    public ObjectMetadata() {
		
	}

	public ObjectMetadata(Integer id, String bucketName, String objectKey, String originalFileName, String contentType,
			Long size, String storagePath, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.bucketName = bucketName;
		this.objectKey = objectKey;
		this.originalFileName = originalFileName;
		this.contentType = contentType;
		this.size = size;
		this.storagePath = storagePath;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
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
