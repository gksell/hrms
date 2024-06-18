package org.example.hrms.core.external.adapters;

import com.example.generated.KPSPublic;
import com.example.generated.KPSPublicSoap;
import org.example.hrms.core.external.services.MernisService;
import org.springframework.stereotype.Service;

@Service
public class MernisAdapter implements MernisService {
    @Override
    public boolean isRealPerson(String nationalIdentityNumber, String firstName, String lastName, int birthYear) {
        KPSPublic service = new KPSPublic();
        KPSPublicSoap port = service.getKPSPublicSoap();
        return port.tcKimlikNoDogrula(Long.parseLong(nationalIdentityNumber),
                firstName.toUpperCase(),
                lastName.toUpperCase(),
                birthYear);
    }
}
