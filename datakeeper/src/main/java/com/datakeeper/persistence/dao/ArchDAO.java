/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.persistence.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import com.datakeeper.entities.Arch;

/**
 * INTERFACE DAO ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface ArchDAO extends GenericDAO<Arch, Integer> {
	List<Arch> findAllByEsta(Integer idEsta) throws PersistenceException;

	Integer counRecords() throws PersistenceException;
}
