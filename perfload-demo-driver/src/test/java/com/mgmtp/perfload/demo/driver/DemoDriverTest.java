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

import org.testng.annotations.Test;

import com.mgmtp.perfload.core.common.util.PropertiesMap;
import com.mgmtp.perfload.test.utils.DriverTestRunner;

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
		props.put("target.appserver.host", "http://localhost:8085/perfload-ref-app/rest");
		props.put("responseParser.allowedStatusCodes", "200,204,500");
		props.put("responseParser.forbiddenStatusCodes", "404,408");
		DriverTestRunner.runDriver(new DemoDriverModule(props), operation, "localhost", props);
	}
}
