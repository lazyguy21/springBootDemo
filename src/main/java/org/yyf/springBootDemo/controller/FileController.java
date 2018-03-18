package org.yyf.springBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileController {
  @RequestMapping("/file")
  public void uploadFile(MultipartFile file) throws IOException {
    byte[] bytes = file.getBytes();
    log.info(new String(bytes));
    log.info(file.getContentType()+file.getName()+file.getOriginalFilename()+file.getSize());
  }
}
