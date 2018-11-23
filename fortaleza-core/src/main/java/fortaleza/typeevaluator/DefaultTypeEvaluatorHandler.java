package fortaleza.typeevaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultTypeEvaluatorHandler implements TypeEvaluatorHandler {

    private Map<String, TypePermissionEvaluator> mapString = new HashMap<>();

    private Map<Class<?>, TypePermissionEvaluator> mapClass = new HashMap<>();

    @Override
    public Optional<TypePermissionEvaluator> getTypePermissionEvaluator(Class clazz) {
        return Optional.ofNullable(mapClass.get(clazz));
    }

    @Override
    public Optional<TypePermissionEvaluator> getTypePermissionEvaluator(String type) {
        return Optional.ofNullable(mapString.get(type));
    }

    @Override
    public TypeEvaluatorHandler add(Class<?> type, TypePermissionEvaluator<?> typePermissionEvaluator) {
        mapClass.put(type, typePermissionEvaluator);
        mapString.put(type.getSimpleName(), typePermissionEvaluator);
        return this;
    }
}
