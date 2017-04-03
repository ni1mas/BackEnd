package com.example.Controller;

/**
 * Created by Robert on 02/04/2017.
 */
import java.util.concurrent.atomic.AtomicLong;

import com.example.Models.Saludo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value= "/saludo", method = RequestMethod.GET)
    public Saludo greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Saludo(counter.incrementAndGet(),
                String.format(template, name));
    }
}
