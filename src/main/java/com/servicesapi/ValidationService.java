package com.servicesapi;

public interface ValidationService {
    Object checkUniqueEmail(String email);
    Object checkUniqueUsername(String username);
    Object findTopicByname(String topicname);
}
