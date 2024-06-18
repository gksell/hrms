package org.example.hrms.core.external.services;

public interface MernisService {
    boolean isRealPerson(String nationalIdentityNumber,String firstName,String lastName,int birthYear);
}
