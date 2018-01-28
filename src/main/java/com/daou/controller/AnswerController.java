package com.daou.controller;

import com.daou.domain.*;
import com.daou.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * Created by hongjong-wan on 2017. 7. 2..
 */
@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("")
    public String create(@PathVariable Long questionId, String contents, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/user/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromHttpSession(session);
        Question question = questionRepository.findOne(questionId);
        System.out.println(question.toString());

        Answer answer = new Answer(loginUser, question, contents);
        answerRepository.save(answer);

        return String.format("redirect:/questions/%d", questionId);
    }


    // 삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long questionId, @PathVariable Long id, Model model, HttpSession session) {

        Question question = questionRepository.findOne(id);

        Answer answer = answerRepository.findOne(id);
        Result result = valid(answer, session);

        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/loginForm";
        }

        answerRepository.delete(id);
        return String.format("redirect:/questions/%d", questionId);
    }

    public Result valid(Answer answer, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인이 필요합니다.");
        }

        User sessionedUser = HttpSessionUtils.getUserFromHttpSession(session);
        if (!answer.isSameWriter(sessionedUser)) {
            return Result.fail("자신이 작성한 댓글만 수정,삭제를 하실 수 있습니다.");
        }
        return Result.ok();
    }

}
