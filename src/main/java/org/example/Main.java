package org.example;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            File ficheroXSL = new File("./XML/reporte.xsl");

            StreamSource xmlSource = new StreamSource(new File("./XML/datos.xml"));

            FopFactory fopFactory = FopFactory.newInstance(new File("").toURI());

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            OutputStream out;

            File salidaPDF = new File("./XML/reporte.pdf");
            out = new FileOutputStream(salidaPDF.toURI().getPath());
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(ficheroXSL));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}