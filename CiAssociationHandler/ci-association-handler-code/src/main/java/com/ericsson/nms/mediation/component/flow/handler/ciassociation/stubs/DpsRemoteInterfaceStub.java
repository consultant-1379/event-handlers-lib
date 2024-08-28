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
package com.ericsson.nms.mediation.component.flow.handler.ciassociation.stubs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Temporary stub of the DPS Remote Interface
 * 
 */
public class DpsRemoteInterfaceStub {

	private static Logger log = LoggerFactory
			.getLogger(DpsRemoteInterfaceStub.class);

	public String getParentFdn(final String childFdn) {
		log.info(
				"getParentFdn: called with childFdn [{}], return [Parent/FDN]",
				childFdn);
		return "Parent/FDN";
	}

	public long getEntityAddressInfo(final String moFdn) {
		log.info("getEntityAddressInfo: called with moFdn [{}], return [1234]",
				moFdn);
		return 1234l;
	}

	public boolean addAssociation(final long id, final String fdn,
			final String associationName) {
		log.info(
				"addAssociation: called with id [{}], fdn [{}], associationName [{}], return [true]",
				id, fdn, associationName);
		return true;
	}
}
