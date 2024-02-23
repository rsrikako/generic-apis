package com.ravi.dynamiccontroller.specifications;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Map;
import java.util.Set;

@Component
public class GenericSpecifications<T> {

    public Specification<T> byCriteria(Map<String, String> criteria, String sort) {
        Set<String> excludedKeys = Set.of("sort", "page", "size");
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                if (!excludedKeys.contains(entry.getKey())) {
                    predicate = builder.and(predicate, builder.equal(root.get(entry.getKey()), entry.getValue()));
                }
            }

            if (sort != null && !sort.isEmpty()) {
                query.orderBy(QueryUtils.toOrders(parseSort(sort, root, builder), root, builder));
            }

            return predicate;
        };
    }

    private Sort parseSort(String sort, Root<T> root, CriteriaBuilder builder) {
        String[] sortArr = sort.split(";");
        Sort.Order[] orders = new Sort.Order[sortArr.length];
    
        for (int i = 0; i < sortArr.length; i++) {
            String[] parts = sortArr[i].split(",");
            String property = parts[0];
            String direction = parts.length > 1 ? parts[1] : "asc";
    
            orders[i] = new Sort.Order(Sort.Direction.fromString(direction), property);
        }
    
        return Sort.by(orders);
    }
    
}