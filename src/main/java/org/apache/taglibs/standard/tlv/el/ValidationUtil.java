/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.taglibs.standard.tlv.el;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluator;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 */
public class ValidationUtil {
    static String validateExpression(
            String elem, String att, String expr) {

        // let's just use the cache kept by the ExpressionEvaluatorManager
        ExpressionEvaluator current;
        try {
            current =
                    ExpressionEvaluatorManager.getEvaluatorByName(
                            ExpressionEvaluatorManager.EVALUATOR_CLASS);
        } catch (JspException ex) {
            // (using JspException here feels ugly, but it's what EEM uses)
            return ex.getMessage();
        }

        String response = current.validate(att, expr);
        if (response == null) {
            return response;
        } else {
            return "tag = '" + elem + "' / attribute = '" + att + "': "
                    + response;
        }
    }
}
