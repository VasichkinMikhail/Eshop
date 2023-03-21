package ru.budharain.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.budharain.model.User;

public final class UserSpecification {
    public static Specification<User> usernamePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("username"), prefix + "%");
    }
}
