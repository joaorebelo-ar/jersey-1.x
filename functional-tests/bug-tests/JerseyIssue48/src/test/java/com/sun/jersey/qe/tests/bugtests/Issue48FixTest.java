/*
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2011 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at http://jersey.java.net/CDDL+GPL.html
 * or jersey/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at jersey/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.sun.jersey.qe.tests.bugtests;

import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly.GrizzlyTestContainerFactory;
import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author naresh
 */
public class Issue48FixTest extends JerseyTest {

    @Path("dummy")
    public static class DummyResource {

        @GET
        public String  getIt() {
            return "Take It!";
        }

    }

    @Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyTestContainerFactory();
    }

    public Issue48FixTest() {
        super(new LowLevelAppDescriptor.Builder("com.sun.jersey.qe.tests.bugtests").build());
    }

    @Test
     public void testIllegalArgumentException() {
        boolean caught = false;
        try {
            UriBuilder.fromPath(null);
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        assertTrue("IllegalArgumentException not thrown for UriBuilder.fromPath(null)",
                caught);

        caught = false;
        try {
            UriBuilder.fromUri((URI)null);
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        assertTrue("IllegalArgumentException not thrown for UriBuilder.fromUri((URI)null)",
                caught);

        caught = false;
        try {
            UriBuilder.fromUri((String)null);
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        assertTrue("IllegalArgumentException not thrown for UriBuilder.fromUri((String)null)",
                caught);

        caught = false;
        try {
            UriBuilder.fromPath("");
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        assertFalse("IllegalArgumentException thrown for UriBuilder.fromPath(\"\"), though \"\" is a valid path",
                caught);

    }

}