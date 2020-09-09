package org.sharefiles.root.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Document(collection = "anonymous files")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnonymousFiles {

    @Id
    private String hashedFileName;
    private String fileName;
    private String directoryFile;
}
