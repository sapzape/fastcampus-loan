package com.fastcampus.loan.service;

import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = Paths.get(uploadPath).resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new BaseException(ResultType.SYSTEM_ERROR);
      }
    } catch (MalformedURLException e ) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(Paths.get(uploadPath).toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(Paths.get(uploadPath), 1).filter(path -> !path.equals(Paths.get(uploadPath)));
    } catch (IOException e) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }
  }
}
