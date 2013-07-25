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

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.lineIterator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.LineIterator;

import com.google.inject.Provides;
import com.mgmtp.perfload.core.client.web.config.AbstractWebLtModule;
import com.mgmtp.perfload.core.client.web.config.WebLtModule;
import com.mgmtp.perfload.core.common.util.PropertiesMap;

/**
 * @author rnaegele
 */
public class DemoDriverModule extends AbstractWebLtModule {

	public DemoDriverModule(final PropertiesMap testplanProperties) {
		super(testplanProperties);
	}

	@Override
	protected void doConfigureWebModule() {
		bindRequestFlowEventListener().to(DemoClientListener.class);
		install(new WebLtModule(testplanProperties));
	}

	/**
	 * Reads test data from a file and provides it for dependency injection.
	 * 
	 * @return The test data stored in a list.
	 */
	@TestData
	@Provides
	List<String> provideTestData() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream is = cl.getResourceAsStream("testdata.txt");
		try {
			List<String> result = new ArrayList<>(20);
			for (LineIterator it = lineIterator(is, "UTF-8"); it.hasNext();) {
				String line = it.nextLine();
				if (line.startsWith("#")) {
					continue;
				}
				result.add(line);
			}
			return result;
		} catch (IOException ex) {
			throw new IllegalStateException("Error reading test data.", ex);
		} finally {
			closeQuietly(is);
		}
	}
}
