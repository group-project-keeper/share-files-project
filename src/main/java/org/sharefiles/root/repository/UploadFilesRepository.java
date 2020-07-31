package org.sharefiles.root.repository;

import org.sharefiles.root.model.UploadedFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFilesRepository extends MongoRepository<UploadedFile, String> {

}
