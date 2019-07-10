package com.example.springhibernatedemo.api;

import com.example.springhibernatedemo.bo.SysUserBO;
import com.example.springhibernatedemo.entity.SysUserDO;
import com.example.springhibernatedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public List<SysUserBO> findUserDesc(){
        List<SysUserBO> sysUserBOList = userService.findAll(Sort.by(Sort.Direction.DESC,"id"));
        return sysUserBOList;
    }

    @RequestMapping("/saveAll")
    public List<SysUserBO> saveAll(){
        List<SysUserBO> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SysUserBO userEntity = new SysUserBO();
            userEntity.setUsername("hehe");
            userEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            userList.add(userEntity);
        }
        List<SysUserBO> sysUserDOS = userService.saveAll(userList);
        return sysUserDOS;
    }

}
