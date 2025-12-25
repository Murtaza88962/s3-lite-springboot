package com.murtaza.s3lite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murtaza.s3lite.entity.ObjectMetadata;

@Repository
public interface ObjectMetaDataRepository extends JpaRepository<ObjectMetadata, Integer>{

	
	
	public Optional<ObjectMetadata> findByBucketNameAndObjectKey(String bucketName , String objectKey);
	
	
}
