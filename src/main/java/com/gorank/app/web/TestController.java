package com.gorank.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

  @GetMapping(value = "/test")
  public String test(Model model) {
    return "test";
  }
}
