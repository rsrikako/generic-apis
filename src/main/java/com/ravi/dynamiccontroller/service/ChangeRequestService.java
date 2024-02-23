package com.ravi.dynamiccontroller.service;

import org.springframework.stereotype.Service;

import com.ravi.dynamiccontroller.entity.ChangeRequest;
import com.ravi.dynamiccontroller.repository.ChangeRequestRepository;
import com.ravi.dynamiccontroller.service.impl.GenericServiceImpl;

@Service
public class ChangeRequestService extends GenericServiceImpl<ChangeRequest, Long> {
      public ChangeRequestService(ChangeRequestRepository repository) {
        super(repository);
    }
}
