/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datakeeper.dto.CataDTO;
import com.datakeeper.facade.CataFacade;
import com.datakeeper.utils.MsgConstant;
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

	private String input_new_codi;
	private String input_new_nomb;
	private String input_new_valo;

	@PostConstruct
	public void postConstruct() {
		initVar();
		initObj();
		initList();
	}

	private void initVar() {
		input_new_codi = null;
		input_new_nomb = null;
		input_new_valo = null;
	}

	private void initList() {
		itemsList = null;
		initItemsList();
	}

	private void initObj() {
		currentItem = null;
	}

	private void initItemsList() {
		try {
			itemsList = facade.findByIdEsta(VarConstant.CATA_ID_CATA_ACTIVO);
		} catch (Exception e) {
			System.out.println("Error en CataController - initItemsList: " + e);
			itemsList = null;
		}
	}

	public void refreshAll() {
		initVar();
		initObj();
		initList();
	}

	public void onLoadNew() {
		if (currentItem != null) {
			input_new_codi = currentItem.getCodi();
		}
	}

	public void onSave() {
		CataDTO save = new CataDTO();
		save.setCodi(input_new_codi.toUpperCase());
		save.setNomb(input_new_nomb);
		save.setValo(input_new_valo);
		save.setIdEsta(VarConstant.CATA_ID_CATA_ACTIVO);
		save.setFchCrea(now());
		if (!validExist(save)) {
			try {
				facade.save(save);
				super.addInfoMessage(MsgConstant.MSG_SAVE_SUCCESS);
			} catch (Exception e) {
				super.addErrorMessage(MsgConstant.MSG_SAVE_FAIL);
				System.out.println("Error en CataController - onSave: " + e);
			} finally {
				refreshAll();
				RequestContext.getCurrentInstance().execute(
						"PF('dlgNuevo').hide();");
			}
		} else {
			super.addWarningMessage(MsgConstant.MSG_RECORD_EXIST);
			RequestContext.getCurrentInstance().execute(
					"PF('dlgNuevo').show();");
		}
	}

	private boolean validExist(CataDTO dto) {
		boolean r = false;
		try {
			r = facade.findByObject(dto);
		} catch (Exception e) {
			System.out.println("Error en CataController - validExist " + e);
		}
		return r;
	}

	public void onCancel() {
		refreshAll();
		super.addInfoMessage(MsgConstant.MSG_CANCEL_ACTION);
	}

	public void onValidateSelectedRow() {
		if (currentItem == null) {
			super.addWarningMessage(MsgConstant.MSG_NOT_SELECTED);
		}
	}
}
