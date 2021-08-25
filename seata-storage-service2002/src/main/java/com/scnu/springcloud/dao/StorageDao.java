package com.scnu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    int update(@Param("productId") Long productId,@Param("count") Integer count);

}
