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

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;

import com.google.common.collect.ImmutableList;
import com.mgmtp.perfload.core.client.util.PlaceholderContainer;
import com.mgmtp.perfload.core.client.web.event.RequestFlowEvent;
import com.mgmtp.perfload.core.client.web.event.RequestFlowEventListener;

/**
 * @author rnaegele
 */
@Singleton
@Immutable
@ThreadSafe
public class DemoClientListener implements RequestFlowEventListener {

	private final Provider<PlaceholderContainer> placeholderContainerProvider;
	private final List<String> testDataEntries;
	private final Random random = new Random();

	@Inject
	public DemoClientListener(final Provider<PlaceholderContainer> placeholderContainerProvider,
			@TestData final List<String> testData) {
		this.placeholderContainerProvider = placeholderContainerProvider;
		this.testDataEntries = ImmutableList.copyOf(testData);
	}

	@Override
	public void beforeRequest(final RequestFlowEvent event) {
		int index = random.nextInt(testDataEntries.size());
		String entry = testDataEntries.get(index);
		placeholderContainerProvider.get().put("value", entry);
	}

	@Override
	public void beforeRequestFlow(final RequestFlowEvent event) { /* no-op */
	}

	@Override
	public void afterRequestFlow(final RequestFlowEvent event) { /* no-op */
	}

	@Override
	public void afterRequest(final RequestFlowEvent event) { /* no-op */
	}

}
