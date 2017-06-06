/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.entities.Cata;
import com.datakeeper.persistence.dao.CataDAO;
import com.datakeeper.persistence.dao.GenericDAO;
import com.datakeeper.service.CataService;
import com.datakeeper.service.assembler.CataAssembler;
import com.datakeeper.service.assembler.GenericAssembler;

/**
 * IMPLEMENTACIÓN DE LA INTERFACE SERVICE CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since
 *
 */
@Service
public class CataServiceImpl extends GenericServiceImpl<Cata, CataDTO, Integer>
		implements CataService {

	@Autowired
	private CataDAO dao;
	@Autowired
	private CataAssembler assembler;

	@Override
	public List<CataDTO> findAllByEsta(Integer idEsta) throws ServiceException {
		List<CataDTO> listDTO = null;
		try {
			List<Cata> listEntitie = dao.findAllByEsta(idEsta);
			listDTO = assembler.getDTOListTransform(listEntitie);
		} catch (PersistenceException ex) {
			System.out
					.println("Error en CataServiceImpl - findByIdEsta: " + ex);
		}
		return listDTO;
	}

	@Override
	public boolean checkIfObjectExist(CataDTO dto) throws ServiceException {
		try {
			return dao.checkIfObjectExist(assembler.getMappingTransform(dto));
		} catch (PersistenceException ex) {
			System.out
					.println("Error en CataServiceImpl - checkIfObjectExist: "
							+ ex);
			return false;
		}
	}

	@Override
	public CataDTO getObjectCata(CataDTO dto) throws ServiceException {
		try {
			Cata entitie = dao
					.getObjectCata(assembler.getMappingTransform(dto));
			return assembler.getDTOTransform(entitie);
		} catch (PersistenceException ex) {
			System.out.println("Error en CataServiceImpl - getObjectCata: "
					+ ex);
			return null;
		}
	}

	@Override
	public List<CataDTO> findByCodi(String codi) throws ServiceException {
		try {
			List<Cata> listEntitie = dao.findByCodi(codi);
			return assembler.getDTOListTransform(listEntitie);
		} catch (PersistenceException ex) {
			System.out.println("Error en CataServiceImpl - findByCodi: " + ex);
			return null;
		}
	}

	@Override
	public Integer counRecords() throws ServiceException {
		try {
			return dao.counRecords();
		} catch (PersistenceException ex) {
			System.out.println("Error en CataServiceImpl - counRecords: " + ex);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	GenericDAO getDAO() {
		return dao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	GenericAssembler getAssembler() {
		return assembler;
	}

}
