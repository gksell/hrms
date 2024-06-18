package org.example.hrms.business.concretes;

import com.cloudinary.utils.ObjectUtils;
import org.example.hrms.business.abstracts.CVService;
import org.example.hrms.business.utils.mappers.CVMapper;
import org.example.hrms.core.external.services.ContentService;
import org.example.hrms.core.utilities.results.*;
import org.example.hrms.dataAccess.abstracts.CV.CVDao;
import org.example.hrms.entities.concretes.cv.CV;
import org.example.hrms.entities.dtos.CV.CVDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CVManager implements CVService {
    private final CVDao cvDao;
    private final ContentService contentService;

    @Autowired
    public CVManager(CVDao cvDao, ContentService contentService) {
        this.cvDao = cvDao;
        this.contentService = contentService;
    }

    @Override
    public Result add(CVDto cvDto) {

        CV cv = CVMapper.mapToCV(cvDto);
        this.cvDao.save(cv);
        return new SuccessResult("CV Başarıyla oluşturuldu");
    }

    @Override
    public Result addPhoto(int id, MultipartFile file) {
        CV cv = this.cvDao.getById(id);
       if (file != null && !file.isEmpty()) {
            try {
                File tempFile = convertMultipartFileToFile(file);
                String photoUrl = contentService.uploadFile(tempFile, ObjectUtils.emptyMap());
                cv.setPhoto(photoUrl);
                this.cvDao.save(cv);
                tempFile.delete(); // Geçici dosyayı sil
            } catch (IOException e) {
                e.printStackTrace();
                return new ErrorResult("Fotoğraf yüklenirken bir hata oluştu");
            }
        }
       return new SuccessResult("Fotoğraf eklendi.");
    }

    @Override
    public DataResult<List<CVDto>> getCvsByJobSeekerId(int jobSeekerId) {
        List<CV> cv = this.cvDao.findByJobSeeker_Id(jobSeekerId);
        return new SuccessDataResult<>(CVMapper.mapToCVList(cv));
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
