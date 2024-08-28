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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ericsson.nms.mediation.component.flow.handler.ciassociation.CiAssociationHandler;
import com.ericsson.oss.itpf.common.config.Configuration;
import com.ericsson.oss.itpf.common.event.handler.EventHandlerContext;

public class CiAssociationHandlerTest {

	@Mock
	EventHandlerContext ctx;

	@Mock
	Configuration config;

	CiAssociationHandler ciAssociationHandler;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ciAssociationHandler = new CiAssociationHandler();
		when(ctx.getEventHandlerConfiguration()).thenReturn(config);
		when(config.getStringProperty("fdn")).thenReturn("erbs/fdn");
	}

	@Test
	public void testHandlerRuns() throws Exception {
		ciAssociationHandler.init(ctx);
		ciAssociationHandler.onEvent(null);
	}

	@Test
	public void testHandlerNullCxtException() throws Exception {
		try {
			ciAssociationHandler.init(null);
		} catch (final IllegalArgumentException e) {
			assertEquals("Context must not be null", e.getMessage());
		}
	}

	@Test
	public void testHandlerNullConfigException() throws Exception {
		try {
			when(ctx.getEventHandlerConfiguration()).thenReturn(null);
			ciAssociationHandler.init(ctx);
		} catch (final IllegalArgumentException e) {
			assertEquals("Configuration must not be null", e.getMessage());
		}
	}
}