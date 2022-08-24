package com.bank.document.project.repository;

import com.bank.document.project.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel,Long> {

    public List<CommentModel> findByDocId(String docId);
}
