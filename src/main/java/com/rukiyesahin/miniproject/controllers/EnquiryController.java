package com.rukiyesahin.miniproject.controllers;

import com.rukiyesahin.miniproject.dto.DashboardResponse;
import com.rukiyesahin.miniproject.entities.Enquiry;
import com.rukiyesahin.miniproject.service.CounsellorService;
import com.rukiyesahin.miniproject.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnquiryController {

    private EnquiryService enqService;
    public EnquiryController(EnquiryService enqService){
        this.enqService = enqService;
    }

    @GetMapping("/enquiry")
    public String addEnquiryPage(Model model){
        Enquiry enquiry = new Enquiry();
        model.addAttribute("enquiry", enquiry);
        return "enquiryForm";
    }

    @PostMapping("/addEnq")
    public String handleAddEnquiry(Enquiry enquiry, HttpServletRequest req, Model model) throws  Exception{
        //get existing session object

        HttpSession session = req.getSession(false);
        Integer counsellorId =(Integer) session.getAttribute("counsellorId");

        boolean isSaved = enqService.addEnquiry(enquiry, counsellorId);

        if (isSaved){
            model.addAttribute("smsg", "Enqiury Added");
        }else {
            model.addAttribute("emsg", "Failed To Add Enquiry");
        }

        enquiry = new Enquiry();
        model.addAttribute("enquiry", enquiry);


        return "enquiryForm";
    }


}
