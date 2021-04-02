package com.aceplaying.game.gm_server.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aceplaying.game.gm_server.dao.UserDao;
import com.aceplaying.game.gm_server.model.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author xhy
 * @date 2021/3/29 17:10
 */
@Service
public class MenuService implements ApplicationRunner {
    @Autowired
    private UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MenuFactory.buildMenu();
    }

    public List<Menu> getUserMenus(String username) {
        UserEntity user = userDao.findFirstByUsername(username);
        String menus = user.getMenus();
        if ("*".equals(menus)) {
            return MenuFactory.menuTemplates;
        } else {
            String[] menuStr = StringUtils.split(user.getMenus(),',');
            List<Menu> menuList = toTree(menuStr);
            return menuList;
        }
    }

    public List<Menu> toTree(String[] menuStr) {
        Map<Integer, Menu> tempMap = new HashMap<>();
        List<Menu> menuList = new ArrayList<>();
        if (menuStr != null) {
            for (String id : menuStr) {
                Menu menu = MenuFactory.menuTemplateMap.get(Integer.parseInt(id));
                Menu tempMenu = new Menu(menu.getId(), menu.getName(), menu.getPath(), menu.getComponent());
                tempMenu.setParentId(menu.getParentId());
                tempMap.put(menu.getId(), tempMenu);
            }
            Arrays.stream(menuStr).forEach(idStr -> {
                int id = Integer.parseInt(idStr);
                Menu menu = tempMap.get(id);
                int parentId = menu.getParentId();
                if (tempMap.containsKey(parentId)) {
                    Menu parentMenu = tempMap.get(parentId);
                    parentMenu.getChildList().add(menu);
                } else {
                    menuList.add(menu);
                }
            });
        }
        return menuList;
    }


}
