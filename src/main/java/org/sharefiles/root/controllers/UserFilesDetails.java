package org.sharefiles.root.controllers;


import org.sharefiles.root.model.RegisteredFiles;
import org.sharefiles.root.services.UserFilesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/details")
public class UserFilesDetails {


    @Autowired
    UserFilesDetailsService userFilesDetailsService;


    @GetMapping("")
    public ResponseEntity getUsersFilesDetails(@RequestParam(value="login") String login){

        List<RegisteredFiles> lp = userFilesDetailsService.getAllFileList(login);

        return ResponseEntity.ok().body("gotowe");

    }


}
