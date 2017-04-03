package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by Robert on 03/04/2017.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "Página de inicio.";
    }
}
