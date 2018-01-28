package com.daou.controller;

import com.daou.domain.Question;
import com.daou.domain.QuestionRepository;
import com.daou.vo.PageMaker;
import com.daou.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

//    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping("/")
    public String home(PageVO vo, Model model) {
//                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 2) Pageable pageable) {

        Pageable pageable = vo.makePageable(0, "id");
        System.out.println("vo.getKeyword() : " + vo.getKeyword());

        // 페이징 처리된 결과가 나옴 (depend on page, size)
        Page<Question> result = questionRepository.findAll(questionRepository.makePredicate(null, vo.getKeyword()), pageable);
        model.addAttribute("result", new PageMaker(result));

        return "index";

    }

}