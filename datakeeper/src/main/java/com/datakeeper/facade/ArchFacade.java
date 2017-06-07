/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.facade;

import java.util.List;

import com.datakeeper.dto.ArchDTO;
import com.datakeeper.entities.Arch;

/**
 * INTERFACE FACADE ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface ArchFacade extends GenericFacade<Arch, ArchDTO, Integer> {
	List<ArchDTO> findByIdEsta(Integer idEsta) throws Exception;

	Integer counRecords() throws Exception;
}
