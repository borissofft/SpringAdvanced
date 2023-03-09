package bg.softuni.errors.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object was not found.") // Status code 404
public class ObjectNotFoundException extends RuntimeException {
    private long objectId;
    private String objectType;

    public ObjectNotFoundException(long objectId, String objectType) {
        super("Object with ID " + objectId + " and type " + objectType + " not found!");
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public long getObjectId() {
        return objectId;
    }

    public ObjectNotFoundException setObjectId(long objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getObjectType() {
        return objectType;
    }

    public ObjectNotFoundException setObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }
}
