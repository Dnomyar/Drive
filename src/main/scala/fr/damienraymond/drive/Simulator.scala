package fr.damienraymond.drive

import fr.damienraymond.drive.model.{AgentBreed, BreedC, BreedNC, InputData}
import fr.damienraymond.drive.services.{CSVDataFetcher, DataFetcher}

/**
  * Created by damien on 16/05/2017.
  */
class Simulator {

  private val dataFetcher: DataFetcher = new CSVDataFetcher

  def simulate(): Unit = {

    val data = dataFetcher.fetch()

    val bandFactor: Float = 1 // TODO

    val output =
      (0 until 15).map { year =>
        data
          .flatMap(handleOneDataRow(_, bandFactor))
          .map((year, _))
      }


    println(output.mkString("\n"))

  }


  // TODO refactor
  def handleOneDataRow(data: InputData, bandFactor: Float): Option[AgentBreed] = {

    // TODO age incrementation

    if (data.autoRenew){
      // Do nothing, maintain Breed<
      Some(data.agentBreed)
    }else{
      val rand = Math.random() * 3
      val affinity = data.paymentAtPurchase / data.attributePrice + (rand * data.attributePromotions * data.inertiaForSwitch)
      if(data.agentBreed == BreedC && affinity < (data.socialGrade * data.attributeBrand)){
        // Switch Breed to Breed_NC
        Some(BreedNC)
      } else if (data.agentBreed == BreedC && affinity < (data.socialGrade * data.attributeBrand * bandFactor)){
        // Switch Breed to Breed_C
        Some(BreedC)
      } else {
        None
      }
    }
  }

}
