/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service.impl;

import java.io.Serializable;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import com.datakeeper.persistence.dao.GenericDAO;
import com.datakeeper.service.GenericService;
import com.datakeeper.service.assembler.GenericAssembler;

/**
 * IMPLEMENTACIÓN DE LA INTERFACE SERVICIO GENERICO
 * 
 * @author cjflores
 * @version 1.0.0
 * 
 * @param <DTO>
 * @param <PK>
 * @param <Mapping>
 */
public abstract class GenericServiceImpl<Mapping, DTO, PK extends Serializable>
		implements GenericService<Mapping, DTO, PK> {

	@SuppressWarnings("rawtypes")
	abstract GenericDAO getDAO();

	@SuppressWarnings("rawtypes")
	abstract GenericAssembler getAssembler();

	@SuppressWarnings("unchecked")
	@Transactional
	public PK save(DTO object) throws ServiceException {
		try {
			Mapping mapping = (Mapping) getAssembler().getMappingTransform(
					object);
			return (PK) getDAO().save(mapping);
		} catch (PersistenceException ex) {
			System.out.println("Error en GenericServiceImpl - save: " + ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(DTO object) throws ServiceException {
		try {
			Mapping mapping = (Mapping) getAssembler().getMappingTransform(
					object);
			getDAO().update(mapping);
		} catch (PersistenceException ex) {
			System.out.println("Error en GenericServiceImpl - update: " + ex);
		}
	}
}
