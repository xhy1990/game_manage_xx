package com.aceplaying.game.gm_server.menu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.aceplaying.game.gm_server.common.Result;
import com.aceplaying.game.gm_server.model.UserEntity;
import com.aceplaying.game.gm_server.user.User;
import com.aceplaying.game.gm_server.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhy
 */
@RestController
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("/menu")
    private Result menu(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginUser");
        if(user == null){
            return Result.err("请重新登录");
        }
        return Result.ok("", menuService.getUserMenus(user.getUsername()));
    }

}
