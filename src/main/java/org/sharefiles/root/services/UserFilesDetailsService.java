package org.sharefiles.root.services;

import org.sharefiles.root.model.RegisteredFiles;
import org.sharefiles.root.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserFilesDetailsService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public List getAllFileList(String userName){
        /*
        MongoCollection<Document> gradesCollection = mongoTemplate.getCollection("registered files");
        Document foundFiles = (Document) gradesCollection.find(new Document("userName", userName))
                .projection(fields(include("fileName"), excludeId()));
*/
        List<RegisteredFiles> lp = userDetailsRepository.findAllByUserName(userName);
        System.out.println(Arrays.toString(lp.toArray()));

        //System.out.println(foundFiles);


        List<String> tk = new ArrayList<>();

        return lp;


    }



}
