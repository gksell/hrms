package org.example.hrms.core.external.services;

import java.io.File;
import java.util.Map;

public interface ContentService {

    /**
     * Belirtilen dosyayı Cloudinary'ye yükler ve URL'sini döndürür.
     *
     * @param file Yüklenecek dosya
     * @param options Yükleme seçenekleri (isim, format, vb.)
     * @return Yüklenen resmin URL'si
     */
    String uploadFile(File file, Map<String, Object> options);

    /**
     * Cloudinary'den belirtilen ID'ye sahip dosyayı siler.
     *
     * @param publicId Dosya ID'si
     * @return İşlem sonucu (başarılı/başarısız)
     */
    boolean deleteFile(String publicId);

    /**
     * Cloudinary'deki belirtilen ID'ye sahip dosyanın URL'sini döndürür.
     *
     * @param publicId Dosya ID'si
     * @param options URL oluşturma seçenekleri (boyut, format, vb.)
     * @return Dosyanın URL'si
     */
    String getImageUrl(String publicId, Map<String, Object> options);
}
