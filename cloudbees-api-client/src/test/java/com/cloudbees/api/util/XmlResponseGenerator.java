/*
 * Copyright 2010-2012, CloudBees Inc.
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

package com.cloudbees.api.util;

import com.cloudbees.api.*;
import com.thoughtworks.xstream.XStream;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Olivier Lamy
 */
public class XmlResponseGenerator {

    static XStream xStream = new XStream();
    static {
        xStream.processAnnotations(ApplicationListResponse.class);
        xStream.processAnnotations(ApplicationInfo.class);
        xStream.processAnnotations(ApplicationCheckSumsResponse.class);
        xStream.processAnnotations(ApplicationDeployArchiveResponse.class);
        xStream.processAnnotations(ApplicationJarHashesResponse.class);
    }

    public static String applicationListResponse() {
        ApplicationListResponse applicationListResponse = new ApplicationListResponse();
        applicationListResponse.getApplications().add(new ApplicationInfo("foo1", "nice application1", new Date(), "running", new String[]{"http://foo1.bar"}));
        applicationListResponse.getApplications().add(new ApplicationInfo("foo2", "nice application2", new Date(), "sucks", new String[]{"http://foo2.bar"}));
        return xStream.toXML(applicationListResponse);
    }

    public static String applicationCheckSumsResponse() {
        ApplicationCheckSumsResponse applicationCheckSumsResponse = new ApplicationCheckSumsResponse();
        applicationCheckSumsResponse.setCheckSums(new HashMap<String,Long>(0));
        return xStream.toXML(applicationCheckSumsResponse);
    }

    public static String applicationJarHashesResponse() {
        ApplicationJarHashesResponse applicationJarHashesResponse = new ApplicationJarHashesResponse();
        applicationJarHashesResponse.setJarHash(new HashMap<String,String>(0));
        return xStream.toXML(applicationJarHashesResponse);
    }

    public static String applicationDeployArchiveResponse() {
        ApplicationDeployArchiveResponse response = new ApplicationDeployArchiveResponse("id", "url");
        return xStream.toXML(response);
    }

}
