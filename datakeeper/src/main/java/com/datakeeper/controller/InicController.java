/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CONTROLLER DE INICIO
 * 
 * @author cjflores
 * @version 1.0.0
 * 
 */

@Data
@ManagedBean(name = "inicController", eager = true)
@ViewScoped
@EqualsAndHashCode(callSuper = false)
@Component
public class InicController extends GenericController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 696422350309373471L;

	@PostConstruct
	public void postConstruct() {
	}
}
