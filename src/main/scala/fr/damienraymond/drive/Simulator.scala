package fr.damienraymond.drive

import fr.damienraymond.drive.model.{AgentBreed, BreedC, BreedNC, InputData}
import fr.damienraymond.drive.services.DataFetcher

/**
  * Created by damien on 16/05/2017.
  */
class Simulator {

  private val dataFetcher: DataFetcher = new DataFetcher

  def simulate(): Unit = {

    dataFetcher.fetch()

    for(year <- 0 until 15){}



  }


  // Todo refactor
  def handleOneDataRow(data: InputData, bandFactor: Float): Option[AgentBreed] = {
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
