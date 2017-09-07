package com.sagacify.sonar.scala

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

import com.buransky.plugins.scoverage.measure.ScalaMetrics
import com.buransky.plugins.scoverage.sensor.ScoverageSensor
import com.buransky.plugins.scoverage.widget.ScoverageWidget
import com.ncredinburgh.sonar.scalastyle.ScalastyleQualityProfile
import com.ncredinburgh.sonar.scalastyle.ScalastyleRepository
import com.ncredinburgh.sonar.scalastyle.ScalastyleSensor
import org.sonar.api.config.Settings
import org.sonar.api.Extension
import org.sonar.api.resources.AbstractLanguage
import org.sonar.api.SonarPlugin
import scalariform.lexer.ScalaLexer
import scalariform.lexer.Token

/**
 * Defines Scala as a language for SonarQube.
 */
class Scala(s: Settings) extends AbstractLanguage("scala", "Scala") {

  override def getFileSuffixes: Array[String] = Array("scala")

}

object Scala {

  def tokenize(sourceCode: String, scalaVersion: String): List[Token] =
    ScalaLexer.createRawLexer(sourceCode, false, scalaVersion).toList

}

/**
 * Plugin entry point.
 */
class ScalaPlugin extends SonarPlugin {

  override def getExtensions: java.util.List[Class[_]] =
    ListBuffer[Class[_]] (
      classOf[Scala],
      classOf[ScalaSensor],
      classOf[ScalastyleRepository],
      classOf[ScalastyleQualityProfile],
      classOf[ScalastyleSensor],
      classOf[ScalaMetrics],
      classOf[ScoverageSensor],
      classOf[ScoverageWidget]
    )

  override val toString = getClass.getSimpleName

}
