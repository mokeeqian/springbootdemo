package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.dao.IWUserDao;
import cn.edu.ahut.springbootdemo.entity.WUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WUserService implements IWUserService{

    @Autowired
    IWUserDao wuserDao;
    @Override
    // 通过用户名来返回一个user对象
    public WUser getWuserByUsername(String username) {
        return wuserDao.findDistinctTopByUsername(username);
    }

    @Override
    public Page<WUser> getAllPageUserList(Pageable pageable) {
        return wuserDao.findAll(pageable);
    }

    @Override
    public void addUser(WUser wUser) {
        wuserDao.save(wUser);
    }

    @Override
    public void delUserById(Integer id) {
        wuserDao.deleteById(id);
    }

    @Override
    public WUser getUserById(Integer id) {
        return this.wuserDao.getOne(id);
    }

    @Override
    public List<WUser> getAllUsersByUsernameContains(String pattern) {
        return wuserDao.findWUsersByUsernameContains(pattern);
    }
}
