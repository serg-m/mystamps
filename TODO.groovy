
Steps:
- add org.codehaus.groovy/groovy-all/2.0.4 to Maven dependencies
? add groovy-eclipse-compiler to configuration of maven-compiler-plugin
- define beans with
  <lang:groovy id="xxx" script-source="classpath:XXX.groovy" />
- set time updating (15 sec for test and never for dev profile)

- renamed files: .java -> .groovy
- remove ; at end of line
- removed useless imports:
	java.io.*
	java.lang.*
	java.math.BigDecimal
	java.math.BigInteger
	java.net.*
	java.util.*
- remove public keyword
