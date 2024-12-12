package com.uml.FileManage.controller;

import com.uml.FileManage.entity.File;
import com.uml.FileManage.service.FileService;
import com.uml.FileManage.util.ApiResponse;
import com.uml.FileManage.util.FileUtil;
import com.uml.FileManage.vo.FileVo;
import com.uml.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Integer>> uploadFile(
            @CookieValue(value = "userId", defaultValue = "0") int userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileType") String fileType) throws IOException {
        try {
            // Check file size (10MB = 10 * 1024 * 1024 bytes)
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new MaxUploadSizeExceededException(10 * 1024 * 1024);
            }
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileUtil.ensureDirectoryExists(uploadDir);
            String uniqueFileName = userId + "_" + fileName;
            if (fileService.fileExists(uniqueFileName) != 0) {
                throw new CustomException("File with the same name already exists.");
            }
            Path filePath = FileUtil.validateAndGetFilePath(uploadDir, uniqueFileName);
            file.transferTo(filePath.toFile());
            fileService.saveFile(userId, fileType, fileName, filePath.toString());
            Integer fileId = fileService.getFileByPath(filePath.toString()).getFileId();
            return ResponseEntity.ok(ApiResponse.success(fileId, "File uploaded successfully."));
        } catch (IOException e) {
            throw new IOException("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws IOException {
        try {
            Path filePath = FileUtil.validateAndGetFilePath(uploadDir, filename);
            if (FileUtil.fileExists(uploadDir, filename)) {
                throw new NoSuchFileException("File not found: " + filename);
            }
            byte[] fileBytes = Files.readAllBytes(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            return ResponseEntity.ok().headers(headers).body(fileBytes);
        } catch (IOException e) {
            throw new IOException("Failed to download file: " + e.getMessage());
        }
    }

    @GetMapping("/downloadById/{fileId}")
    public ResponseEntity<byte[]> downloadFileById(@PathVariable int fileId) throws IOException {
        try {
            File file = fileService.getFileById(fileId);
            if (file == null) {
                throw new NoSuchFileException("File not found with ID: " + fileId);
            }
            Path filePath = FileUtil.validateAndGetFilePath(uploadDir, file.getFileUrl());
            if (FileUtil.fileExists(uploadDir, file.getFileUrl())) {
                throw new NoSuchFileException("File not found: " + file.getFileUrl());
            }
            byte[] fileBytes = Files.readAllBytes(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"");
            return ResponseEntity.ok().headers(headers).body(fileBytes);
        } catch (IOException e) {
            throw new IOException("Failed to download file: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<FileVo>>> getFileList() {
        try {
            List<FileVo> fileList = fileService.getFileList();
            return ResponseEntity.ok(ApiResponse.success(fileList, "File list retrieved successfully."));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve file list: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<ApiResponse<String>> deleteFile(@PathVariable String filename) throws IOException {
        try {
            Path filePath = FileUtil.validateAndGetFilePath(uploadDir, filename);
            if (FileUtil.fileExists(uploadDir, filename)) {
                throw new NoSuchFileException("File not found: " + filename);
            }
            Files.delete(filePath);
            File file = fileService.getFileByPath(filePath.toString());
            if (file == null) {
                throw new NoSuchFileException("File not found in database: " + filename);
            } else {
                fileService.deleteFile(file.getFileId());
            }
            return ResponseEntity.ok(ApiResponse.success("File deleted successfully: " + filename, "File deleted successfully."));
        } catch (IOException e) {
            throw new IOException("Failed to delete file: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{fileId}")
    public ResponseEntity<ApiResponse<String>> deleteFileById(@PathVariable int fileId) throws IOException {
        try {
            File file = fileService.getFileById(fileId);
            if (file == null) {
                throw new NoSuchFileException("File not found with ID: " + fileId);
            }
            Path filePath = FileUtil.validateAndGetFilePath(uploadDir, file.getFileUrl());
            if (FileUtil.fileExists(uploadDir, file.getFileUrl())) {
                throw new NoSuchFileException("File not found: " + file.getFileUrl());
            }
            Files.delete(filePath);
            fileService.deleteFile(fileId);
            return ResponseEntity.ok(ApiResponse.success("File deleted successfully: " + file.getFileName(), "File deleted successfully."));
        } catch (IOException e) {
            throw new IOException("Failed to delete file: " + e.getMessage());
        }
    }

    @PutMapping("/update/{oldFileId}")
    public ResponseEntity<ApiResponse<String>> updateFile(
            @CookieValue(value = "userId", defaultValue = "0") int userId,
            @RequestParam("newFile") MultipartFile newFile,
            @PathVariable("oldFileId") int oldFileId) {
        try {
            File oldFile = fileService.getFileById(oldFileId);
            if (oldFile == null) {
                throw new NoSuchFileException("Old file not found with ID: " + oldFileId);
            } else {
                fileService.deleteFile(oldFileId);
            }

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(newFile.getOriginalFilename()));
            FileUtil.ensureDirectoryExists(uploadDir);
            String uniqueFileName = userId + "_" + fileName;
            if (fileService.fileExists(uniqueFileName) != 0) {
                throw new CustomException("File with the same name already exists.");
            } else {
                Path filePath = FileUtil.validateAndGetFilePath(uploadDir, uniqueFileName);
                newFile.transferTo(filePath.toFile());
                fileService.reloadFile(oldFileId, fileName, filePath.toString());
                return ResponseEntity.ok(ApiResponse.success("File updated successfully.", "File updated successfully."));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update file: " + e.getMessage());
        }
    }
}