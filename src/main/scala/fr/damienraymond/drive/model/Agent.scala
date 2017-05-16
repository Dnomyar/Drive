package fr.damienraymond.drive.model


/**
  * Created by damien on 16/05/2017.
  */
case class Agent(agentBreed: AgentBreed,
                 policyId: Double,
                 age: Int,
                 socialGrade: Int,
                 paymentAtPurchase: Int,
                 attributeBrand: Float,
                 attributePrice: Float,
                 attributePromotions: Float,
                 autoRenew: Boolean,
                 inertiaForSwitch: Int)
