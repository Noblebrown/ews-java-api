/**************************************************************************
 * copyright file="SafeXmlDocument.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SafeXmlDocument.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * XmlDocument that does not allow DTD parsing.
 */
public class SafeXmlDocument extends DocumentBuilder {

	/**
	 * Initializes a new instance of the SafeXmlDocument class.
	 */
	public XMLInputFactory inputFactory;

	public SafeXmlDocument() {
		super();
		inputFactory = XMLInputFactory.newInstance();
	}

	/**
	 * Initializes a new instance of the SafeXmlDocument class with the
	 * specified XSImplementation.
	 * 
	 * @param imp
	 *            The XmlImplementation to use.
	 * @throws NotSupportedException
	 */
	// <remarks>Not supported do to no use within exchange dev code.</remarks>
	public SafeXmlDocument(DocumentBuilder imp) throws NotSupportedException {
		throw new NotSupportedException("Not supported");
	}

	/**
	 * Initializes a new instance of the SafeXmlDocument class with the
	 * specified XmlNameTable.
	 * 
	 * @param nt
	 *            The XmlNameTable to use.
	 */
	public SafeXmlDocument(XmlNameTable nt) {
		super();
		if (inputFactory == null) {
			inputFactory = XMLInputFactory.newInstance();
		}
	}

	/**
	 * Loads the XML document from the specified stream.
	 * 
	 * @param inStream
	 *            The stream containing the XML document to load.
	 * @throws javax.xml.stream.XMLStreamException
	 */
	public void load(InputStream inStream) throws XMLStreamException {
		// not in a using block because
		// the stream doesn't belong to us
		if (inputFactory != null) {
			XMLEventReader reader = inputFactory
			.createXMLEventReader(inStream);

			this.load((InputStream) reader);
		}
	}

	/**
	 * Loads the XML document from the specified URL.
	 * 
	 * @param filename
	 *            URL for the file containing the XML document to load. The URL
	 *            can be either a local file or an HTTP URL (a Web address).
	 */
	public void load(String filename) {
		if (inputFactory != null) {
			FileInputStream inp;

			XMLEventReader reader;
			try {
				inp = new FileInputStream(filename);
				reader = inputFactory.createXMLEventReader(inp);
				this.load((InputStream) reader);
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Loads the XML document from the specified TextReader.
	 * 
	 * @param txtReader
	 *            The TextReader used to feed the XML data into the document.
	 */
	public void load(Reader txtReader) {
		if (inputFactory != null) {

			XMLEventReader reader;
			try {
				reader = inputFactory
				.createXMLEventReader(txtReader);

				this.load((InputStream) reader);
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Loads the XML document from the specified XMLReader.
	 * 
	 * @param reader
	 *            The XMLReader used to feed the XML data into the document.
	 * @throws java.io.IOException
	 * @throws org.xml.sax.SAXException
	 */
	public void load(XMLStreamReader reader) throws SAXException, IOException {

		super.parse((InputStream)reader);
	}

	/**
	 * Loads the XML document from the specified string.
	 * 
	 * @param xml
	 *            String containing the XML document to load.
	 */
	public void loadXml(String xml)
	{
		if(inputFactory!=null)
		{
			try {
				XMLEventReader reader = inputFactory
				.createXMLEventReader(new StringReader(xml));

				this.load((InputStream) reader);
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public DOMImplementation getDOMImplementation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNamespaceAware() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidating() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Document newDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document parse(InputSource is) throws SAXException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityResolver(EntityResolver er) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setErrorHandler(ErrorHandler eh) {
		// TODO Auto-generated method stub
		
	}


}
