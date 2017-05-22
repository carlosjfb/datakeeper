/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service;

import java.util.List;
import org.hibernate.service.spi.ServiceException;
import com.datakeeper.dto.CataDTO;
import com.datakeeper.entities.Cata;

/**
 * IMPLEMENTACÓN SERVICE CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface CataService extends GenericService<Cata, CataDTO, Integer> {
	List<CataDTO> findAllByEsta(Integer idEsta) throws ServiceException;
}
