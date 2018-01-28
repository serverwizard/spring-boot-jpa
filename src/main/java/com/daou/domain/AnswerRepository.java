package com.daou.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by hongjong-wan on 2018. 1. 3..
 */
public interface AnswerRepository extends JpaRepository<Answer, Long>, QueryDslPredicateExecutor<Answer> {
}
