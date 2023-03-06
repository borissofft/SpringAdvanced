package bg.softuni.cloudinary2.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile file) throws IOException; // structure where is our image

    boolean delete(String publicId);

}
