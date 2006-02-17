// $Id$
/*
 * ====================================================================
 * Copyright (c) 2002-2004, Christophe Labouisse All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.ggtools.grand.ant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ggtools.grand.graph.Graph;
import net.ggtools.grand.graph.Node;
import net.ggtools.grand.graph.visit.LinkVisitor;

/**
 * A link representing a call by a task such like <code>ant</code>,
 * <code>runtarget</code>, etc. Instances of the class will have a mandatory
 * task name and may have some definited parameters.
 * 
 * @author Christophe Labouisse
 */
public class AntTaskLink extends AntLink {

    private final String taskName;

    private final Map parameterMap = new HashMap();
    
    private final List propertyFileList = new ArrayList();

    /**
     * @param name
     * @param graph
     * @param startNode
     * @param endNode
     */
    public AntTaskLink(final String name, final Graph graph, final Node startNode,
            final Node endNode, final String taskName) {
        super(name, graph, startNode, endNode);
        this.taskName = taskName;
        setAttributes(ATTR_WEAK_LINK);
    }

    /*
     * (non-Javadoc)
     * @see net.ggtools.grand.graph.Link#accept(net.ggtools.grand.graph.visit.LinkVisitor)
     */
    public void accept(LinkVisitor visitor) {
        visitor.visitLink(this);
    }

    /**
     * @return Returns the taskName.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets an attribute for the link.
     * @param key
     * @param value
     */
    public void setParameter(final String key, final String value) {
        parameterMap.put(key, value);
    }

    /**
     * Return the value of a parameter or <code>null</code> if not defined.
     * @param key
     * @return
     */
    public String getParameter(final String key) {
        return (String) parameterMap.get(key);
    }
    
    /**
     * Returns an array of property files set for this link.
     * @return
     */
    public String[] getPropertyFiles() {
        return (String[]) propertyFileList.toArray(new String[propertyFileList.size()]);
    }
    
    /**
     * Add a new property file to the current list.
     * @param fileName
     */
    public void addPropertyFile(final String fileName) {
        propertyFileList.add(fileName);
    }

    /**
     * Returns a readonly version of the parameter map.
     * 
     * @return a read only map of the parameters.
     */
    public Map getParameterMap() {
        return parameterMap;
    }
}