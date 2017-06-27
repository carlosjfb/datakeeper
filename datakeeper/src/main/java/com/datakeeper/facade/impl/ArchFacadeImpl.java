/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.facade.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datakeeper.dto.ArchDTO;
import com.datakeeper.entities.Arch;
import com.datakeeper.facade.ArchFacade;
import com.datakeeper.service.ArchService;
import com.datakeeper.service.GenericService;

/**
 * IMPLEMENTACION DE LA INTERFACE FACADE ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
@Component
@Transactional
public class ArchFacadeImpl extends GenericFacadeImpl<Arch, ArchDTO, Integer>
		implements ArchFacade {

	@Autowired
	private ArchService service;

	@Override
	public List<ArchDTO> findByIdEsta(Integer idEsta) throws Exception {
		try {
			return service.findAllByEsta(idEsta);
		} catch (Exception e) {
			System.out.println("Error en ArchFacadeImpl - findByIdEsta: " + e);
			return null;
		}
	}

	@Override
	public Integer counRecords() throws Exception {
		try {
			return service.counRecords();
		} catch (Exception e) {
			System.out.println("Error en ArchFacadeImpl - counRecords: " + e);
			return null;
		}
	}

	@Override
	public boolean checkIfExistName(String nomb) throws Exception {
		try {
			return service.checkIfExistName(nomb);
		} catch (Exception e) {
			System.out.println("Error en ArchFacadeImpl - checkIfExistName: "
					+ e);
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	GenericService getService() {
		return service;
	}

}
