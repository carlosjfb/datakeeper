/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.datakeeper.dto.ArchDTO;
import com.datakeeper.entities.Arch;

/**
 * INTERFACE SERVICE ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface ArchService extends GenericService<Arch, ArchDTO, Integer> {
	List<ArchDTO> findAllByEsta(Integer idEsta) throws ServiceException;

	Integer counRecords() throws ServiceException;
}
