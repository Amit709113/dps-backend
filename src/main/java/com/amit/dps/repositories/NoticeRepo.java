package com.amit.dps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.dps.entities.Notice;

public interface NoticeRepo extends JpaRepository<Notice, Integer> {

}
