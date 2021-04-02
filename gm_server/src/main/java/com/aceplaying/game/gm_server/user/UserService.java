package com.aceplaying.game.gm_server.user;

import com.aceplaying.game.gm_server.dao.UserDao;
import com.aceplaying.game.gm_server.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xhy
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 存放登陆用户信息
     */
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<User>();

    /**
     * 把LoginUser放入到ThreadLocal中
     *
     * @param user
     */
    public static final void pushUser(User user) {
        threadLocal.set(user);
    }

    /**
     * 从ThreadLocal中remove LoginUser
     */
    public static final void popUser() {
        threadLocal.remove();
    }


    /**
     * 得到用户登录的LoginServerId
     *
     * @return 有则返回LoginServerId, 没有返回null
     */
    public static final User getLoginUser() {
        User _user = threadLocal.get();
        if (_user != null) {
            return _user;
        } else {
            return null;
        }
    }


    /**
     * 根据用户名和密码,返回用户
     *
     * @param u 用户名
     * @param p 密码
     * @return 系统用户
     * @throws Exception
     */
    public UserEntity validateUser(String u, String p) {
        UserEntity user = userDao.findFirstByUsername(u);
        if (user != null) {
            if (p != null && p.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean createUser(String u, String p) {
        UserEntity user = userDao.findFirstByUsername(u);
        if (user != null) {
            return false;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(u);
        userEntity.setPassword(p);
        userDao.save(userEntity);
        return true;
    }


}
