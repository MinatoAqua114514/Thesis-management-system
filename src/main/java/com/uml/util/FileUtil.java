package com.uml.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    // 确保目录存在
    public static void ensureDirectoryExists(String uploadDir) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }

    // 验证并获取文件路径
    public static Path validateAndGetFilePath(String uploadDir, String filename) throws IllegalArgumentException {
        Path filePath = Paths.get(uploadDir, filename).normalize();
        if (!filePath.startsWith(Paths.get(uploadDir))) {
            throw new IllegalArgumentException("Invalid file path");
        }
        return filePath;
    }

    // 检查文件是否存在
    public static boolean fileExists(String uploadDir, String filename) {
        Path filePath = Paths.get(uploadDir, filename).normalize();
        return !Files.exists(filePath);
    }
}