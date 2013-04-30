package com.mgmtp.perfload.demo.driver;

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
		install(new WebLtModule(testplanProperties));
	}
}
