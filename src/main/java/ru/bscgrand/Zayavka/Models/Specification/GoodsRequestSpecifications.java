package ru.bscgrand.Zayavka.Models.Specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.GoodsRequest_;
import ru.bscgrand.Zayavka.Models.Specification.SearchCriteria;

import java.time.LocalDate;


public class GoodsRequestSpecifications implements Specification {

    private SearchCriteria criteria;

    public GoodsRequestSpecifications(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static Specification<GoodsRequest> subdivisionIs(String subdivision) {
        return new Specification<GoodsRequest>() {
            @Override
            public Predicate toPredicate(Root<GoodsRequest> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("subdivision"), subdivision);
            }
        };
    }

    public static Specification<GoodsRequest> dateOfPurchaseFrom(LocalDate localDate) {
        return new Specification<GoodsRequest>() {
            @Override
            public Predicate toPredicate(Root<GoodsRequest> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(GoodsRequest_.dateOfPurchaseRequest), localDate);
            }
        };
    }

    public static Specification<GoodsRequest> dateOfPurchaseTo(LocalDate localDateTo) {
        return new Specification<GoodsRequest>() {
            @Override
            public Predicate toPredicate(Root<GoodsRequest> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(GoodsRequest_.dateOfPurchaseRequest), localDateTo);
            }
        };
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                return builder.like(
//                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
//            } else {
//                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
        }
        return null;
    }

}
