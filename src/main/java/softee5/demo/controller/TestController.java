package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class TestController {

    @GetMapping("/")
    public String home() {
        log.info("home");
        log.info("hi");
        return "server running";
    }
}
