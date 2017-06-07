/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.service.assembler;

import org.springframework.stereotype.Component;

import com.datakeeper.dto.ArchDTO;
import com.datakeeper.entities.Arch;
import com.datakeeper.entities.Cata;

/**
 * ASSEMBLER ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 */
@Component
public class ArchAssembler extends GenericAssembler<Arch, ArchDTO> {

	@Override
	public ArchDTO getDTOTransform(Arch mapping) {
		if (mapping == null) {
			return null;
		}
		ArchDTO dto = new ArchDTO();
		dto.setIdArch(mapping.getIdArch());
		dto.setNomb(mapping.getNomb());
		dto.setDescArch(mapping.getDescArch());
		dto.setTama(mapping.getTama());
		dto.setVers(mapping.getVers());
		dto.setArchByte(mapping.getArchByte());
		dto.setIdTipo(mapping.getIdTipo().getIdCata());
		dto.setIdTipoNomb(mapping.getIdTipo().getNomb());
		dto.setIdTipoValo(mapping.getIdTipo().getValo());
		dto.setFchCrea(mapping.getFchCrea());
		dto.setFchModi(mapping.getFchModi());
		return dto;
	}

	@Override
	public Arch getMappingTransform(ArchDTO dto) {
		if (dto == null) {
			return null;
		}
		Arch mapping = new Arch();
		mapping.setIdArch(dto.getIdArch());
		mapping.setNomb(dto.getNomb());
		mapping.setDescArch(dto.getDescArch());
		mapping.setTama(dto.getTama());
		mapping.setVers(dto.getVers());
		mapping.setArchByte(dto.getArchByte());
		if (dto.getIdTipo() != null) {
			Cata cata = new Cata();
			cata.setIdCata(dto.getIdTipo());
			mapping.setIdTipo(cata);
		}
		if (dto.getIdEsta() != null) {
			Cata cata = new Cata();
			cata.setIdCata(dto.getIdEsta());
			mapping.setIdEsta(cata);
		}
		mapping.setFchCrea(dto.getFchCrea());
		mapping.setFchModi(dto.getFchModi());
		return mapping;
	}

}
