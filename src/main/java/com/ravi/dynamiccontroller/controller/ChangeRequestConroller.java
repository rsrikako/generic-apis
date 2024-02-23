package com.ravi.dynamiccontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.dynamiccontroller.entity.ChangeRequest;
import com.ravi.dynamiccontroller.repository.ChangeRequestRepository;
import com.ravi.dynamiccontroller.service.ChangeRequestService;
import com.ravi.dynamiccontroller.specifications.GenericSpecifications;

@RestController
@RequestMapping("/api/data/change-request")
public class ChangeRequestConroller extends GenericController<ChangeRequest, Long>{
      @Autowired
    public ChangeRequestConroller(GenericSpecifications<ChangeRequest> specifications,
                                ChangeRequestRepository repository, ChangeRequestService service) {
        super(specifications, service);
        this.repository = repository;
    }
}
