package com.ducnm.tourapp.auth;

import com.ducnm.tourapp.config.JwtService;
import com.ducnm.tourapp.exception.InputException;
import com.ducnm.tourapp.model.ResponseObject;
import com.ducnm.tourapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            String token = authenticationService.register(request);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đăng ký thành công!", token)
            );
        } catch (InputException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> register(
            @RequestBody AuthenticationRequest request
    ) {
        Token token = authenticationService.authenticate(request);
        if (token != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đăng nhập thành công!", token)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("error", "Tài khoản, mật khẩu không đúng!", token)
        );
    }
    @PostMapping("/checkToken")
    public ResponseEntity<ResponseObject> checkToken(
            @RequestBody Token token
    ) {
        String tmp = jwtService.extractUsername(token.getToken());
        //System.out.println(tmp);
        if (jwtService.checkToken(token.getToken())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("error", "Token het han", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Token chua het han", token)
        );
    }
}
