package com.github.vmickaelv.htmlresultmatcher;

import javax.xml.xpath.XPathExpressionException;

public class CssSelectorResultMatchers {
    /**
     * Access to response body assertions using an CSS selector expression to
     * inspect a specific subset of the body.
     * <p>The CSS selector can be a parameterized string using formatting
     * specifiers as defined in {@link String#format(String, Object...)}.
     * @param cssexpression the CSS selector, optionally parameterized with arguments
     * @param args arguments to parameterize the CSS selector with
     */
    public static CssResultMatchers cssSelector(String cssexpression, Object... args) throws XPathExpressionException {
        return new CssResultMatchers(cssexpression, args);
    }

//    /**
//     * Access to response body assertions using an XPath expression to
//     * inspect a specific subset of the body.
//     * <p>The XPath expression can be a parameterized string using formatting
//     * specifiers as defined in {@link String#format(String, Object...)}.
//     * @param expression the XPath expression, optionally parameterized with arguments
//     * @param namespaces namespaces referenced in the XPath expression
//     * @param args arguments to parameterize the XPath expression with
//     */
//    public static CssResultMatchers cssSelector(String expression, Map<String, String> namespaces, Object... args) {
//
//        return new CssResultMatchers(expression, namespaces, args);
//    }

}
