package org.sharefiles.root.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
//@EntityListeners(AuditingEntityListener.class)
public class RegisteredFiles {

    @Id
    private String hashedFileName;
    private String fileName;
    private String directoryFile;
    private String userName;
    @CreatedDate
    private Date uploadedTime;
    private int expirationTime;

}
