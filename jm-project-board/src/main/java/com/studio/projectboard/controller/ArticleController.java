package com.studio.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map) {

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId,ModelMap map) {

        return "articles/detail";
    }

}
