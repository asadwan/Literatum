<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" omit-xml-declaration="yes" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <h1><xsl:value-of select="//article-title"/></h1>
                <br/>
                <b>
                    <xsl:value-of select="//given-names"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="//surname"/>
                </b>
                <br/>
                <br/>

                <xsl:for-each select="//p">
                    <p><xsl:value-of select="text()"/></p>
                    <br/>
                </xsl:for-each>
                <br/>
                <xsl:value-of select="/copyright-statement"/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>