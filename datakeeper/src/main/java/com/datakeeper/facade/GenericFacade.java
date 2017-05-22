/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.facade;

import java.io.Serializable;

/**
 * INTERFACE FACADE GENERICO
 * 
 * @author cjflores
 * @version 1.0.0
 * @since
 *
 * @param <DTO>
 * @param <PK>
 * @param <Mapping>
 */
public interface GenericFacade<Mapping, DTO, PK extends Serializable> {

	PK save(DTO object) throws Exception;

	void update(DTO object) throws Exception;

}