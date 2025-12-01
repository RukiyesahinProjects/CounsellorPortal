package com.rukiyesahin.miniproject.service;

import ch.qos.logback.core.util.StringUtil;
import com.rukiyesahin.miniproject.dto.ViewEnqsFilterRequest;
import com.rukiyesahin.miniproject.entities.Counsellor;
import com.rukiyesahin.miniproject.entities.Enquiry;
import com.rukiyesahin.miniproject.repos.CounsellorRepo;
import com.rukiyesahin.miniproject.repos.EnquiryRepo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryRepo enqRepo;
    private CounsellorRepo counsellorRepo;

    public EnquiryServiceImpl(EnquiryRepo enqRepo, CounsellorRepo counsellorRepo) {

        this.enqRepo = enqRepo;
        this.counsellorRepo = counsellorRepo;
    }

    @Override
    public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception {
        Counsellor counsellor = counsellorRepo.findById(counsellorId).orElse(null);
        if(counsellor==null){
            throw new Exception("Counsellor not found with id: " + counsellorId);
        }
        enq.setCounsellor(counsellor);
        Enquiry save = enqRepo.save(enq);

        if (save.getEnqId() != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Enquiry> getAllEnquiries(Integer counsellorId) {

        return enqRepo.getEnquiriesByCounsellorId(counsellorId);
    }

    @Override
    public List<Enquiry> getEnquiriesWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
        //QBE implementation (Dynamic Query Preparation) can be done here
        Enquiry enq = new Enquiry();  //entity object

        if(StringUtils.isNotEmpty((filterReq.getClassMode()))){
            enq.setClassMode(filterReq.getClassMode());
        }
        if(StringUtils.isNotEmpty((filterReq.getCourseName()))){
            enq.setEnquiryStatus(filterReq.getCourseName());
        }
        if(StringUtils.isNotEmpty((filterReq.getEnqStatus()))){
            enq.setEnquiryStatus(filterReq.getEnqStatus());
        }
        Counsellor c = counsellorRepo.findById(counsellorId).orElse(null);
        enq.setCounsellor(c);

        Example<Enquiry> of = Example.of(enq);//jpa method to prepare dynamic query
        List<Enquiry> enqList = enqRepo.findAll(of);

        return enqList;
    }

    @Override
    public Enquiry getEnquiryById(Integer enqId) {

        return enqRepo.findById(enqId).orElse(null);
    }
}
