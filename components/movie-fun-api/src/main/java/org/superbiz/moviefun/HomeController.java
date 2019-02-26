package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> cf317882c797a5c5edd36f52446d5a8eb5c219cd
