/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.facade.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.entities.Cata;
import com.datakeeper.facade.CataFacade;
import com.datakeeper.service.CataService;
import com.datakeeper.service.GenericService;

/**
 * IMPLEMENTACION DE LA INTERFACE FACADE CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
@Component
@Transactional
public class CataFacadeImpl extends GenericFacadeImpl<Cata, CataDTO, Integer>
		implements CataFacade {

	@Autowired
	private CataService service;

	@Override
	public List<CataDTO> findByIdEsta(Integer idEsta) throws Exception {
		try {
			return service.findAllByEsta(idEsta);
		} catch (Exception e) {
			System.out.println("Error en CataFacadeImpl - findByIdEsta: " + e);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	GenericService getService() {
		return service;
	}

}
