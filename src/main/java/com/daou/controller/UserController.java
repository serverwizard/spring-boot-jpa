package com.daou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.daou.util.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daou.domain.User;
import com.daou.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String list(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/enrollForm")
    public String enrollForm() {
        return "/user/enrollForm";
    }

    @PostMapping("")
    public String create(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/form")
    public String updateFrom(@PathVariable Long id, Model model, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromHttpSession(session);

        log.info(" PathVariable Long id : " + id + ", loginUser.getId() : " + loginUser.getId());
        log.info(" loginUser.matchID(id) : " + loginUser.matchID(id));

        if (!loginUser.matchID(id)) {
            throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
        }


        log.info(" id : " + id);
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);

        return "/user/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, User updateUser, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromHttpSession(session);

        if (!loginUser.matchID(id)) {
            throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
        }

        User user = userRepository.findOne(loginUser.getId());
        user.update(updateUser);

        // 언제 save, 언제 update인지 ?
        // id값으로 조회해온다음 update
        // 기존에 있는 id값인지 아닌지에 따라 판단
        userRepository.save(user);

        // 언제 redirect하고 언제 경로 쓰는지 구분 ??
        return "redirect:/users";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }


    @PostMapping("/login")
    public String login(User user, HttpSession session) {

        User loginUser = userRepository.findByUserId(user.getUserId());
        log.info(" loginUser info : " + loginUser);
        if (loginUser == null) {
            return "redirect:/users/loginForm";
        }

        if (!loginUser.matchPassword(user.getPassword())) {
            return "redirect:/users/loginForm";
        }

        // 로그인 성공시 session에 사용자 정보를 담는 이유?
        session.setAttribute("loginUser", loginUser);
        log.info(" session.getAttribute(\"loginUser\") " + loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }


}
