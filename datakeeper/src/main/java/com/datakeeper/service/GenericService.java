/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service;

import java.io.Serializable;

import org.hibernate.service.spi.ServiceException;

/**
 * INTERFACE SERVICIO GENERICO
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 * @param <DTO>
 * @param <PK>
 * @param <Mapping>
 */
public interface GenericService<Mapping, DTO, PK extends Serializable> {

	PK save(DTO object) throws ServiceException;

	void update(DTO object) throws ServiceException;

}
