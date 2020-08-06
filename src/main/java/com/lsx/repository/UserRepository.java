package com.lsx.repository;

import com.lsx.model.User;

public interface UserRepository extends BaseRepository<User,Long> {

//    List<User> findByAge(Integer age);

    User findByName(String name);

    User findById(Long Id);
}
