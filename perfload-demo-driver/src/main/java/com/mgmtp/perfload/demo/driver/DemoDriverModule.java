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

	@TestData
	@Provides
	List<String> provideTestData() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream is = cl.getResourceAsStream("testdata.txt");
		try {
			List<String> result = new ArrayList<String>(20);
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
