/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.facade;

import java.util.List;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.entities.Cata;

/**
 * INTERFACE FACADE CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface CataFacade extends GenericFacade<Cata, CataDTO, Integer> {
	List<CataDTO> findByIdEsta(Integer idEsta) throws Exception;
}
