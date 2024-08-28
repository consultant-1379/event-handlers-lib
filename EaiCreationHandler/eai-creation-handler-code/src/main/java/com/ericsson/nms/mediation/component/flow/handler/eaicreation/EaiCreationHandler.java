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
package com.ericsson.nms.mediation.component.flow.handler.eaicreation;

import com.ericsson.nms.mediation.component.flow.handler.eaicreation.stubs.DpsRemoteInterfaceStub;
import com.ericsson.oss.itpf.common.config.Configuration;
import com.ericsson.oss.itpf.common.event.handler.AbstractEventHandler;
import com.ericsson.oss.itpf.common.event.handler.EventInputHandler;

public class EaiCreationHandler extends AbstractEventHandler implements
		EventInputHandler {

	private final String MO_FDN_ATTRIBUTE_KEY = "fdn";
	private final String EAI_NAMESPACE = "OSS_MED";
	private final String EAI_TYPE = "EAI";

	private String managedFunctionFdm;

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
	public void onEvent(final Object inputEvent) {
		final long eaiPoId = createEai(EAI_NAMESPACE, EAI_TYPE, null);
		associateMfWithEai(managedFunctionFdm, eaiPoId);
	}

	/**
	 * Create a new EntityAddressInformation PO in the DPS using DPS remote
	 * interface
	 * 
	 * @return the PO ID of the newly created EAI Persistence Object
	 */
	private long createEai(final String namespace, final String type,
			final String version) {
		final long poid = dps.createPo(namespace, type, version);
		return poid;
	}

	/**
	 * Defines the Entity Address Information to be set into the Managed
	 * Function using the DPS remote interface.
	 * 
	 * @param managedFunctionFdm
	 *            - The FDM of the Managed Function on which to set Entity
	 *            Address Information
	 * @param eaiPoId
	 *            - The PO ID of the Persistence Object containing the entity
	 *            address info to be set on this Managed Function
	 */
	private void associateMfWithEai(final String managedFunctionFdm,
			final long eaiPoId) {
		dps.setEntityAddressInfo(managedFunctionFdm, eaiPoId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ericsson.oss.itpf.common.event.handler.AbstractEventHandler#doInit()
	 */
	@Override
	protected void doInit() {
		final Configuration config = getEventHandlerContext()
				.getEventHandlerConfiguration();
		managedFunctionFdm = config.getStringProperty(MO_FDN_ATTRIBUTE_KEY);
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
