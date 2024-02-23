package com.ravi.dynamiccontroller.repository;

import org.springframework.stereotype.Repository;

import com.ravi.dynamiccontroller.entity.ChangeRequest;

@Repository
public interface ChangeRequestRepository extends GenericRepository<ChangeRequest, Long> {
}