package com.gladysz.library.repository;

import com.gladysz.library.domain.BookCopy;
import com.gladysz.library.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    long countByBookId(Long bookId);
    long countByBookIdAndStatus(Long bookId, Status status);
}
