package org.sharefiles.root.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {


    // FIXME: fix favicon.ico file request, it should send 200 OK status to the browser with empty body

    @GetMapping("favicon.ico")
    @ResponseBody
    public ResponseEntity faviconResponse() {
        return new ResponseEntity(HttpStatus.OK);
    }

}

