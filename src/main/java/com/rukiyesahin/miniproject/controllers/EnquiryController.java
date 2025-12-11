package com.rukiyesahin.miniproject.controllers;

import com.rukiyesahin.miniproject.dto.DashboardResponse;
import com.rukiyesahin.miniproject.dto.ViewEnqsFilterRequest;
import com.rukiyesahin.miniproject.entities.Enquiry;
import com.rukiyesahin.miniproject.service.CounsellorService;
import com.rukiyesahin.miniproject.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EnquiryController {

    private EnquiryService enqService;
    public EnquiryController(EnquiryService enqService){
        this.enqService = enqService;
    }

    @PostMapping("/filter-enqs")
    public String filterEnquries(ViewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest req, Model model){
        HttpSession session = req.getSession(false);
        Integer counsellorId = (Integer) session.getAttribute("counsellorId");

        List<Enquiry> enqList = enqService.getEnquiriesWithFilter(viewEnqsFilterRequest, counsellorId);
        model.addAttribute("enquiries", enqList);

        return "viewEnqsPage";
    }
    @GetMapping("/view-enquiries")
    public String getEnquiries(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        Integer counsellorId = (Integer) session.getAttribute("counsellorId");

        List<Enquiry> enqList = enqService.getAllEnquiries(counsellorId);
        model.addAttribute("enquiries", enqList);

        //Search form binding object
        ViewEnqsFilterRequest filterReq = new ViewEnqsFilterRequest();
        model.addAttribute("viewEnqFilter", filterReq);


        return "viewEnqsPage";
    }

    @GetMapping("/enquiry")   //For creating a NEW enquiry
    public String addEnquiryPage(Model model){
        Enquiry enquiry = new Enquiry();
        model.addAttribute("enquiry", enquiry);
        return "enquiryForm";
    }

    @GetMapping("/editEnq")   //For editing an EXISTING enquiry
    public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model){
        Enquiry enquiry = enqService.getEnquiryById(enqId);
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
