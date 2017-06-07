/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

/**
 * ARCHIVOS
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */

@Data
@Entity
@Table(schema = "data", name = "arch")
public class Arch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2616449659519672375L;

	public Arch() {
		super();
	}

	@Id
	@SequenceGenerator(name = "SEQ_ARCH", sequenceName = "data.arch_id_arch_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ARCH")
	@Column(name = "id_arch")
	private Integer idArch;
	@Column(name = "nomb")
	private String nomb;
	@Column(name = "desc_arch")
	private String descArch;
	@Column(name = "tama")
	private String tama;
	@Column(name = "vers")
	private String vers;
	@Column(name = "arch_byte")
	@Type(type = "org.hibernate.type.BinaryType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] archByte;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_tipo") })
	private Cata idTipo;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_esta") })
	private Cata idEsta;
	@Column(name = "fch_crea")
	private Date fchCrea;
	@Column(name = "fch_modi")
	private Date fchModi;

}
