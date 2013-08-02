package com.dyto.monnings.Managers;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.shape.IShape;


abstract public class AbstractEntityManager<T extends IShape, U extends IShape> implements IEntityManager<T,U> {

    protected List<T> entities;

    protected  AbstractEntityManager()
    {
        entities = new ArrayList<T>();
    }
    
    /* (non-Javadoc)
     * @see com.dyto.monnings.IEntityManager#getColliders(T)
     */
    @Override
    public   List<T> getColliders(U shape) {
        List<T> list = new ArrayList<T>();
        for(T entity : getEntities())
        {
            if(shape.collidesWith(entity))
            {
                list.add(entity);
            }
        }
        return list;
    }
    

   

  

    /* (non-Javadoc)
     * @see com.dyto.monnings.IEntityManager#addEntity(T)
     */
    @Override
    public void addEntity(T entity) {
        this.entities.add(entity);
    }

    /* (non-Javadoc)
     * @see com.dyto.monnings.IEntityManager#getEntities()
     */
    @Override
    public List<T> getEntities() {
        return entities;
    }

    /* (non-Javadoc)
     * @see com.dyto.monnings.IEntityManager#checkForCollision(T)
     */
    @Override
    public boolean checkForCollision(U shape) {
        for(T entity : getEntities())
        {
            if(shape.collidesWith(entity))
            {
                return true;
            }
        }
        return false;
    }

    public void removeEntity(U shape)
    {
        this.entities.remove(shape);
        
    }
}