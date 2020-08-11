package org.sharefiles.root.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
public class FileUploadResponse {

    private HttpStatus httpStatus;
    private boolean isAuthenticated;
    private String responseMessage;
    private String fileAccessLink;

    public FileUploadResponse(HttpStatus httpStatus, boolean isAuthenticated, String responseMessage) {
        this.httpStatus = httpStatus;
        this.isAuthenticated = isAuthenticated;
        this.responseMessage = responseMessage;
    }

    public FileUploadResponse(HttpStatus httpStatus, boolean isAuthenticated, String responseMessage, String fileAccessLink) {
        this.httpStatus = httpStatus;
        this.isAuthenticated = isAuthenticated;
        this.responseMessage = responseMessage;
        this.fileAccessLink = fileAccessLink;
    }
}
