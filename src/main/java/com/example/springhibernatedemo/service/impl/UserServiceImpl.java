package com.example.springhibernatedemo.service.impl;

import com.example.springhibernatedemo.bo.SysUserBO;
import com.example.springhibernatedemo.core.BeanMapperUtil;
import com.example.springhibernatedemo.entity.SysUserDO;
import com.example.springhibernatedemo.repository.UserRepository;
import com.example.springhibernatedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<SysUserBO> findAll() {
        return BeanMapperUtil.mapList(userRepository.findAll(), SysUserBO.class);
    }

    @Override
    public List<SysUserBO> findAll(Sort sort) {
        return BeanMapperUtil.mapList(userRepository.findAll(sort), SysUserBO.class);
    }

    @Override
    public List<SysUserBO> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public List<SysUserBO> saveAll(List<SysUserBO> userBOList) {
        List<SysUserDO> userDOList =  BeanMapperUtil.mapList(userBOList,SysUserDO.class);
        return BeanMapperUtil.mapList(userRepository.saveAll(userDOList), SysUserBO.class);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends SysUserBO> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<SysUserBO> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public SysUserBO getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends SysUserBO> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends SysUserBO> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<SysUserBO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SysUserBO> S save(S entity) {
        return null;
    }

    @Override
    public Optional<SysUserBO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(SysUserBO entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends SysUserBO> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends SysUserBO> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends SysUserBO> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SysUserBO> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends SysUserBO> boolean exists(Example<S> example) {
        return false;
    }
}
