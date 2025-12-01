package com.rukiyesahin.miniproject.service;

import com.rukiyesahin.miniproject.dto.DashboardResponse;
import com.rukiyesahin.miniproject.entities.Counsellor;

public interface CounsellorService {

    public Counsellor findByEmail(String email);
    public boolean register(Counsellor counsellor);
    public Counsellor login(String email, String pwd);
    public DashboardResponse getDashboardInfo(Integer counsellorId);

}
