/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.nms.mediation.component.flow.handler.ciassociation;

import com.ericsson.nms.mediation.component.flow.handler.ciassociation.stubs.DpsRemoteInterfaceStub;
import com.ericsson.oss.itpf.common.config.Configuration;
import com.ericsson.oss.itpf.common.event.handler.AbstractEventHandler;
import com.ericsson.oss.itpf.common.event.handler.EventInputHandler;

public class CiAssociationHandler extends AbstractEventHandler implements
		EventInputHandler {

	private final String MO_FDN_ATTRIBUTE_KEY = "fdn";
	private final String ASSOCIATION_NAME = "EAI_TO_CI";
	private String ciFdm;

	// TODO: This is a mocked DPS Remote Interface, to be replaced by real one
	// when available
	private final DpsRemoteInterfaceStub dps = new DpsRemoteInterfaceStub();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ericsson.oss.itpf.common.event.handler.TypedEventInputHandler#onEvent
	 * (com.ericsson.oss.itpf.common.event.ComponentEvent)
	 */
	@Override
	public void onEvent(Object inputEvent) {
		String managedFunctionFdm = getMoParent(ciFdm);
		long eaiPoId = getEaiPoId(managedFunctionFdm);
		addAssociationToEai(eaiPoId, ciFdm, ASSOCIATION_NAME);
	}

	/**
	 * Get the FDM of the parent MO of the supplied FDM from the DPS using DPS
	 * remote interface
	 * 
	 * @param childFdm
	 *            - The FDM of the child MO
	 * @return The FDM of the child's parent MO
	 */
	private String getMoParent(String childFdm) {
		return dps.getParentFdn(childFdm);
	}

	/**
	 * Get the PO ID of the EntityAddressInformation associated with an MO from
	 * the DPS using DPS remote interface
	 * 
	 * @param moFdm
	 *            - The FDM of the MO
	 * @return - The PO ID of the EAI associated with the MO
	 */
	private long getEaiPoId(String moFdm) {
		return dps.getEntityAddressInfo(moFdm);
	}

	/**
	 * Add an association to an EAI using DPS remote interface
	 * 
	 * @param eaiPoId
	 *            - The PO ID of the EAI
	 * @param ciFdm
	 *            - The FDM of the ConnectivityInformation MO to add to the
	 *            association
	 * @param associationName
	 *            - the endpoint name of the association, as seen by the EAI
	 */
	private void addAssociationToEai(long eaiPoId, String ciFdm,
			String associationName) {
		dps.addAssociation(eaiPoId, ciFdm, associationName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ericsson.oss.itpf.common.event.handler.AbstractEventHandler#doInit()
	 */
	@Override
	protected void doInit() {
		Configuration config = getConfiguration();
		ciFdm = config.getStringProperty(MO_FDN_ATTRIBUTE_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ericsson.oss.itpf.common.Destroyable#destroy()
	 */
	@Override
	public void destroy() {
	}

}
