package org.sharefiles.root.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class UploadedFile {


    @Id
    private String id;

    private String fileName;
    private String uploadedDate;
    private String expiryDate;


}
