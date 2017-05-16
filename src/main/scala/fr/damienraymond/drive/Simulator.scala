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

    printResult(output)

  }


  def handleOneDataRow(agent: Agent, bandFactor: Float): Option[AgentBreed] = {

    def getAffinity = {
      val rand = Math.random() * 3

      agent.paymentAtPurchase / agent.attributePrice + (rand * agent.attributePromotions * agent.inertiaForSwitch)
    }

    def shouldSwitchToBreedNC(affinity: Double) = {
      agent.agentBreed == BreedC && affinity < (agent.socialGrade * agent.attributeBrand)
    }

    def shouldSwitchToBreedC(affinity: Double) = {
      agent.agentBreed == BreedC && affinity < (agent.socialGrade * agent.attributeBrand * bandFactor)
    }

    // TODO age incrementation ?


    // Do nothing, maintain Breed<
    if (agent.autoRenew){
      return Some(agent.agentBreed)
    }

    val affinity = getAffinity

    // Switch Breed to Breed_NC
    if(shouldSwitchToBreedNC(affinity)){
      return Some(BreedNC)
    }

    // Switch Breed to Breed_C
    if (shouldSwitchToBreedC(affinity)){
      return Some(BreedC)
    }

    None
  }



  def printResult(output: Seq[(Int, List[AgentBreed])]): Unit = {

    output.foreach(e => printOneYear(e._1, e._2))

    // TODO print final
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
