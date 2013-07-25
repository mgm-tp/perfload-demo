/*
 * Copyright (c) 2013 mgm technology partners GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mgmtp.perfload.demo.driver;

import java.io.IOException;
import java.io.Serializable;

import org.jboss.netty.channel.ChannelFuture;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.mgmtp.perfload.core.client.config.ModulesLoader;
import com.mgmtp.perfload.core.client.config.annotations.Operation;
import com.mgmtp.perfload.core.client.config.annotations.Target;
import com.mgmtp.perfload.core.client.runner.ErrorHandler;
import com.mgmtp.perfload.core.client.runner.LtRunner;
import com.mgmtp.perfload.core.client.web.config.AbstractWebLtModule;
import com.mgmtp.perfload.core.clientserver.client.Client;
import com.mgmtp.perfload.core.clientserver.client.ClientMessageListener;
import com.mgmtp.perfload.core.common.util.AbortionException;
import com.mgmtp.perfload.core.common.util.PropertiesMap;

/**
 * @author rnaegele
 */
public class DemoDriverTest {

	@Test
	public void testDemo() throws Exception {
		runTest("browse");
		runTest("cpuload");
		runTest("garbage");
	}

	private void runTest(final String operation) throws Exception {
		PropertiesMap props = new PropertiesMap();
		props.put("wtm.strategy.constant.waitingTimeMillis", "0");
		props.put("wtm.beforeTestStartMillis", "0");
		props.put("target.appserver.host", "http://localhost:8085/perfload-ref-app/rest");
		props.put("responseParser.allowedStatusCodes", "200,204,500");
		props.put("responseParser.forbiddenStatusCodes", "404,408");

		ModulesLoader ml = new ModulesLoader(new TestModule(props, operation), props, new MockClient(), 0, 0);
		Injector inj = ml.createInjector();

		LtRunner runner = inj.getInstance(LtRunner.class);
		runner.execute();
	}

	public static class TestModule extends AbstractWebLtModule {
		private final DemoDriverModule module;
		private final String operation;

		public TestModule(final PropertiesMap testplanProperties, final String operation) {
			super(testplanProperties);
			this.operation = operation;
			module = new DemoDriverModule(testplanProperties);
		}

		@Override
		protected void doConfigureWebModule() {
			bindConstant().annotatedWith(Operation.class).to(operation);
			bindConstant().annotatedWith(Target.class).to("appserver");
			install(Modules.override(module).with(new AbstractModule() {
				@Override
				protected void configure() {
					bind(ErrorHandler.class).toInstance(new ErrorHandler() {
						@Override
						public void execute(final Throwable th) throws AbortionException {
							// re-throw, so test fails on any error
							Throwables.propagate(th);
						}
					});
				}
			}));
		}

		@Override
		public PropertiesMap getProperties() throws IOException {
			return module.getProperties();
		}
	}

	static class MockClient implements Client {

		@Override
		public String getClientId() {
			return "client";
		}

		@Override
		public void connect() {
			//
		}

		@Override
		public boolean isConnected() {
			return true;
		}

		@Override
		public void disconnect() {
			//
		}

		@Override
		public ChannelFuture sendMessage(final Serializable object) {
			return null;
		}

		@Override
		public void addClientMessageListener(final ClientMessageListener listener) {
			//
		}

		@Override
		public void removeClientMessageListener(final ClientMessageListener listener) {
			//
		}
	}
}
