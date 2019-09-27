package com.qf.service.impl;

import com.qf.dao.AddressMapper;
import com.qf.pojo.Address;
import com.qf.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Address> findById(int uid) {
        return addressMapper.findById(uid);
    }

    @Override
    public void add(Address address) {
        addressMapper.add(address);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDefault(int id, int uid) {
        addressMapper.updateDefault(id,uid);
    }

    @Override
    public void deleteAddress(int id) {
        addressMapper.deleteAddress(id);
    }

    @Override
    public void update(Address address) {
        addressMapper.update(address);
    }
}
