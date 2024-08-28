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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.ericsson.nms.mediation.component.flow.handler.eaicreation.EaiCreationHandler;
import com.ericsson.oss.itpf.common.config.Configuration;
import com.ericsson.oss.itpf.common.event.handler.EventHandlerContext;

@RunWith(MockitoJUnitRunner.class)
public class EaiCreationHandlerTest {

	@Mock
	EventHandlerContext cxt;

	@Mock
	Configuration config;

	EaiCreationHandler eaiCreationHandler;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		eaiCreationHandler = new EaiCreationHandler();
		when(cxt.getEventHandlerConfiguration()).thenReturn(config);
		when(config.getStringProperty("fdn")).thenReturn("erbs/fdn");
	}

	@Test
	public void testHandlerRunsWithoutException() throws Exception {
		eaiCreationHandler.init(cxt);
		eaiCreationHandler.onEvent(null);
	}

	@Test
	public void testHandlerNullCxtException() throws Exception {
		try {
			eaiCreationHandler.init(null);
		} catch (final IllegalArgumentException e) {
			assertEquals("Context must not be null", e.getMessage());
		}
	}

	@Test
	public void testHandlerNullConfigException() throws Exception {
		try {
			when(cxt.getEventHandlerConfiguration()).thenReturn(null);
			eaiCreationHandler.init(cxt);
		} catch (final IllegalArgumentException e) {
			assertEquals("Configuration must not be null", e.getMessage());
		}
	}
}