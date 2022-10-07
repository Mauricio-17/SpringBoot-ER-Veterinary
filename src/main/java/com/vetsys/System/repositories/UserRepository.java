package com.vetsys.System.repositories;

import com.vetsys.System.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends
        JpaRepository<User,
                Integer>
{
}
