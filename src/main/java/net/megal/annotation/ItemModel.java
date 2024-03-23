package net.megal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemModel {
    ModelType value();

    public enum ModelType {
        GENERATED,
        ARMOR,
        HANDHELD,
        HANDHELD2X,
        BOW
    }
}
