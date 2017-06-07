/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * DTO ARCHIVOS
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */

@Data
public class ArchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -686953968376443206L;
	private Integer idArch;
	private String nomb;
	private String descArch;
	private String tama;
	private String vers;
	private byte[] archByte;
	private Integer idTipo;
	private String idTipoNomb;
	private String idTipoValo;
	private Integer idEsta;
	private String idEstaNomb;
	private Date fchCrea;
	private Date fchModi;

}
