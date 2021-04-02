package com.aceplaying.game.gm_server.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.aceplaying.game.gm_server.common.Result;
import com.aceplaying.game.gm_server.model.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhy
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    private Result login(HttpServletRequest request, @RequestBody User requestUser) throws IOException {
//        Subject subject = SecurityUtils.getSubject();
        UserEntity userEntity = userService.validateUser(requestUser.getUsername(), requestUser.getPassword());
        //没有此用户
        if (userEntity == null) {
            return Result.err("用户名或密码错误");
        }
        HttpSession session = request.getSession();
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());

        session.setAttribute("loginUser", user);
//        UsernamePasswordToken token = new UsernamePasswordToken(requestUser.getUsername(), requestUser.getPassword());
//        token.setRememberMe(true);
//        subject.login(token);
        return Result.ok("登录成功 ", user);
    }

    @PostMapping("/logout")
    private Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return Result.ok("退出成功");
    }

    @PostMapping("/createUser")
    private Result createUser(HttpServletRequest request, @RequestBody User requestUser) throws IOException {
        boolean result = userService.createUser(requestUser.getUsername(), requestUser.getPassword());
        if (!result) {
            return Result.err("创建失败");
        }
        return Result.ok("创建成功");
    }

}
