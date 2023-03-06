package bg.softuni.cloudinary2.service.impl;

import bg.softuni.cloudinary2.service.CloudinaryImage;
import bg.softuni.cloudinary2.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = this.cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc6OM6ra82T_cNYFBxThgbfRlD_LnwN93dAw&usqp=CAU");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage()
                    .setUrl(url)
                    .setPublicId(publicId);
        } finally {
            tempFile.delete();
        }

    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
