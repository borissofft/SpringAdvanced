package bg.softuni.cloudinary2.service;

public class CloudinaryImage {

    private String url; // where we can see the uploaded image
    private String publicId; // needed when we try to delete image from cloudinary by using API

    public CloudinaryImage() {

    }

    public String getUrl() {
        return url;
    }

    public CloudinaryImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public CloudinaryImage setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
