package com.lsx.service;

import com.lsx.model.Role;
import com.lsx.model.User;

import java.util.Map;

public interface LoginService {
    User addUser(Map<String, Object> map);

    Role addRole(Map<String, Object> map);

    User findByName(String name);
}
