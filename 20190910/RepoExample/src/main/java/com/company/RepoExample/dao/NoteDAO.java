package com.company.RepoExample.dao;

import com.company.RepoExample.dto.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<Note, Integer> {

}
