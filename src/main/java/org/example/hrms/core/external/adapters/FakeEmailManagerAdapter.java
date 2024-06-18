package org.example.hrms.core.external.adapters;

import org.example.hrms.core.external.services.EmailService;
import org.springframework.stereotype.Service;

@Service
public class FakeEmailManagerAdapter implements EmailService {
    @Override
    public boolean send(String to, String subject, String body) {
        return true;
    }
}
