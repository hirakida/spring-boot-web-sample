package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AppController {
    private final SessionBean sessionBean;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        log.info("{}", sessionBean);
        if (sessionBean.getUuid() == null) {
            sessionBean.setId(session.getId());
            sessionBean.setUuid(UUID.randomUUID().toString());
            sessionBean.setLocalDateTime(LocalDateTime.now());
        }
        model.addAttribute("appSession", sessionBean);
        return "index";
    }

    @PostMapping("/")
    public String clear(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}