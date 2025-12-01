package com.rukiyesahin.miniproject.service;

import com.rukiyesahin.miniproject.dto.DashboardResponse;
import com.rukiyesahin.miniproject.entities.Counsellor;
import com.rukiyesahin.miniproject.entities.Enquiry;
import com.rukiyesahin.miniproject.repos.CounsellorRepo;
import com.rukiyesahin.miniproject.repos.EnquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    @Autowired
    private CounsellorRepo counsellorRepo;
    @Autowired
    private EnquiryRepo enqRepo;
    public CounsellorServiceImpl(CounsellorRepo counsellorRepo, EnquiryRepo enqRepo) {
        this.counsellorRepo = counsellorRepo;
        this.enqRepo = enqRepo;
    }
    @Override
    public Counsellor findByEmail(String email) {
        return counsellorRepo.findByEmail(email);

    }
    @Override
    public boolean register(Counsellor counsellor) {
        Counsellor savedCounsellor = counsellorRepo.save(counsellor);
        if(null!= savedCounsellor.getCounsellorId()){
            return true;
        }
        return false;
    }
    @Override
    public Counsellor login(String email, String pwd) {
        return counsellorRepo.findByEmailAndPwd(email, pwd);
    }
    @Override
    public DashboardResponse getDashboardInfo(Integer counsellorId) {

        DashboardResponse response = new DashboardResponse();

        List<Enquiry> enqlist = enqRepo.getEnquiriesByCounsellorId(counsellorId);
        int totalEnq = enqlist.size();
        int enrolledEnqs = enqlist.stream()
                                 .filter(e -> e.getEnquiryStatus().equals("Enrolled") )
                                 .collect(Collectors.toList())
                                 .size();

        int lostEnqs = enqlist.stream()
                              .filter(e -> e.getEnquiryStatus().equals("Lost") )
                              .collect(Collectors.toList())
                              .size();

        int openEnqs = enqlist.stream()
                              .filter(e -> e.getEnquiryStatus().equals("Open") )
                              .collect(Collectors.toList())
                              .size();
        response.setTotalEnqs(totalEnq);
        response.setEnrolledEnqs(enrolledEnqs);
        response.setLostEnqs(lostEnqs);
        response.setOpenEnqs(openEnqs);
        return response;
    }
}

