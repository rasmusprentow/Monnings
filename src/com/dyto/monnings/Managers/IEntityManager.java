package com.dyto.monnings.Managers;

import java.util.List;

import org.andengine.entity.shape.IShape;

public interface IEntityManager<T extends IShape, U extends IShape> {

    public abstract List<T> getColliders(U shape);

    public abstract void addEntity(T entity);

    public abstract List<T> getEntities();

    public abstract boolean checkForCollision(U shape);

    public abstract void removeEntity( U shape);

}