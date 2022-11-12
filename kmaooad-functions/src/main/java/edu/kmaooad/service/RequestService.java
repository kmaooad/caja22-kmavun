package edu.kmaooad.service;

import edu.kmaooad.model.Message;
import edu.kmaooad.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository reqRepo;

    public void saveMessage(Message msg) {
        reqRepo.save(msg);
    }
}