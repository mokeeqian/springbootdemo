package cn.edu.ahut.springbootdemo.service;

import cn.edu.ahut.springbootdemo.entity.WUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWUserService {
    WUser getWuserByUsername(String username);
    Page<WUser> getAllPageUserList(Pageable pageable);
    void  addUser(WUser wUser);
    void delUserById(Integer id);
    WUser getUserById(Integer id);

}
