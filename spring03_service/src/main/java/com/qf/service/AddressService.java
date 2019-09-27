package com.qf.service;

import com.qf.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address> findById(int uid);

    void add(Address address);

    void updateDefault(int id, int uid);

    void deleteAddress(int id);

    void update(Address address);
}
