package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends CrudRepository<Author, Long>
{

    @Query(value = "SELECT COUNT(*) as count FROM wrote WHERE authorid = :authorid AND bookid = :bookid",
        nativeQuery = true)
    JustTheCount checkWroteCombo(
        long userid,
        long roleid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM wrote WHERE authorid = :authorid AND bookid = :bookid")
    void deleteWrote(
        long authorid,
        long bookid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wrote(authorid, bookid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:authorid, :bookid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
        nativeQuery = true)
    void insertUserRoles(
        String uname,
        long authorid,
        long bookid);

}
