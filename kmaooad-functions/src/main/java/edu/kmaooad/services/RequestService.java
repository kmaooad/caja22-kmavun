package edu.kmaooad.services;

import edu.kmaooad.interfaces.IService;
import edu.kmaooad.models.Message;
import edu.kmaooad.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService implements IService<Message> {

    @Autowired
    RequestRepository reqRepo;

    public void saveMessage(Message msg) {
        reqRepo.save(msg);
    }


}