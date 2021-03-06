package com.myFood.order.domain.service;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractService<T extends Object, ID extends Serializable> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Transactional
    public <S extends T> S save(S s) {
        return (S) getRepository().save(s);
    }

    @Transactional
    public <S extends T> Iterable<S> saveAll(Iterable<S> itrbl) {
        return getRepository().saveAll(itrbl);
    }

    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public Iterable<T> findAllById(Iterable<ID> itrbl) {
        return getRepository().findAllById(itrbl);
    }

    public long count() {
        return getRepository().count();
    }

    @Transactional
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Transactional
    public void delete(T t) {
        getRepository().delete(t);
    }

    @Transactional
    public void deleteAll(Iterable<? extends T> itrbl) {
        getRepository().deleteAll(itrbl);
    }

}
