package com.rukiyesahin.miniproject.controllers;

import com.rukiyesahin.miniproject.entities.Enquiry;
import com.rukiyesahin.miniproject.service.EnquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

    private EnquiryService enqService;

    public EnquiryController(EnquiryService enqService){
        this.enqService = enqService;
    }

    @GetMapping("/enquiry")
    public String addEnquiryPage(Model model){
        Enquiry enqObj = new Enquiry();
        model.addAttribute("enq", enqObj);
        return "enquiryForm";
    }


}
