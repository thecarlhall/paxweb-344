/**
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ops4j.paxweb344.bundle2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.ops4j.pax.web.service.WebContainer;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Component
//@Service
//@Property(name = ExtenderConstants.PROPERTY_ALIAS, value = "/bundle2")
public class Bundle2SharedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(Bundle2SharedServlet.class);

    @Reference
    private WebContainer webContainer;

    @Activate
    protected void activate(BundleContext bndContext) throws ServletException {
        webContainer.registerServlet(this, new String[] { "/bundle2" }, null,
                webContainer.getDefaultSharedHttpContext());
        LOGGER.info("Registered Bundle2Servlet to default shared http context");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Welcome to Bundle2SharedServlet");
        LOGGER.info("doGet({}, {})", new Object[] { req, resp });
    }

}
