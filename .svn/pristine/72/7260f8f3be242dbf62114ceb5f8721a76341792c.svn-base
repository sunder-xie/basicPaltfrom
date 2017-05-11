package com.kintiger.platform.framework.velocity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.views.velocity.VelocityManager;
import org.apache.velocity.tools.view.ToolboxManager;
import org.apache.velocity.tools.view.XMLToolboxManager;
import org.springframework.util.ResourceUtils;

public class VelocityManagerEx extends VelocityManager {

	private static final Log log = LogFactory.getLog(VelocityManagerEx.class);

	public VelocityManagerEx() {
		String toolboxLocation = "classpath\\:toolbox.xml";
		if (toolboxLocation != null) {
			XMLToolboxManager mgr = new XMLToolboxManager();
			try {
				File config = ResourceUtils.getFile(toolboxLocation);
				if (config != null) {
					InputStream in = new FileInputStream(config);
					try {
						mgr.load(in);
						toolboxManager = mgr;
					} finally {
						try {
							in.close();
						} catch (IOException e) {
							log.warn("failed to close file: " + toolboxLocation);
						}
					}
				}
			} catch (Exception ex) {
				log.error("failed to load configuration file: "
						+ toolboxLocation, ex);
			}
		}
	}

	protected void initToolbox(ServletContext context) {

	}

	public ToolboxManager getToolboxManager() {
		return toolboxManager;
	}

}
