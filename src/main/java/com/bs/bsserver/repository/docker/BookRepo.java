package com.bs.bsserver.repository.docker;

import com.bs.bsserver.repository.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<BookEntity, Long> {
}
