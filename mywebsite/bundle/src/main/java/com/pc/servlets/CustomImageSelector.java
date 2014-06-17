package com.pc.servlets;

import com.day.image.Font;
import com.day.image.Layer;
import com.day.cq.wcm.commons.AbstractImageServlet;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.osgi.framework.Constants;
import javax.jcr.RepositoryException;
import java.awt.Color;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({
		@Property(name = Constants.SERVICE_DESCRIPTION, value = "Custom Image Selector"),
		@Property(name = Constants.SERVICE_VENDOR, value = "pc"),
		@Property(name = "sling.servlet.resourceTypes", value = {
				"/content/dam/bandn/home/Koala",
				"foundation/components/parbase", "cq:Page",
				"mywebsite/components/custom-rich-content" }),
		@Property(name = "sling.servlet.selectors", value = {
				"invokeSelector1", "invokeSelector2" }),
		@Property(name = "sling.servlet.extensions", value = { "jpg", "jpeg",
				"png", "gif", "bmp" }) })
public class CustomImageSelector extends AbstractImageServlet {
	private static final long serialVersionUID = -6549518176129073295L;
	@Override
	protected Layer createLayer(ImageContext ctx) throws RepositoryException {
		// this will returns the custom image layer object to the incoming
		// request
		// A sample implementation of a custom image selector can be found at
		// /libs/foundation/components/page/navimage_png.java in CQ5
		//
		String selectors = ctx.request.getRequestPathInfo().getSelectorString();
		Layer layer = null;
		if (selectors.contains("invokeSelector1")) {
			Font font = new Font("Arial", 12);
			layer = new Layer(500, 600, Color.WHITE);
			layer.drawText(10, 10, 500, 600, "wtih in invokeSelector1 ", font,
					Font.ALIGN_LEFT, 0, 0);
		} else if (selectors.contains("invokeSelector2")) {
			Font font = new Font("Arial", 20);
			layer = new Layer(500, 600, Color.WHITE);
			layer.drawText(10, 10, 500, 600, "wtih in invokeSelector2 ", font,
					Font.ALIGN_LEFT, 0, 0);
		} else {
			Font font = new Font("Arial", 30);
			layer = new Layer(500, 600, Color.WHITE);
			layer.drawText(10, 10, 500, 600, "wtih in invokeSelector3 ", font,
					Font.ALIGN_LEFT, 0, 0);
		}
		return layer;
	}
}