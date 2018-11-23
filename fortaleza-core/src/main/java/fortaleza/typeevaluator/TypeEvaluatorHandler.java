package fortaleza.typeevaluator;

import java.util.Optional;

public interface TypeEvaluatorHandler {

    Optional<TypePermissionEvaluator> getTypePermissionEvaluator(Class clazz);

    Optional<TypePermissionEvaluator> getTypePermissionEvaluator(String type);

    TypeEvaluatorHandler add(Class<?> type, TypePermissionEvaluator<?> typePermissionEvaluator);

}
