package com.dao;

import org.springframework.data.repository.CrudRepository;

import com.dao.plus.DictDaoPlus;
import com.entity.Dict;

public interface DictDao extends CrudRepository<Dict, Integer>, DictDaoPlus{

}