package com.acxiom_tech_service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

    Path getFileStorageLocation();
}
