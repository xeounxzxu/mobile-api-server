package com.app.api.domain.review.Support;

import com.app.api.domain.review.Review;
import com.app.api.web.dto.PageableDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.app.api.domain.review.QReview.review;
import static com.app.api.utils.ApiDomainUtils.getOrder;
import static com.app.api.utils.ApiDomainUtils.isNotEmpty;

@Repository
public class ReviewSupportImpl extends QuerydslRepositorySupport implements ReviewSupport {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager  entityManager;

    public ReviewSupportImpl( JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(Review.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Page<Review> findByReview(PageableDto dto) {

        JPAQuery<Review> query = jpaQueryFactory
                .selectFrom(review);

        if(isNotEmpty(dto.getSort())){
            query.orderBy(getOrder(dto.getSort()).toArray(new OrderSpecifier[0]));
        }

        Pageable pageable = PageRequest.of(dto.getPage() , dto.getSize());

        query.limit(pageable.getPageSize()).offset(pageable.getOffset());

        QueryResults<Review> results = query.fetchResults();

        long total = results.getTotal();

        List<Review> result = results.getResults();

        return new PageImpl<>(result , pageable , total);
    }

}
