package com.sadang.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadang.lotto.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
}