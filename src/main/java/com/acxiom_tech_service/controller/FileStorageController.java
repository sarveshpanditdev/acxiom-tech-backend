package com.acxiom_tech_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.acxiom_tech_service.dto.ApiResponseDto;
import com.acxiom_tech_service.service.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
public class FileStorageController {
	
	@Autowired
	FileStorageService fileStorageService;
	
	@PostMapping("/upload")
    public ResponseEntity<ApiResponseDto<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = fileStorageService.storeFile(file);
            return ResponseEntity.ok(ApiResponseDto.success("File uploaded successfully", filePath, 200));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(ApiResponseDto.error("File upload failed", e.getMessage(), 500));
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath, HttpServletRequest request) {
        try {
            Path path = Path.of(filePath);
            String fileName = path.getFileName().toString();
            Resource resource = fileStorageService.loadFileAsResource(fileName);

            // Determine content type
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
