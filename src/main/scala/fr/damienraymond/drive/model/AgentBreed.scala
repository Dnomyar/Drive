package fr.damienraymond.drive.model

import scala.language.implicitConversions


sealed trait AgentBreed

case object BreedC extends AgentBreed
case object BreedNC extends AgentBreed


object AgentBreed {

  implicit def fromString(str: String): AgentBreed =
    str match {
      case "Breed_C" => BreedC
      case "Breed_NC" => BreedNC
    }

}