package com.rukiyesahin.miniproject.controllers;

import com.rukiyesahin.miniproject.dto.DashboardResponse;
import com.rukiyesahin.miniproject.entities.Counsellor;
import com.rukiyesahin.miniproject.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CounsellorController {

    // Dependency Injection
    private CounsellorService counsellorService;
    public CounsellorController(CounsellorService counsellorService){

        this.counsellorService = counsellorService;
    }

    @GetMapping("/")
    public String index(Model model) {

        Counsellor cobj = new Counsellor();

        // sending data from controller to view(UI)
        model.addAttribute("counsellor", cobj);

        //returning view name
        return "index";
    }
    @PostMapping("/login")
    public String login(Counsellor counsellor, HttpServletRequest request, Model model){

        Counsellor c = counsellorService.login(counsellor.getEmail(), counsellor.getPwd());
        if (c == null) {
            model.addAttribute("errMsg", "Invalid Email or Password");

            return "index";
        }else {

            //valid login, store counsellorId in session for future purpose

            HttpSession session = request.getSession(true);
            session.setAttribute("counsellorObj", c.getCounsellorId());

            return "redirect:/dashboard";
        }
    }

    @GetMapping("/dashboard")
    public String displayDashboard(HttpServletRequest req, Model model){
        //get existing session object
        HttpSession session = req.getSession(false);
        Integer counsellorId = (Integer) session.getAttribute("counsellorId");

        DashboardResponse dbresObj = counsellorService.getDashboardInfo(counsellorId);

        model.addAttribute("dashboardInfo", dbresObj);

        return "dashboard";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        Counsellor cobj = new Counsellor();

        // sending data from controller to view(UI)
        model.addAttribute("counsellor", cobj);

        return "register";
    }

    @PostMapping("/register")
    public String handleRegistration(Counsellor counsellor, Model model){

        Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
        if(byEmail != null){
            model.addAttribute("errMsg", "Email already exists! Please use a different email");
            return "register";
        }

        boolean isRegistered = counsellorService.register(counsellor);
        if(isRegistered){
            model.addAttribute("succMsg", "Registration Successful! Please login");
        }else{
            model.addAttribute("errMsg", "Registration Failed! Please try again");
        }

        return "register";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        //get existing session nad invalidate it
        HttpSession session = req.getSession(false);
        session.invalidate();

        //redirect to home page (login page)
        return "redirect:/";

    }

}
