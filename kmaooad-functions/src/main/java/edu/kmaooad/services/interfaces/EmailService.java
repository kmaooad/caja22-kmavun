package edu.kmaooad.services.interfaces;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
