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
package com.ericsson.nms.mediation.component.flow.handler.eaicreation.stubs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Temporary stub of the DPS Remote Interface
 * 
 */
public class DpsRemoteInterfaceStub {

	private static Logger log = LoggerFactory
			.getLogger(DpsRemoteInterfaceStub.class);

	public long createPo(final String namespace, final String type,
			final String version) {
		final long id = 1234l;
		log.info(
				"createPo: called with namespace [{}], type [{}], version [{}], return id [{}]",
				namespace, type, version, id);
		return id;
	}

	public boolean setEntityAddressInfo(final String fdn, final long id) {
		log.info(
				"setEntityAddressInfo: called with fdn [{}], id [{}], return [true]",
				fdn, id);
		return true;
	}
}
