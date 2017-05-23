/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service.assembler;

import org.springframework.stereotype.Component;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.entities.Cata;
import com.datakeeper.utils.VarConstant;

/**
 * ASSEMBLER CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 */
@Component
public class CataAssembler extends GenericAssembler<Cata, CataDTO> {

	@Override
	public CataDTO getDTOTransform(Cata mapping) {
		if (mapping == null) {
			return null;
		}
		CataDTO dto = new CataDTO();
		dto.setIdCata(mapping.getIdCata());
		dto.setCodi(mapping.getCodi());
		dto.setNomb(mapping.getNomb());
		dto.setValo(mapping.getValo());
		dto.setIdEsta(mapping.getIdCata());
		dto.setIdEstaNomb((mapping.getIdEsta().equals(
				VarConstant.CATA_ID_CATA_ACTIVO) ? VarConstant.CATA_NOMB_CATA_ACTIVO
				: VarConstant.CATA_NOMB_CATA_INACTIVO));
		dto.setFchCrea(mapping.getFchCrea());
		dto.setFchModi(mapping.getFchModi());
		return dto;
	}

	@Override
	public Cata getMappingTransform(CataDTO dto) {
		if (dto == null) {
			return null;
		}
		Cata mapping = new Cata();
		mapping.setIdCata(dto.getIdCata());
		mapping.setCodi(dto.getCodi());
		mapping.setNomb(dto.getNomb());
		if (dto.getValo() != "") {
			mapping.setValo(dto.getValo());
		}
		mapping.setIdEsta(dto.getIdEsta());
		mapping.setFchCrea(dto.getFchCrea());
		mapping.setFchModi(dto.getFchModi());
		return mapping;
	}

}
