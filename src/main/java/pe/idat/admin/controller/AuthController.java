package pe.idat.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.idat.admin.service.UserService;

@Controller
public class AuthController {
    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "Auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        if (service.findByUsername(username).isPresent()) {
            return "redirect:/register?error";
        }

        service.saveUser(username, password);
        return "redirect:/login";
    }
}
