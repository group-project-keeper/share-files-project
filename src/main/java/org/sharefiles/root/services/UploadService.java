package org.sharefiles.root.services;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.sharefiles.root.config.ShareFilesConfig;
import org.sharefiles.root.exceptions.FileStorageException;
import org.sharefiles.root.helpers.FileNameGenerator;
import org.sharefiles.root.helpers.OwnDateFormatter;
import org.sharefiles.root.model.AnonymousFiles;
import org.sharefiles.root.model.RegisteredFiles;
import org.sharefiles.root.model.User;
import org.sharefiles.root.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
    private String todayFolderDirectory = OwnDateFormatter.getSimpleDateFormat();


    @Autowired
    private UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    // TODO implement @CreatedDate in constructor for registered users
    private java.util.Date Date;

    public boolean uploadFileRegistered(MultipartFile multipartFile) {
//      String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = "login1"; // for test purpose


        File userFolderDirectory = new File(ShareFilesConfig.REGISTERED_DIRECTORY +"/"+ username);
        if (! userFolderDirectory.exists()){
            userFolderDirectory.mkdir();
        }
        String FileNameGenerated = FileNameGenerator.generateRegisteredFileNameHash(multipartFile.getOriginalFilename());
        Path uploadPath = Paths.get(ShareFilesConfig.REGISTERED_DIRECTORY +"/"+ username +"/" + FileNameGenerated);
        System.out.println(uploadPath.toString());
        try {
            Files.copy(multipartFile.getInputStream(), uploadPath);
            RegisteredFiles registeredFileMongoDate = new RegisteredFiles(FileNameGenerated, multipartFile.getOriginalFilename(),
                    uploadPath.toString(), username,Date,  7);
            mongoTemplate.insert(registeredFileMongoDate, ShareFilesConfig.MONGO_COLECTION_FILE_REIGSTERED);
            return true;

        } catch (IOException e) {
            logger.error("Error in uploading REGIS file function() " + e.getMessage());
            return false;
        }
    }

    public boolean uploadFileAnon(MultipartFile multipartFile) {
        String FileNameGenerated = FileNameGenerator.generateAnonFileNameHash(multipartFile.getOriginalFilename());

        Path uploadPath = Paths.get(ShareFilesConfig.ANONYMOUS_DIRECTORY + todayFolderDirectory+"/"
                +FileNameGenerated);
        try {
            Files.copy(multipartFile.getInputStream(), uploadPath);
            AnonymousFiles anonFileMongoDate = new AnonymousFiles(FileNameGenerated, multipartFile.getOriginalFilename(),uploadPath.toString());
            mongoTemplate.insert(anonFileMongoDate, ShareFilesConfig.MONGO_COLECTION_FILE_ANON);
            return true;
        } catch (IOException e) {
            logger.error("Error in uploading ANON file function() " + e.getMessage());
            return false;
        }
    }


    public void fileValidator(String fileName){
        if (fileName.contains("..")){
            throw new FileStorageException("File contains invalid name (\"\")");
        }
        if (fileName.length() > ShareFilesConfig.FILE_NAME_MAX_LENGTH){
            throw new FileStorageException("File name is too long, maximum number of characters is 20");
        }
    }




}
