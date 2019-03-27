package com.bs.bsserver.repository.docker;

import com.bs.bsserver.repository.entities.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findAllByNameLike(String name);


}
