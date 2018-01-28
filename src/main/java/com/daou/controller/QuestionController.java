package com.daou.controller;

import com.daou.domain.Question;
import com.daou.domain.QuestionRepository;
import com.daou.domain.Result;
import com.daou.domain.User;
import com.daou.util.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hongjong-wan on 2017. 7. 2..
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionRepository questionRepository;


    @GetMapping("/form")
    public String form(HttpSession session) {
        log.info(" QuestionController form() start... ");
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/user/loginForm";
        }

        return "/qna/enrollForm";
    }

    @PostMapping("")
    public String create(String title, String contents, HttpSession session) {
        log.info(" QuestionController create() start... ");
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/user/loginForm";
        }

        User user = HttpSessionUtils.getUserFromHttpSession(session);

        Question question = new Question(user, title, contents);
        log.info(" question : " + question);
        questionRepository.save(question);

        return "redirect:/";
    }

    // 상세보기
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, HttpSession session, Model model) {
        log.info(" QuestionController show() start... ");

        model.addAttribute("question", questionRepository.findOne(id));

        return "/qna/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {

        Question question = questionRepository.findOne(id);
        Result result = valid(question, session);


        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/loginForm";
        }

        model.addAttribute("question", question);
        return "/qna/updateForm";
    }

    private boolean hasPermission(Question question, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        User sessionedUser = HttpSessionUtils.getUserFromHttpSession(session);
        if (!question.isSameWriter(sessionedUser)) {
            throw new IllegalStateException("자신이 작성한 글만 수정,삭제를 하실 수 있습니다.");
        }
        return true;
    }


    public Result valid(Question question, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인이 필요합니다.");
        }

        User sessionedUser = HttpSessionUtils.getUserFromHttpSession(session);
        if (!question.isSameWriter(sessionedUser)) {
            return Result.fail("자신이 작성한 글만 수정,삭제를 하실 수 있습니다.");
        }
        return Result.ok();
    }

    // 업데이트
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Question updateQuestion, Model model, HttpSession session) {

        Question question = questionRepository.findOne(id);
        Result result = valid(question, session);


        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/loginForm";
        }

        question.update(updateQuestion.getTitle(), updateQuestion.getContents());
        questionRepository.save(question);
        return String.format("redirect:/questions/%d", id);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model, HttpSession session) {

        Question question = questionRepository.findOne(id);
        Result result = valid(question, session);

        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/loginForm";
        }

        questionRepository.delete(id);
        return "redirect:/";
    }

}
