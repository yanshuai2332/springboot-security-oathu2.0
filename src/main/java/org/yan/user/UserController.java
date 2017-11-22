package org.yan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yanshuai
 * @date： 2017/11/15
 */
@RequestMapping(value = "users")
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("signin")
    public String signin(String mobile,String password){
        return userService.signin(mobile,password);

    }

    @GetMapping("test")
    @PreAuthorize("hasAuthority('PERM_APP_POLICE')")
    public String test(){
//        return UserContext.getCurrentUser().toString();
        return "ddd";
    }
}
