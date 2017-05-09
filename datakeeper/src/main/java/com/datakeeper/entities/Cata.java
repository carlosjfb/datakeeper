/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * CATÁLOGO PRINCIPAL
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */

@Data
@Entity
@Table(schema = "data", name = "cata")
public class Cata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8370227337308953407L;

	public Cata() {
		super();
	}

	@Id
	@SequenceGenerator(name = "SEQ_CATA", sequenceName = "data.cata_id_cata_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATA")
	@Column(name = "id_cata")
	private Integer idCata;
	@Column(name = "codi")
	private String codi;
	@Column(name = "nomb")
	private String nomb;
	@Column(name = "valo")
	private String valo;
	@Column(name = "id_esta")
	private Integer idEsta;
	@Column(name = "fch_crea")
	private Date fchCrea;
	@Column(name = "fch_modi")
	private Date fchModi;

}
