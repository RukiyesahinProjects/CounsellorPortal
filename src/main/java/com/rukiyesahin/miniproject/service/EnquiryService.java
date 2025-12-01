package com.rukiyesahin.miniproject.service;

import com.rukiyesahin.miniproject.dto.ViewEnqsFilterRequest;
import com.rukiyesahin.miniproject.entities.Enquiry;

import java.util.List;

public interface EnquiryService {

    public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception;
    public List<Enquiry> getAllEnquiries(Integer counsellorId);
    public List<Enquiry> getEnquiriesWithFilter(ViewEnqsFilterRequest filterReq,Integer counsellorId);
    public Enquiry getEnquiryById(Integer enqId);//require for edit fonctionality



}
