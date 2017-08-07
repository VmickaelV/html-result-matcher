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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * A helper class for applying assertions via Css Selectors.
 *
 * @author VIEGAS Mickael
 */
public class CssSelectorExpectationsHelper {

	private final String expression;

	/**
	 * XpathExpectationsHelper constructor.
	 * @param expression the XPath expression
	 * @param args arguments to parameterize the XPath expression with using the
	 * formatting specifiers defined in {@link String#format(String, Object...)}
	 * @throws XPathExpressionException
	 */
	public CssSelectorExpectationsHelper(String expression, Object... args)
			throws XPathExpressionException {

		this.expression = String.format(expression, args);
	}

//	/**
//	 * Parse the content, evaluate the XPath expression as a {@link Node},
//	 * and assert it with the given {@code Matcher<Node>}.
//	 */
//	public void assertNode(byte[] content, String encoding, final Matcher<? super Node> matcher) throws Exception {
//		Document document = parseHtmlByteArray(content, encoding);
//		Node node = document.select(expression);
//		assertThat("XPath " + this.expression, node, matcher);
//	}

	/**
	 * Parse the given XML content to a {@link Document}.
	 * @param html the content to parse
	 * @param encoding optional content encoding, if provided as metadata (e.g. in HTTP headers)
	 * @return the parsed document
	 */
	protected Document parseHtmlByteArray(byte[] html, String encoding) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(html);
		return Jsoup.parse(bis, encoding, "");
	}

	private Element retrieveUniqueElement(Document document) {
		Elements a = document.select(expression);
		assertEquals("Elements from \"" + expression + "\" css selectors is not unique", 1, a.size());
		return a.get(0);
	}

//	/**
//	 * Apply the XPath expression to given document.
//	 * @throws XPathExpressionException
//	 */
//	@SuppressWarnings("unchecked")
//	protected <T> T evaluateXpath(Document document, QName evaluationType, Class<T> expectedClass)
//			throws XPathExpressionException {
//
//		return (T) getXpathExpression().evaluate(document, evaluationType);
//	}

	/**
	 * Apply the XPath expression and assert the resulting content exists.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void exists(byte[] content, String encoding) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Elements elements = document.select(expression);
		assertTrue("CssSelector " + this.expression + " does not exist", elements.size() != 0);
	}

	/**
	 * Apply the XPath expression and assert the resulting content does not exist.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void doesNotExist(byte[] content, String encoding) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Elements a = document.select(expression);
		assertTrue("CssSelector " + this.expression + " exists", a.size() == 0);
	}

	/**
	 * Apply the XPath expression and assert the resulting content with the
	 * given Hamcrest matcher.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertNodeCount(byte[] content, String encoding, Matcher<Integer> matcher) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Elements elements = document.select(expression);
		assertThat("nodeCount for XPath " + this.expression, elements.size(), matcher);
	}

	/**
	 * Apply the XPath expression and assert the resulting content as an integer.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertNodeCount(byte[] content, String encoding, int expectedCount) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Elements elements = document.select(expression);
		assertEquals("nodeCount for XPath " + this.expression, expectedCount, elements.size());
	}

	/**
	 * Apply the XPath expression and assert the resulting content with the
	 * given Hamcrest matcher.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertString(byte[] content, String encoding, Matcher<? super String> matcher) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Element node = retrieveUniqueElement(document);
		assertThat("XPath " + this.expression, node.text(), matcher);
	}

	/**
	 * Apply the XPath expression and assert the resulting content as a String.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertString(byte[] content, String encoding, String expectedValue) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Element element = retrieveUniqueElement(document);
		assertEquals("XPath " + this.expression, expectedValue, element.text());
	}

	/**
	 * Apply the XPath expression and assert the resulting content with the
	 * given Hamcrest matcher.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertNumber(byte[] content, String encoding, Matcher<? super Double> matcher) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Element element = retrieveUniqueElement(document);
		assertThat("XPath " + this.expression, Double.valueOf(element.text()), matcher);
	}

	/**
	 * Apply the XPath expression and assert the resulting content as a Double.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertNumber(byte[] content, String encoding, Double expectedValue) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Element element = retrieveUniqueElement(document);
		assertEquals("XPath " + this.expression, expectedValue, Double.valueOf(element.text()));
	}

	/**
	 * Apply the XPath expression and assert the resulting content as a Boolean.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertBoolean(byte[] content, String encoding, boolean expectedValue) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Element element = retrieveUniqueElement(document);
		assertEquals("XPath " + this.expression, expectedValue, Boolean.parseBoolean(element.text()));
	}










	/**
	 * Apply the XPath expression and assert the resulting content as a String.
	 * @throws Exception if content parsing or expression evaluation fails
	 */
	public void assertAttribute(byte[] content, String encoding, String attributeName, String expectedValue) throws Exception {
		Document document = parseHtmlByteArray(content, encoding);
		Element element = retrieveUniqueElement(document);
		assertEquals("XPath " + this.expression, expectedValue, element.attr(attributeName));
	}

}
