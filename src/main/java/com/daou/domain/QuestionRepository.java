package com.daou.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by hongjong-wan on 2017. 7. 2..
 */
public interface QuestionRepository extends JpaRepository<Question, Long>, QueryDslPredicateExecutor<Question> {

    
    public default Predicate makePredicate(String type, String keyword) {

        BooleanBuilder builder = new BooleanBuilder();

        QQuestion question = QQuestion.question;

        builder.and(question.id.gt(0));

        if (keyword == null) {
            return builder;
        }

        builder.and(question.title.like("%" + keyword + "%"));

//        switch (type) {
//            case "t":
//                builder.and(question.title.like("%" + keyword + "%"));
//                break;
//            case "c":
//                builder.and(question.contents.like("%" + keyword + "%"));
//                break;
//            case "w":
//                builder.and(question.writer.id.like("%" + keyword + "%"));
//                break;
//        }
        return builder;
    }
}
