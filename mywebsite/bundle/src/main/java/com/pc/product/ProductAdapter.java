package com.pc.product;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.adapter.*;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.*;

import com.day.cq.wcm.api.Page;


@Component(metatype = true, immediate = true)
@Service
public class ProductAdapter implements AdapterFactory{
 
    private static final Logger LOG = LoggerFactory
            .getLogger(ProductAdapter.class);
 
    private static final Class PRODUCT_CLASS = Product.class;
    private static final Class PAGE_CLASS = Page.class;
 
    @Property(name = "adapters")
    protected static final String[] ADAPTER_CLASSES = { PRODUCT_CLASS.getName() };
 
    @Property(name = "adaptables")
    protected static final String[] ADAPTABLE_CLASSES = { PAGE_CLASS.getName() };
 
    
    public  Product getAdapter(Object adaptable,Class type) {
        if (adaptable instanceof Page) {
            return this.adaptFromPage((Page) adaptable, type);
        }
        return null;
    }
 
    private  Product adaptFromPage(Page page,
            Class type) {
        if ((page != null) && (page.getContentResource() != null)) {
            return adaptFromResource(page.getContentResource(), type);
        }
        return null;
    }
 
    @SuppressWarnings("unchecked")
    private  Product adaptFromResource(Resource resource,
            Class type) {
        Product product = new Product();
        try {
            Node productNode = resource.adaptTo(Node.class);
            product.setPath(productNode.getParent().getPath());
 
            // Set serial
            if (productNode.hasProperty(Product.PROP_SERIAL))
                product.setSerial(productNode.getProperty(Product.PROP_SERIAL)
                        .getString());
 
            // Set category
            if (productNode.hasProperty(Product.PROP_CATEGORY))
                product.setCategory(productNode.getProperty(
                        Product.PROP_CATEGORY).getString());
 
            // Set price
            if (productNode.hasProperty(Product.PROP_PRICE))
                product.setPrice(productNode
                        .getProperty(Product.PROP_PRICE).getDouble());
 
            // Set image url
            if (productNode.hasProperty(Product.PROP_IMG_URL))
                product.setImgURL(productNode.getProperty(
                        Product.PROP_IMG_URL).getString());
 
        } catch (RepositoryException repositoryException) {
            LOG.error("RepositoryException ------ > ", repositoryException);
        }
        return (Product) product;
    }
 
}