/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * DTO CATÁLOGO PRINCIPAL
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */

@Data
public class CataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2577449867705032561L;
	private Integer idCata;
	private String codi;
	private String nomb;
	private String valo;
	private Integer idEsta;
	private String idEstaNomb;
	private Date fchCrea;
	private Date fchModi;

}
