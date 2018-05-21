package org.yyf.springBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileController {
  @RequestMapping("/file")
  public void uploadFile(MultipartFile file) throws IOException {
    byte[] bytes = file.getBytes();
    log.info(new String(bytes));
    log.info(file.getContentType() + file.getName() + file.getOriginalFilename() + file.getSize());
  }

  @RequestMapping("/file2")
  public void uploadFile2(MultipartFile file) throws IOException {
    byte[] bytes = file.getBytes();
    log.info(new String(bytes));
    log.info(file.getContentType() + file.getName() + file.getOriginalFilename() + file.getSize());
    String fileName = UUID.randomUUID().toString();
    String path="/apps/file/"+fileName;
    final File newFile = new File(path);
    try {
      Files.write(bytes, newFile);
    } catch (IOException fileIoEx) {
      log.error("ERROR trying to write to file '" + fileName + "' - "
          + fileIoEx.toString());
    }
  }
}
