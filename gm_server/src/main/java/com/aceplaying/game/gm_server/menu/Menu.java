package com.aceplaying.game.gm_server.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhy
 * @date 2021/3/29 17:11
 */
public class Menu {
    private int id;
    private String name;
    /**
     * 前端路由对应的path
     */
    private String path;
    /**
     * 前端路由指向的组件
     */
    private String component;
    private int parentId;
    private List<Menu> childList = new ArrayList<>();

    public Menu(int id, String name, String path, String component) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.component = component;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public List<Menu> getChildList() {
        return childList;
    }

    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }

    public Menu addChildMenu(Menu childMenu) {
        this.childList.add(childMenu);
        childMenu.setParentId(this.getId());
        return this;
    }
}
