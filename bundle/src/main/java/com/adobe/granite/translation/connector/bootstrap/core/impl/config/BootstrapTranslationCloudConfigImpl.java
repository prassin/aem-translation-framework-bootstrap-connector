/*
*************************************************************************
ADOBE SYSTEMS INCORPORATED
Copyright [first year code created] Adobe Systems Incorporated
All Rights Reserved.
 
NOTICE:  Adobe permits you to use, modify, and distribute this file in accordance with the
terms of the Adobe license agreement accompanying it.  If you have received this file from a
source other than Adobe, then your use, modification, or distribution of it requires the prior
written permission of Adobe.
*************************************************************************
 */

package com.adobe.granite.translation.connector.bootstrap.core.impl.config;

import com.adobe.granite.translation.api.TranslationException;
import com.adobe.granite.translation.connector.bootstrap.core.BootstrapTranslationCloudConfig;

import org.apache.jackrabbit.JcrConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapTranslationCloudConfigImpl implements BootstrapTranslationCloudConfig {
    private static final Logger log = LoggerFactory.getLogger(BootstrapTranslationCloudConfigImpl.class);

    private String dummyServerUrl;
    private String dummyConfigId;
    private String previewPath;

    public BootstrapTranslationCloudConfigImpl(Resource translationConfigResource) throws TranslationException {
        log.info("BootstrapTranslationCloudConfigImpl.");

        Resource configContent;
        if (JcrConstants.JCR_CONTENT.equals(translationConfigResource.getName())) {
            configContent = translationConfigResource;
        } else {
            configContent = translationConfigResource.getChild(JcrConstants.JCR_CONTENT);
        }

        if (configContent != null) {
            ValueMap properties = configContent.adaptTo(ValueMap.class);

            this.dummyServerUrl = properties.get(PROPERTY_DUMMY_SERVER_URL, "");
            this.dummyConfigId = properties.get(PROPERTY_DUMMY_CONFIG_ID, "");
            this.previewPath = properties.get(PROPERTY_PREVIEW_PATH, ""); 

            if (log.isDebugEnabled()) {
                log.debug("Created Bootstrap Cloud Config with the following:");
                log.debug("dummyServerUrl: {}", dummyServerUrl);
                log.debug("dummyConfigId: {}", dummyConfigId);
                log.debug("previewPath: {}", previewPath);
                
            }
        } else {
            throw new TranslationException("Error getting Cloud Config credentials",
                TranslationException.ErrorCode.MISSING_CREDENTIALS);
        }
    }

    public String getDummyServerUrl() {
        log.info("BootstrapTranslationCloudConfigImpl.getDummyServerUrl");
        return dummyServerUrl;
    }

    public String getDummyConfigId() {
        log.info("BootstrapTranslationCloudConfigImpl.getDummyConfigId");
        return dummyConfigId;
    }
    
    public String getPreviewPath(){
        log.info("BootstrapTranslationCloudConfigImpl.gePreviewPath");
        return previewPath;
    }
}