package cn.edu.ahut.springbootdemo.dao;

import cn.edu.ahut.springbootdemo.entity.WUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IWUserDao extends JpaRepository<WUser,Integer>, JpaSpecificationExecutor {
    WUser findDistinctTopByUsername(String username);
    WUser findWUserByIdEquals(Integer id);

    // 模糊查询
    List<WUser> findWUsersByUsernameContains(String pattern);

}
