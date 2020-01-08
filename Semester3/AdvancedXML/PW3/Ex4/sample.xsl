<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/liste">
        <html>
            <body>
                <table border="1" width="80%">
                    <tr>
                        <td>Titre</td>
                        <td>Auteur</td>
                        <td>parution</td>
                    </tr>
                        <xsl:for-each select="livre">
                            <xsl:sort
                                select="parution"
                                order="ascending"
                                data-type="number"
                                lang="en"/>
                            <xsl:sort
                                select="titre"
                                order="ascending"
                                data-type="text"
                                lang="en"/>
                            <tr>
                                <td><xsl:value-of select="titre"/></td>
                                <td><xsl:value-of select="auteur"/></td>
                                <td><xsl:value-of select="parution"/></td>
                            </tr>
                        </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>