<xsl:stylesheet version = "1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de-DE" lang="de-DE">
    <head profile="http://www.w3.org/2000/08/w3c-synd/#">
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <title>Buchbestand</title>
    </head>

    <body>
      <h1 align="center">Buchbestand</h1>
      <xsl:apply-templates select="*" />
    </body>
  </html>
</xsl:template>

<xsl:template match="buch">
  <h3>
    <xsl:value-of select="titel" />
  </h3>
  Autoren:
  <ul>
    <xsl:apply-templates select="autor" />
  </ul>

  <xsl:value-of select="verlag" />

  <hr width="50%" />
</xsl:template>


<xsl:template match="autor">
  <li> <xsl:value-of select="." /> </li>
</xsl:template>


</xsl:stylesheet>