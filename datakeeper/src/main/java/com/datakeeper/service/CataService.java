/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.entities.Cata;

/**
 * INTERFACE SERVICE CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface CataService extends GenericService<Cata, CataDTO, Integer> {
	List<CataDTO> findAllByEsta(Integer idEsta) throws ServiceException;

	boolean checkIfObjectExist(CataDTO obj) throws ServiceException;

	CataDTO getObjectCata(CataDTO obj) throws ServiceException;

	List<CataDTO> findByCodi(String codi) throws ServiceException;

	Integer counRecords() throws ServiceException;
}
