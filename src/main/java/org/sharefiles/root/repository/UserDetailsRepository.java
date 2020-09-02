package org.sharefiles.root.repository;

import org.sharefiles.root.model.RegisteredFiles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDetailsRepository extends MongoRepository<RegisteredFiles, String> {

    List<RegisteredFiles> findAllByUserName(String userName);


}
