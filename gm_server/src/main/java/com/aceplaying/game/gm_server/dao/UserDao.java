package com.aceplaying.game.gm_server.dao;

import com.aceplaying.game.gm_server.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xhy
 * @date 2021/3/29 15:46
 */
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    /**
     * 根据名称进行查询用户列表
     *
     * @param userName
     * @return
     */
    UserEntity findFirstByUsername(String userName);
}
