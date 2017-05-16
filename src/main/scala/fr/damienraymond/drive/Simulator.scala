package fr.damienraymond.drive

import fr.damienraymond.drive.model.{AgentBreed, BreedC, BreedNC, Agent}
import fr.damienraymond.drive.services.{CSVDataFetcher, DataFetcher}

/**
  * Created by damien on 16/05/2017.
  */
class Simulator {

  private val dataFetcher: DataFetcher = new CSVDataFetcher


  def simulate(): Unit = {

    val data = dataFetcher.fetch()

    val bandFactor: Float = 1 // TODO

    val output: Seq[(Int, List[AgentBreed])] =
      (0 until 15).map { year =>
        (
          year,
          data.flatMap(handleOneDataRow(_, bandFactor))
        )
      }


    //println(output.mkString("\n"))

    printResult(output)

  }


  // TODO refactor
  def handleOneDataRow(agent: Agent, bandFactor: Float): Option[AgentBreed] = {

    // TODO age incrementation

    if (agent.autoRenew){
      // Do nothing, maintain Breed<
      Some(agent.agentBreed)
    }else{
      val rand = Math.random() * 3
      val affinity = agent.paymentAtPurchase / agent.attributePrice + (rand * agent.attributePromotions * agent.inertiaForSwitch)
      if(agent.agentBreed == BreedC && affinity < (agent.socialGrade * agent.attributeBrand)){
        // Switch Breed to Breed_NC
        Some(BreedNC)
      } else if (agent.agentBreed == BreedC && affinity < (agent.socialGrade * agent.attributeBrand * bandFactor)){
        // Switch Breed to Breed_C
        Some(BreedC)
      } else {
        None
      }
    }
  }



  def printResult(output: Seq[(Int, List[AgentBreed])]): Unit = {



    output.foreach(e => printOneYear(e._1, e._2))
  }


  def printOneYear(year: Int, data: List[AgentBreed]): Unit = {

    val nbBreadCAgents = data.count(_ == BreedC)
    val nbBreadNCAgents = data.count(_ == BreedNC)

    println()
    println(s"Year ${year + 1}")
    println(s"# Bread_C Agents : $nbBreadCAgents")
    println(s"# Bread_NC Agents : $nbBreadNCAgents")

  }

}
