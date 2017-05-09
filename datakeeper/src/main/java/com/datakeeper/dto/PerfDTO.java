/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * DTO PERFILES
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */

@Data
public class PerfDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2515941828524194759L;
	private Integer idPerf;
	private Integer idServ;
	private String idServNomb;
	private String idServValo;
	private String usua;
	private String cntr;
	private Integer idEsta;
	private String idEstaNomb;
	private Date fchCrea;
	private Date fchModi;

}
