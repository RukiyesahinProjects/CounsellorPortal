package com.rukiyesahin.miniproject.repos;

import com.rukiyesahin.miniproject.entities.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

    // select * from counsellor where email=:email
    public Counsellor findByEmail(String email);
    // select * from counsellor where email=:email and pwd=:pwd
    public Counsellor findByEmailAndPwd(String email, String pwd);

}
