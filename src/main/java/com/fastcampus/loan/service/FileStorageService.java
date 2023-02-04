package com.fastcampus.loan.service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  void save(MultipartFile file);

  Resource load(String file);

  void deleteAll();

  Stream<Path> loadAll();
}
