package com.qf.dao;

import com.qf.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    List<Address> findById(@Param("uid") int uid);

    void add(Address address);

    void updateDefault(@Param("id") int id,@Param("uid") int uid);

    void deleteAddress(@Param("id") int id);

    void update(Address address);

    Address findByAid(@Param("aid") int aid);
}
