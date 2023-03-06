package bg.softuni.cloudinary2.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile file); // structure where is our image

    boolean delete(String publicId);

}
