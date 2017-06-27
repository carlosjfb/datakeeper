/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datakeeper.dto.ArchDTO;
import com.datakeeper.entities.Arch;
import com.datakeeper.persistence.dao.ArchDAO;
import com.datakeeper.persistence.dao.GenericDAO;
import com.datakeeper.service.ArchService;
import com.datakeeper.service.assembler.ArchAssembler;
import com.datakeeper.service.assembler.GenericAssembler;

/**
 * IMPLEMENTACIÓN DE LA INTERFACE SERVICE ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since
 *
 */
@Service
public class ArchServiceImpl extends GenericServiceImpl<Arch, ArchDTO, Integer>
		implements ArchService {

	@Autowired
	private ArchDAO dao;
	@Autowired
	private ArchAssembler assembler;

	@Override
	public List<ArchDTO> findAllByEsta(Integer idEsta) throws ServiceException {
		List<ArchDTO> listDTO = null;
		try {
			List<Arch> listEntitie = dao.findAllByEsta(idEsta);
			listDTO = assembler.getDTOListTransform(listEntitie);
		} catch (PersistenceException ex) {
			System.out
					.println("Error en ArchServiceImpl - findByIdEsta: " + ex);
		}
		return listDTO;
	}

	@Override
	public Integer counRecords() throws ServiceException {
		try {
			return dao.counRecords();
		} catch (PersistenceException ex) {
			System.out.println("Error en ArchServiceImpl - counRecords: " + ex);
			return null;
		}
	}

	@Override
	public boolean checkIfExistName(String nomb) throws ServiceException {
		try {
			return dao.checkIfExistName(nomb);
		} catch (PersistenceException ex) {
			System.out.println("Error en ArchServiceImpl - checkIfExistName: "
					+ ex);
			return false;
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
