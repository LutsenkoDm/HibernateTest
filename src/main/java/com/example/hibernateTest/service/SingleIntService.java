package com.example.hibernateTest.service;

import com.example.hibernateTest.entities.SingleInt;
import com.example.hibernateTest.repositories.SingleIntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SingleIntService {

    @Autowired
    SingleIntRepository singleIntRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create3() {
        singleIntRepository.save(new SingleInt(10));
        singleIntRepository.save(new SingleInt(20));
        singleIntRepository.save(new SingleInt(30));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Integer integer) {
        SingleInt singleInt = singleIntRepository.findById(1L).get();
        singleInt.setInteger(integer);
        singleIntRepository.save(singleInt);
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            System.out.println("!!! Interrupt");
        }
    }


}
