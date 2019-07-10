package com.example.springhibernatedemo.repository;

import com.example.springhibernatedemo.entity.SysUserDO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SysUserDO,Long> {

    @Override
    List<SysUserDO> findAll();

    @Override
    List<SysUserDO> findAll(Sort sort);

    @Override
    List<SysUserDO> findAllById(Iterable<Long> longs);

    @Override
    <S extends SysUserDO> List<S> saveAll(Iterable<S> entities);

    @Override
    void flush();

    @Override
    <S extends SysUserDO> S saveAndFlush(S entity);

    @Override
    void deleteInBatch(Iterable<SysUserDO> entities);

    @Override
    void deleteAllInBatch();

    @Override
    SysUserDO getOne(Long aLong);

    @Override
    <S extends SysUserDO> List<S> findAll(Example<S> example);

    @Override
    <S extends SysUserDO> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<SysUserDO> findAll(Pageable pageable);

    @Override
    <S extends SysUserDO> S save(S entity);

    @Override
    Optional<SysUserDO> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(SysUserDO entity);

    @Override
    void deleteAll(Iterable<? extends SysUserDO> entities);

    @Override
    void deleteAll();

    @Override
    <S extends SysUserDO> Optional<S> findOne(Example<S> example);

    @Override
    <S extends SysUserDO> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends SysUserDO> long count(Example<S> example);

    @Override
    <S extends SysUserDO> boolean exists(Example<S> example);
}
