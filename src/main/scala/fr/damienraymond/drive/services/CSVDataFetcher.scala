package fr.damienraymond.drive.services
import fr.damienraymond.drive.Configuration
import fr.damienraymond.drive.Configuration._
import fr.damienraymond.drive.model.{AgentBreed, InputData}

import scala.io.Source

/**
  * Created by damien on 16/05/2017.
  */
class CSVDataFetcher extends DataFetcher {

  override def fetch(): List[InputData] =
    Source.fromFile(INPUT_FILE)
      .getLines()
      .drop(1) // drop first line
      .map(processLine)
      .toList

  // TODO .replaceAll(",", ".") is ugly
  private def processLine(line: String): InputData = {
    line.split(CSV_SEPARATOR)
      .map(_.trim) match {
        case Array(agentBreed,
                   policyId,
                   age,
                   socialGrade,
                   paymentAtPurchase,
                   attributeBrand,
                   attributePrice,
                   attributePromotions,
                   autoRenew,
                   inertiaForSwitch) =>
          InputData(agentBreed,
            policyId.replaceAll(",", ".").toDouble,
            age.toInt,
            socialGrade.toInt,
            paymentAtPurchase.toInt,
            attributeBrand.replaceAll(",", ".").toFloat,
            attributePrice.replaceAll(",", ".").toFloat,
            attributePromotions.replaceAll(",", ".").toFloat,
            booleanFromString(autoRenew),
            inertiaForSwitch.toInt)
      }
  }

  private def booleanFromString(str: String): Boolean =
    str.toInt match {
      case 0 => false
      case 1 => true
    }

}
