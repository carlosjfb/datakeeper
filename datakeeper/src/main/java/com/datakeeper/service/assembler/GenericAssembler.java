/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

/**
 * GENERIC ASSEMBLER
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 * @param <M>
 * @param <D>
 */
public abstract class GenericAssembler<M, D> {

	public abstract D getDTOTransform(M mapping);

	public abstract M getMappingTransform(D dto);

	public List<D> getDTOListTransform(List<M> mappingList) {
		List<D> listaDTO = null;
		if (!CollectionUtils.isEmpty(mappingList)) {
			listaDTO = new ArrayList<D>();
			for (M mapping : mappingList) {
				D entity = this.getDTOTransform(mapping);
				listaDTO.add(entity);
			}
		}
		return listaDTO;
	}

	public List<M> getMappingListTransform(List<D> dtoList) {
		List<M> listaMapping = null;
		if (!CollectionUtils.isEmpty(dtoList)) {
			listaMapping = new ArrayList<M>();
			for (D dto : dtoList) {
				M mapping = this.getMappingTransform(dto);
				listaMapping.add(mapping);
			}
		}
		return listaMapping;
	}

	public Set<D> getDTOSetTransform(Set<M> mappingSet) {
		Set<D> setDTO = null;
		if (!CollectionUtils.isEmpty(mappingSet)) {
			setDTO = new HashSet<D>();
			for (M mapping : mappingSet) {
				D dto = this.getDTOTransform(mapping);
				setDTO.add(dto);
			}
		}
		return setDTO;
	}

	public Set<M> getMappingSetTransform(Set<D> dtoSet) {
		Set<M> setMapping = null;
		if (!CollectionUtils.isEmpty(dtoSet)) {
			setMapping = new HashSet<M>();
			for (D dto : dtoSet) {
				M mapping = this.getMappingTransform(dto);
				setMapping.add(mapping);
			}
		}
		return setMapping;
	}

}
