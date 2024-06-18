package org.example.hrms.core.external.adapters;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.example.hrms.core.external.services.ContentService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryAdapter implements ContentService {

    private Cloudinary cloudinary;

    public CloudinaryAdapter() {
        Dotenv dotenv = Dotenv.load();
        String cloudName = dotenv.get("CLOUD_NAME");
        String apiKey = dotenv.get("API_KEY");
        String apiSecret = dotenv.get("API_SECRET");

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @Override
    public String uploadFile(File file, Map<String, Object> options) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file, options);
            return (String) uploadResult.get("url"); // Yüklenen resmin URL'si
        } catch (IOException e) {
            // Hata durumunda loglama veya hata mesajı döndürme işlemleri ekleyebilirsiniz
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean deleteFile(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return "ok".equals(result.get("result")); // Dosya başarıyla silindi
        } catch (IOException e) {
            // Hata durumunda loglama veya hata mesajı döndürme işlemleri ekleyebilirsiniz
            e.printStackTrace();
            return false; // Dosya silinemedi
        }
    }

    @Override
    public String getImageUrl(String publicId, Map<String, Object> options) {
        Transformation transformation = new Transformation();
        options.forEach((key, value) -> transformation.param(key, value));
        return cloudinary.url().transformation(transformation).generate(publicId); // Belirtilen boyut ve formatlarda URL
    }
}
