package ru.budharain.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.budharain.model.Product;


public class ProductSpecification {

    public static Specification<Product> nameLike(String pattern) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + pattern + "%");
    }

    public static Specification<Product> byCompany(long companyId) {
        return (root, query, builder) -> builder.equal(root.get("company").get("id"), companyId);
    }
    public static Specification<Product> byCharacteristik(long charasteristikId) {
        return (root, query, builder) -> builder.equal(root.get("characteristik").get("id"), charasteristikId);
    }
    public static Specification<Product> byComment(long commentId) {
        return (root, query, builder) -> builder.equal(root.get("comment").get("id"), commentId);
    }
    public static Specification<Product> byDiscount(long discountId) {
        return (root, query, builder) -> builder.equal(root.get("discount").get("id"), discountId);
    }
}
