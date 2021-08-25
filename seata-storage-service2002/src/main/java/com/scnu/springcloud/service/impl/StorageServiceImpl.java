package com.scnu.springcloud.service.impl;

import com.scnu.springcloud.dao.StorageDao;
import com.scnu.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public int update(Long productId, Integer count) {
        //TODO 此处还需要判断库存是否足够
        return storageDao.update(productId,count);
    }
}
