package org.sharefiles.root.services;

import org.sharefiles.root.config.ShareFilesConfig;
import org.sharefiles.root.helpers.FileNameGenerator;
import org.sharefiles.root.helpers.OwnDateFormatter;
import org.sharefiles.root.model.AnonymousFiles;
import org.sharefiles.root.model.User;
import org.sharefiles.root.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
    private String todayFolderDirectory = OwnDateFormatter.getSimpleDateFormat();


    @Autowired
    private UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public boolean uploadFileRegistered(MultipartFile multipartFile) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> uploadUser = userRepository.findByUsername(username);

        if(uploadUser.isPresent()) {
            Path uploadPath = Paths.get(ShareFilesConfig.REGISTERED_DIRECTORY + uploadUser.get().getUploadDirName()
                    + multipartFile.getOriginalFilename());
            try {
                Files.copy(multipartFile.getInputStream(), uploadPath);

            } catch (IOException e) {
                logger.error("Error in uploading REGIS file function() " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean uploadFileAnon(MultipartFile multipartFile) {
        String FileNameGenerated = FileNameGenerator.generateFileNameHash(multipartFile.getOriginalFilename());

        Path uploadPath = Paths.get(ShareFilesConfig.ANONYMOUS_DIRECTORY + todayFolderDirectory+"/"
                +FileNameGenerated);
        try {
            Files.copy(multipartFile.getInputStream(), uploadPath);
            AnonymousFiles anonFileMongoDate = new AnonymousFiles(FileNameGenerated, multipartFile.getOriginalFilename(),uploadPath.toString());
            mongoTemplate.insert(anonFileMongoDate, "anonymous files");
            return true;
        } catch (IOException e) {
            logger.error("Error in uploading ANON file function() " + e.getMessage());
            return false;
        }
    }




}
