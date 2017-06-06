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

	@Override
	public boolean checkIfObjectExist(CataDTO dto) throws Exception {
		try {
			return service.checkIfObjectExist(dto);
		} catch (Exception e) {
			System.out.println("Error en CataFacadeImpl - checkIfObjectExist: "
					+ e);
			return false;
		}
	}

	@Override
	public CataDTO getObjectCata(CataDTO dto) throws Exception {
		try {
			return service.getObjectCata(dto);
		} catch (Exception e) {
			System.out.println("Error en CataFacadeImpl - getObjectCata: " + e);
			return null;
		}
	}

	@Override
	public List<CataDTO> getObjectCata(String codi) throws Exception {
		try {
			return service.findByCodi(codi);
		} catch (Exception e) {
			System.out.println("Error en CataFacadeImpl - getObjectCata: " + e);
			return null;
		}
	}

	@Override
	public Integer counRecords() throws Exception {
		try {
			return service.counRecords();
		} catch (Exception e) {
			System.out.println("Error en CataFacadeImpl - counRecords: " + e);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	GenericService getService() {
		return service;
	}

}
