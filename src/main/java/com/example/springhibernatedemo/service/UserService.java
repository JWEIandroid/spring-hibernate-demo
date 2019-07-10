package com.example.springhibernatedemo.service;

import com.example.springhibernatedemo.bo.SysUserBO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserService  {

    List<SysUserBO> findAll();

    
    List<SysUserBO> findAll(Sort sort);

    
    List<SysUserBO> findAllById(Iterable<Long> longs);


    List<SysUserBO> saveAll(List<SysUserBO> userList);

    
    void flush();

    
    <S extends SysUserBO> S saveAndFlush(S entity);

    
    void deleteInBatch(Iterable<SysUserBO> entities);

    
    void deleteAllInBatch();

    
    SysUserBO getOne(Long aLong);

    
    <S extends SysUserBO> List<S> findAll(Example<S> example);

    
    <S extends SysUserBO> List<S> findAll(Example<S> example, Sort sort);

    
    Page<SysUserBO> findAll(Pageable pageable);

    
    <S extends SysUserBO> S save(S entity);

    
    Optional<SysUserBO> findById(Long aLong);

    
    boolean existsById(Long aLong);

    
    long count();

    
    void deleteById(Long aLong);

    
    void delete(SysUserBO entity);

    
    void deleteAll(Iterable<? extends SysUserBO> entities);

    
    void deleteAll();

    
    <S extends SysUserBO> Optional<S> findOne(Example<S> example);

    
    <S extends SysUserBO> Page<S> findAll(Example<S> example, Pageable pageable);

    
    <S extends SysUserBO> long count(Example<S> example);

    
    <S extends SysUserBO> boolean exists(Example<S> example);
}
