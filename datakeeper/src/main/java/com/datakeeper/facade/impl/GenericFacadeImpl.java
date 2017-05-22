/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.facade.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.datakeeper.facade.GenericFacade;
import com.datakeeper.service.GenericService;

/**
 * IMPLEMENTACION DE LA INTERFACE FACADE GENERICO
 * 
 * @author cjflores
 * @version 1.0.0
 * 
 * @param <DTO>
 * @param <PK>
 * @param <Mapping>
 */
public abstract class GenericFacadeImpl<Mapping, DTO, PK extends Serializable>
		implements GenericFacade<Mapping, DTO, PK> {

	@SuppressWarnings("rawtypes")
	abstract GenericService getService();

	@SuppressWarnings("unchecked")
	@Transactional
	public PK save(DTO object) throws Exception {
		try {
			return (PK) getService().save(object);
		} catch (Exception e) {
			System.out.println("Error en GenericFacadeImpl - save: " + e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(DTO object) throws Exception {
		try {
			getService().update(object);
		} catch (Exception e) {
			System.out.println("Error en GenericFacadeImpl - update: " + e);
		}
	}
}
