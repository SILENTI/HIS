import com.controller.UserDisplyController;
import com.dao.UserDao;
import com.domain.User;
import com.service.UserService;
import com.util.MybatisUtil;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        List<User> users = userDao.selectUserList(1,10);
        System.out.println(users.toString());
    }
}
