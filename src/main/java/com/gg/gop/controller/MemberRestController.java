<<<<<<< HEAD
package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.gg.gop.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
=======
package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> 460304782af17c227702360b2e841954ad0105a1
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MemberRestController {

    @Autowired
    private MemberService memberService;

<<<<<<< HEAD
}

=======
    @PostMapping("/confirmusername")
    public ResponseEntity<Boolean> confirmUsername(@RequestParam("username") String username) {
        boolean result;

        if (username.trim().isEmpty()) {
            result = false;
        } else {
            result = !memberService.selectusername(username);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
>>>>>>> 460304782af17c227702360b2e841954ad0105a1
