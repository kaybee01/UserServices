package org.scaler.springsecuritytest.Controler;

import org.scaler.springsecuritytest.DTO.LoginRequestDTO;
import org.scaler.springsecuritytest.DTO.LoginResponseDTO;
import org.scaler.springsecuritytest.DTO.SignUpRequest;
import org.scaler.springsecuritytest.DTO.UserDTO;
import org.scaler.springsecuritytest.Model.Token;
import org.scaler.springsecuritytest.Model.User;
import org.scaler.springsecuritytest.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpRequest request){
        User u = userService.signUp(request.getName(),request.getEmail(),request.getPassword());
        return UserDTO.from(u);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request){
        Token t=  userService.loginM(request.getEmail(),request.getPassword());
        return LoginResponseDTO.from(t);
    }

    @PutMapping("/logout/{token}")
    public ResponseEntity<Token> logout(@PathVariable("token") String token){

        try {
           Token t = userService.logoutM(token);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        catch (Exception e){
return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{token}")
    public UserDTO validate(@PathVariable("token") String token){
        User u = userService.validateUser(token);
        return UserDTO.from(u);
    }
}
