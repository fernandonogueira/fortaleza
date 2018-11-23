package fortaleza;

public class PermissionAction {

    private String entityClass;

    private PermissionActionType type;

    public static PermissionAction of(String entityClass, PermissionActionType type) {
        PermissionAction action = new PermissionAction();
        action.setEntityClass(entityClass);
        action.setType(type);
        return action;
    }

    public static PermissionAction read(String entityClass) {
        return of(entityClass, PermissionActionType.READ);
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public PermissionActionType getType() {
        return type;
    }

    public void setType(PermissionActionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PermissionAction{" +
                "entityClass='" + entityClass + '\'' +
                ", type=" + type +
                '}';
    }
}
