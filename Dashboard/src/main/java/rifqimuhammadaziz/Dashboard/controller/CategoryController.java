package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/posts/categories")
    public String categories() {
        return "categories/categories";
    }
}
