package org.sharefiles.root.services;


import com.mongodb.client.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;


@Service
public class DownloadService {
    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);



    @Autowired
    MongoTemplate mongoTemplate;


    public Map<String, String> DownloadFileAnon(String hashedfFileName){
        MongoCollection<Document> gradesCollection = mongoTemplate.getCollection("anonymous files");
        Document foundFile = gradesCollection.find(new Document("hashedFileName", hashedfFileName))
                 .projection(fields(include("directoryFile", "fileName"), excludeId())).first();


        Map<String, String> fileDetails= new HashMap<>();

        if (foundFile != null) {
            fileDetails.put("directoryFile", foundFile.getString("directoryFile"));
            fileDetails.put("fileName", foundFile.getString("fileName"));
        }
        return fileDetails;
    }

    public Map<String, String> DownloadFIleRegistered(String hashedFileName){
        MongoCollection<Document> gradesCollection = mongoTemplate.getCollection("registered files");
        Document foundFile = gradesCollection.find(new Document("hashedFileName", hashedFileName))
                .projection(fields(include("directoryFile", "fileName"), excludeId())).first();

        Map<String, String> fileDetails = new HashMap<>();

        if (foundFile !=null){
            fileDetails.put("directoryFile", foundFile.getString("directoryFile"));
            fileDetails.put("fileName", foundFile.getString("fileName"));
        }
        return fileDetails;



    }



}
