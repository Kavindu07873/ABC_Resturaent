package ABC_Restaurant.example.ABC_Restaurant.utill;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    //    String baseDirectory = "C:\\opt\\tomcat\\webapps\\resources\\attachment";
    @Value("${fileUploadUrl}")
    private String baseDirectory;

    @Value("${fileDownloadUrl}")
    private String fileDownloadUrl;

    public String saveMultipartFile(MultipartFile file, String type) {
        try {

            UUID uuid = UUID.randomUUID();
            String extension = getFileExtension(file);
            String fileName = uuid + "." + extension;

            // Determine the file upload and download directories based on the file type.
            String fileUploadDir = getFileUploadDirectory(type); // Update to fetch the correct directory based on type
            String fileDownloadDir = getFileDownloadDirectory(type); // Update to fetch the correct directory based on type

            String filePath = fileUploadDir + File.separator + fileName;

            File serverFile = new File(filePath);

            // Ensure that the parent directories exist.
            serverFile.getParentFile().mkdirs();

            // Save the file.
            file.transferTo(serverFile);

            String fileUrl = fileDownloadDir + File.separator + fileName;
            return fileUrl;
        } catch (Exception e) {
            // Handle exceptions here.
            e.printStackTrace();
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
        // Implement logic to fetch the upload directory based on the file type.
        // For example, you can use a switch or if-else statement.
        // Example: If type is "pdf," return the PDF upload directory.
        // Update this to fetch the correct directory based on your application's logic.
        return baseDirectory;
    }

    private String getFileDownloadDirectory(String type) {
        // Implement logic to fetch the download directory based on the file type.
        // Similar to the upload directory, you need to fetch the correct directory based on the type.
        return fileDownloadUrl;
    }
}
