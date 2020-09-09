package org.sharefiles.root.controllers;

import org.sharefiles.root.helpers.IsFileRegistered;
import org.sharefiles.root.services.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @RequestMapping(value = "/{file_name}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("file_name") String fileName, HttpServletResponse response) throws IOException {

        Map<String, String> fileDetails;

        if(IsFileRegistered.isFileRegistered(fileName)){
            fileDetails = downloadService.DownloadFIleRegistered(fileName);
        }
        else {
            fileDetails = downloadService.DownloadFileAnon(fileName);
        }

        String filePath = fileDetails.get("directoryFile");
        String basicFileName = fileDetails.get("fileName");

        if (!fileDetails.isEmpty()) {
            if (Files.exists(Paths.get(filePath))) {
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment; filename=" + basicFileName);
                try {
                    Files.copy(Paths.get(filePath), response.getOutputStream());
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                } catch (IOException e) {
                    System.out.println("Error :- " + e.getMessage());
                }
            }
        }else{
            throw new FileNotFoundException("Invalid url,  File does not exists");
        }
    }
}
