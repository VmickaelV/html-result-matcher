/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.vmickaelv.htmlresultmatcher;

import org.hamcrest.Matcher;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.xml.xpath.XPathExpressionException;

/**
 * Factory for assertions on the response content using CSS selectors.
 * <p>An instance of this class is typically accessed via
 * {@link CssSelectorResultMatchers#cssSelector}.
 *
 * @author Rossen Stoyanchev
 * @since 3.2
 */
public class CssResultMatchers {

    private final CssSelectorExpectationsHelper cssSelectorExpectationsHelper;

    /**
     * Protected constructor, not for direct instantiation. Use
     * {@link CssSelectorResultMatchers#cssSelector(String, Object...)}.
     *
     * @param expression the XPath expression
     * @param args       arguments to parameterize the XPath expression with using the
     *                   formatting specifiers defined in {@link String#format(String, Object...)}
     */
    protected CssResultMatchers(String expression, Object... args)
            throws XPathExpressionException {

        this.cssSelectorExpectationsHelper = new CssSelectorExpectationsHelper(expression, args);
    }

//    /**
//     * Evaluate the XPath and assert the {@link Node} content found with the
//     * given Hamcrest {@link Matcher}.
//     */
//    public ResultMatcher node(final Matcher<? super Node> matcher) {
//        return new ResultMatcher() {
//            @Override
//            public void match(MvcResult result) throws Exception {
//                MockHttpServletResponse response = result.getResponse();
//                cssSelectorExpectationsHelper.assertNode(response.getContentAsByteArray(), getDefinedEncoding(response), matcher);
//            }
//        };
//    }

    /**
     * Get the response encoding if explicitly defined in the response, {code null} otherwise.
     */
    private String getDefinedEncoding(MockHttpServletResponse response) {
        return response.isCharset() ? response.getCharacterEncoding() : null;
    }

    /**
     * Evaluate the XPath and assert that content exists.
     */
    public ResultMatcher exists() {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.exists(response.getContentAsByteArray(), getDefinedEncoding(response));
            }
        };
    }

    /**
     * Evaluate the XPath and assert that content doesn't exist.
     */
    public ResultMatcher doesNotExist() {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.doesNotExist(response.getContentAsByteArray(), getDefinedEncoding(response));
            }
        };
    }

    /**
     * Evaluate the XPath and assert the number of nodes found with the given
     * Hamcrest {@link Matcher}.
     */
    public ResultMatcher nodeCount(final Matcher<Integer> matcher) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertNodeCount(response.getContentAsByteArray(), getDefinedEncoding(response), matcher);
            }
        };
    }

    /**
     * Evaluate the XPath and assert the number of nodes found.
     */
    public ResultMatcher nodeCount(final int expectedCount) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertNodeCount(response.getContentAsByteArray(), getDefinedEncoding(response), expectedCount);
            }
        };
    }

    /**
     * Apply the XPath and assert the {@link String} value found with the given
     * Hamcrest {@link Matcher}.
     */
    public ResultMatcher string(final Matcher<? super String> matcher) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertString(response.getContentAsByteArray(), getDefinedEncoding(response), matcher);
            }
        };
    }

    /**
     * Apply the XPath and assert the {@link String} value found.
     */
    public ResultMatcher string(final String expectedValue) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertString(response.getContentAsByteArray(), getDefinedEncoding(response), expectedValue);
            }
        };
    }

    /**
     * Evaluate the XPath and assert the {@link Double} value found with the
     * given Hamcrest {@link Matcher}.
     */
    public ResultMatcher number(final Matcher<? super Double> matcher) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertNumber(response.getContentAsByteArray(), getDefinedEncoding(response), matcher);
            }
        };
    }

    /**
     * Evaluate the XPath and assert the {@link Double} value found.
     */
    public ResultMatcher number(final Double expectedValue) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertNumber(response.getContentAsByteArray(), getDefinedEncoding(response), expectedValue);
            }
        };
    }

    /**
     * Evaluate the XPath and assert the {@link Boolean} value found.
     */
    public ResultMatcher booleanValue(final Boolean value) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertBoolean(response.getContentAsByteArray(), getDefinedEncoding(response), value);
            }
        };
    }

    /**
     * Evaluate the XPath and assert the {@link Boolean} value found.
     */
    public ResultMatcher attribute(final String name, final String value) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertAttribute(response.getContentAsByteArray(), getDefinedEncoding(response), name, value);
            }
        };
    }

    public ResultMatcher attribute(final String name, final long value) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                MockHttpServletResponse response = result.getResponse();
                cssSelectorExpectationsHelper.assertAttribute(response.getContentAsByteArray(), getDefinedEncoding(response), name, String.valueOf(value));
            }
        };
    }

}