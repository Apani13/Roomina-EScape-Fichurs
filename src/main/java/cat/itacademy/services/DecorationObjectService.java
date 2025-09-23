package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.DecorationObject;
import cat.itacademy.utils.ObjectErrorMessages;
import cat.itacademy.utils.ObjectSuccessMessages;

import java.util.ArrayList;

public class DecorationObjectService {

    ArrayList<DecorationObject> decorationObjects;

    public DecorationObjectService() {
        this.decorationObjects = new ArrayList<>();
    }

    public void addDecorationObject(DecorationObject decorationObject) {

        if (decorationObjects.contains(decorationObject)) {
            throw new DuplicateException(ObjectErrorMessages.OBJECT_DUPLICATED);
        }
        if (decorationObject.getName() == null || decorationObject.getName().isEmpty()) {
            throw new InvalidAttributeException(ObjectErrorMessages.OBJECT_NAME_NULL_EMPTY);
        }
        if (decorationObject.getMaterial() == null || decorationObject.getMaterial().isEmpty()) {
            throw new InvalidAttributeException(ObjectErrorMessages.OBJECT_MATERIAL_NULL_EMPTY);
        }
        if (decorationObject.getQuantity() >= 0) {
            throw new InvalidAttributeException(ObjectErrorMessages.OBJECT_QUANTITY_INVALID);
        }

        decorationObjects.add(decorationObject);
        System.out.println(ObjectSuccessMessages.OBJECT_SUCCES);
    }
}