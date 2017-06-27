/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

	private ArchDTO currentItem;

	private String view_mensajes;
	private StreamedContent view_file;

	private String input_edit_nomb;
	private String input_edit_descArch;
	private String input_edit_vers;

	@Autowired
	@PostConstruct
	public void postConstruct() {
		initVar();
		initObj();
		initList();
	}

	private void initVar() {
		view_mensajes = "";
	}

	private void initList() {
		itemsList = null;
		initItemsList();
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
			boolean saveMsg = false;
			int oSave = 0;
			String tipo = null;
			String nombre = e.getFile().getFileName();
			tipo = nombre.substring(nombre.lastIndexOf(".") + 1);
			nombre = nombre.substring(0, nombre.lastIndexOf("."));
			if (!checkIfExistName(nombre)) {
				NumberFormat nf = new DecimalFormat("#0.0");
				Double fileB = (double) e.getFile().getSize();
				Double fileKB = fileB / 1024;
				Double fileMB = fileKB / 1024;
				Double fileGB = fileMB / 1024;
				CataDTO cata = findByTipoArch(tipo.toLowerCase());
				if (cata != null) {
					ArchDTO arch = new ArchDTO();
					arch.setNomb(nombre);
					arch.setTama((fileGB > 1 ? nf.format(fileGB) + " GB"
							: fileMB > 1 ? nf.format(fileMB) + " MB"
									: fileKB > 1 ? nf.format(fileKB) + " KB"
											: nf.format(fileB) + " Bytes"));
					arch.setIdTipo(cata.getIdCata());
					arch.setArchByte(e.getFile().getContents());
					saveArch(arch);
				} else {
					saveMsg = true;
					oSave = 1;
				}
			} else {
				saveMsg = true;
				oSave = 2;
			}
			if (saveMsg) {
				switch (oSave) {
				case 1:
					view_mensajes += "\nExtencion: '" + tipo
							+ "' no registrada!";
					break;
				case 2:
					view_mensajes += "\nNombre: '" + nombre + "' registrado!";
					break;
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

	private boolean checkIfExistName(String nomb) {
		boolean exist = false;
		try {
			exist = facade.checkIfExistName(nomb);
		} catch (Exception e) {
			System.out.println("Error en ArchController - checkIfExistName: "
					+ e);
			e.printStackTrace();
		}
		return exist;
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

	public void onSave() {
		refreshAll();
	}

	public void onViewAcept() {
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
