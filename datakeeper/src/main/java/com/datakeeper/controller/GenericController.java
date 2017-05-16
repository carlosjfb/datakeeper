package com.datakeeper.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class GenericController {

	private ResourceBundle rb;

	public GenericController() {
		rb = ResourceBundle.getBundle("messages_es");
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected void addInfoMessage(String messageID) {
		addMessage(FacesMessage.SEVERITY_INFO, messageID);
	}

	protected void addWarningMessage(String messageID) {
		addMessage(FacesMessage.SEVERITY_WARN, messageID);
	}

	protected void addErrorMessage(String messageID) {
		addMessage(FacesMessage.SEVERITY_ERROR, messageID);
	}

	private void addMessage(Severity severidad, String messageID) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(severidad);
		String summary_ = getBundleMessage(messageID);
		message.setSummary(summary_);
		getFacesContext().addMessage(null, message);
	}

	protected String getMessage(String messageID) {
		return getMessage(messageID, null);
	}

	public String getMessage(String messageId, Object[] params) {

		String text;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			text = rb.getString(messageId);

			if (params != null) {
				MessageFormat mf = new MessageFormat(text, context
						.getViewRoot().getLocale());
				text = mf.format(params, new StringBuffer(), null).toString();
			}

		} catch (MissingResourceException e) {
			text = "?? key " + messageId + " not found ??";
		}
		return text;
	}

	private String getBundleMessage(String messageID) {
		try {
			return rb.getString(messageID);
		} catch (MissingResourceException e) {
			return "text = ?? key " + messageID + " not found ??";
		}
	}

	protected Date now() {
		return new Date();
	}

	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}

}
