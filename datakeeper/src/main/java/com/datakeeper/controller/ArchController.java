/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datakeeper.dto.ArchDTO;
import com.datakeeper.dto.CataDTO;
import com.datakeeper.facade.ArchFacade;
import com.datakeeper.facade.CataFacade;
import com.datakeeper.utils.MsgConstant;
import com.datakeeper.utils.VarConstant;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CONTROLLER DE ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 * 
 */

@Data
@ManagedBean(name = "archController", eager = true)
@SessionScoped
@EqualsAndHashCode(callSuper = false)
@Component
public class ArchController extends GenericController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2658355738583170873L;
	@Autowired
	private ArchFacade facade;
	@Autowired
	private CataFacade facadeCata;

	private List<ArchDTO> itemsList;
	private HashSet<String> listExtencions;

	private ArchDTO currentItem;

	private String view_extension_fail;
	private StreamedContent view_file;

	@Autowired
	@PostConstruct
	public void postConstruct() {
		initVar();
		initObj();
		initList();
	}

	private void initVar() {
		view_extension_fail = null;
	}

	private void initList() {
		itemsList = null;
		initItemsList();
		listExtencions = new HashSet<String>();
	}

	private void initObj() {
		currentItem = null;
		view_file = null;
	}

	private void initItemsList() {
		try {
			itemsList = facade.findByIdEsta(VarConstant.CATA_ID_CATA_ACTIVO);
		} catch (Exception e) {
			System.out.println("Error en ArchController - initItemsList: " + e);
			itemsList = null;
		}
	}

	public void refreshAll() {
		initVar();
		initObj();
		initList();
	}

	public void onLoadFiles(FileUploadEvent e) {
		if (e.getFile() != null) {
			String nombre = e.getFile().getFileName();
			NumberFormat nf = new DecimalFormat("#0.0");
			Double fileB = (double) e.getFile().getSize();
			Double fileKB = fileB / 1024;
			Double fileMB = fileKB / 1024;
			Double fileGB = fileMB / 1024;
			String tipo = nombre.substring(nombre.lastIndexOf(".") + 1);
			CataDTO cata = findByTipoArch(tipo.toLowerCase());
			if (cata != null) {
				ArchDTO arch = new ArchDTO();
				nombre = nombre.substring(0, nombre.lastIndexOf("."));
				arch.setNomb(nombre);
				arch.setTama((fileGB > 1 ? nf.format(fileGB) + " GB"
						: fileMB > 1 ? nf.format(fileMB) + " MB"
								: fileKB > 1 ? nf.format(fileKB) + " KB" : nf
										.format(fileB) + " Bytes"));
				arch.setIdTipo(cata.getIdCata());
				arch.setArchByte(e.getFile().getContents());
				saveArch(arch);
			} else {
				listExtencions.add(e.getFile().getFileName());
				view_extension_fail = null;
				for (String val : listExtencions) {
					if (view_extension_fail == null) {
						view_extension_fail = val;
						continue;
					}
					view_extension_fail += "\n" + val;
				}
			}
		}
	}

	private CataDTO findByTipoArch(String nomb) {
		CataDTO dto = new CataDTO();
		try {
			dto.setCodi(VarConstant.CATA_COD_MIME_TYPE);
			dto.setNomb(nomb);
			dto = facadeCata.getObjectCata(dto);
		} catch (Exception e) {
			System.out
					.println("Error en ArchController - findByTipoArch: " + e);
			e.printStackTrace();
		}
		return dto;
	}

	private void saveArch(ArchDTO arch) {
		try {
			arch.setIdEsta(VarConstant.CATA_ID_CATA_ACTIVO);
			arch.setFchCrea(now());
			facade.save(arch);
			super.addInfoMessage(MsgConstant.MSG_FILE_LOAD);
		} catch (Exception e) {
			super.addErrorMessage(MsgConstant.MSG_FILE_LOAD_FAIL);
			System.out.println("Error en ArchController - saverArch: " + e);
		}
	}

	public void onSaveLoad() {
		refreshAll();
	}

	public void onViewArch() {
		if (currentItem != null) {
			view_file = new DefaultStreamedContent(new ByteArrayInputStream(
					currentItem.getArchByte()));
		}
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

	public void onAssignUpdate() {
		if (currentItem != null) {
		} else {
			super.addWarningMessage(MsgConstant.MSG_NOT_SELECTED);
		}
	}

	public void onUpdate() {

	}

	public void onTemporalilyRemove() {
		try {
			currentItem.setIdEsta(VarConstant.CATA_ID_CATA_INACTIVO);
			currentItem.setFchModi(now());
			facade.update(currentItem);
			super.addInfoMessage(MsgConstant.MSG_DELETED_SUCESS);
		} catch (Exception e) {
			super.addErrorMessage(MsgConstant.MSG_DELETED_FAIL);
		}
		refreshAll();
	}

}
