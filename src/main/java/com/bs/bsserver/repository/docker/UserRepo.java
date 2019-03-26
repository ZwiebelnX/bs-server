package com.bs.bsserver.repository.docker;

import com.bs.bsserver.repository.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
