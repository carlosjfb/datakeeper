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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * PERFILES
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */

@Data
@Entity
@Table(schema = "data", name = "perf")
public class Perf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2546127894624276721L;

	@Id
	@SequenceGenerator(name = "SEQ_PERF", sequenceName = "data.perf_id_perf_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERF")
	@Column(name = "id_perf")
	private Integer idPerf;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_serv") })
	private Cata idServ;
	@Column(name = "corr")
	private String corr;
	@Column(name = "usua")
	private String usua;
	@Column(name = "tele")
	private String tele;
	@Column(name = "cntr")
	private String cntr;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_esta") })
	private Cata idEsta;
	@Column(name = "fch_crea")
	private Date fchCrea;
	@Column(name = "fch_modi")
	private Date fchModi;

}
