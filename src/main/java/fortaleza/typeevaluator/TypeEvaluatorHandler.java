package fortaleza.typeevaluator;

import java.util.Optional;

public interface TypeEvaluatorHandler {
    <P> Optional<TypePermissionEvaluator<P>> getTypePermissionEvaluator(Class<P> clazz);

    <P> Optional<TypePermissionEvaluator<P>> getTypePermissionEvaluator(String type);
}
