package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    long countByBookId(Long bookId);
    long countByBookIdAndStatus(Long  bookId, Status status);
}
