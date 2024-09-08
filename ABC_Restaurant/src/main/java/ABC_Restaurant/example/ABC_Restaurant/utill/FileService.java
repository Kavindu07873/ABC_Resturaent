package ABC_Restaurant.example.ABC_Restaurant.utill;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${fileUploadUrl}")
    private String baseDirectory;

    @Value("${fileDownloadUrl}")
    private String fileDownloadUrl;

    public String saveMultipartFile(MultipartFile file, String type) {
        try {
            // Ensure type is valid and use it to determine the directory
            String fileUploadDir = getFileUploadDirectory(type);
            String fileDownloadDir = getFileDownloadDirectory(type);

            // Generate unique file name
            UUID uuid = UUID.randomUUID();
            String extension = getFileExtension(file);
            String fileName = uuid + (extension.isEmpty() ? "" : "." + extension);
            String filePath = fileUploadDir + File.separator + fileName;

            // Create file object and ensure parent directories exist
            File serverFile = new File(filePath);
            serverFile.getParentFile().mkdirs();

            // Save file
            file.transferTo(serverFile);

            // Generate file URL
            return fileDownloadDir + "/" + fileName;
        } catch (IOException e) {
            logger.error("Error saving file: {}", e.getMessage(), e);
            return null;
        }
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            int lastDotIndex = originalFilename.lastIndexOf('.');
            if (lastDotIndex >= 0) {
                return originalFilename.substring(lastDotIndex + 1);
            }
        }
        return ""; // Default extension if not found.
    }

    private String getFileUploadDirectory(String type) {
        // Implement your logic to determine upload directory based on type
        return baseDirectory;
    }

    private String getFileDownloadDirectory(String type) {
        // Implement your logic to determine download directory based on type
        return fileDownloadUrl;
    }
}
