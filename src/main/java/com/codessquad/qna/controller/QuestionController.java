package com.codessquad.qna.controller;

import com.codessquad.qna.domain.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("/questions")
@Controller
public class QuestionController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private static List<Question> questions = new ArrayList<>();

    public static List<Question> questions() {
        return Collections.unmodifiableList(questions);
    }

    @PostMapping("/")
    public String createQuestion(Question question) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        question.setDate(date);
        question.setIndex(questions.size() + 1);

        questions.add(question);
        logger.info(question.toString());

        return "redirect:/";
    }

    @GetMapping("/{index}")
    public String getQuestion(@PathVariable("index") long index, Model model) {
        model.addAttribute("question", questions.get((int) index - 1));
        return "/qna/show";
    }

}
