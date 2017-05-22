/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.persistence.dao;

import java.io.Serializable;

import javax.persistence.PersistenceException;

/**
 * INTERFACE DAO GENERICO
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 * @param <T>
 * @param <PK>
 */
public interface GenericDAO<T, PK extends Serializable> {

	PK save(T objeto) throws PersistenceException;

	void update(T objeto) throws PersistenceException;

}
