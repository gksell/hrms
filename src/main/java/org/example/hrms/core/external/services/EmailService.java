package org.example.hrms.core.external.services;

public interface EmailService {
    boolean send(String to, String subject, String body);
}
