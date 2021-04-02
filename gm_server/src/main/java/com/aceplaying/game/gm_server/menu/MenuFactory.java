package com.aceplaying.game.gm_server.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhy
 * @date 2021/3/29 17:17
 */
public class MenuFactory {
    public static List<Menu> menuTemplates;
    public static Map<Integer, Menu> menuTemplateMap;

    public static void buildMenu() {
        menuTemplates = new ArrayList<>();
        menuTemplateMap = new HashMap<>();
        menuTemplates.add(createMenu(100, "首页", "", ""));
        menuTemplates.add(createMenu(200, "玩家管理", "", "")
                .addChildMenu(createMenu(201, "玩家信息", "", ""))
        );
        menuTemplates.add(createMenu(300, "游戏管理", "", "")
                .addChildMenu(createMenu(301, "游戏公告", "", ""))
                .addChildMenu(createMenu(302, "活动", "", ""))
        );
        menuTemplates.add(createMenu(400, "日志查询", "", "")
                .addChildMenu(createMenu(401, "测试1", "", ""))
                .addChildMenu(createMenu(402, "测试2", "", ""))
        );
        menuTemplates.add(createMenu(500, "系统管理", "", "")
                .addChildMenu(createMenu(501, "用户管理", "", ""))
                .addChildMenu(createMenu(502, "用户创建", "/home/createUser", "user/CreateUser"))
                .addChildMenu(createMenu(503, "修改密码", "", ""))
                .addChildMenu(createMenu(504, "权限管理", "", ""))
        );
        menuTemplates.add(createMenu(600, "测试", "", "")
                .addChildMenu(createMenu(601, "退出", "/login", ""))
        );
    }

    private static Menu createMenu(int id, String name, String path, String component) {
        Menu menu = new Menu(id, name, path, component);
        menuTemplateMap.put(menu.getId(), menu);
        return menu;
    }

}
