package com.bank.document.project.repository;

import com.bank.document.project.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel,Long> {

    public List<PostModel> findByDocId(String docId);
}
