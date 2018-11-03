package ch.heigvd.amt.gamification.services.dao;

public class EntityNotFoundException extends Exception {

    private Object id;

    private Class cls;

    public EntityNotFoundException(Object id, Class cls) {
        this.id = id;
        this.cls = cls;
    }

    public Object getId() {
        return id;
    }

    public Class getCls() {
        return cls;
    }
}
