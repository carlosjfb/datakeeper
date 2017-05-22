/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.facade.CataFacade;
import com.datakeeper.utils.VarConstant;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CONTROLLER DE CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 */

@Data
@ManagedBean(name = "cataController", eager = true)
@ViewScoped
@EqualsAndHashCode(callSuper = false)
@Component
public class CataController extends GenericController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2658355738583170873L;

	@Autowired
	private CataFacade facade;

	private List<CataDTO> itemsList;
	private CataDTO currentItem;

	@PostConstruct
	public void postConstruct() {
		initVar();
		initList();
		initObj();
	}

	private void initVar() {

	}

	private void initList() {
		itemsList = null;
		initItemsList();
	}

	private void initObj() {

	}

	private void initItemsList() {
		try {
			itemsList = facade.findByIdEsta(VarConstant.CATA_ID_CATA_ACTIVO);
		} catch (Exception e) {
			System.out.println("Error en CataController - initItemsList: " + e);
			itemsList = null;
		}
	}
}
