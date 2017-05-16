package fr.damienraymond.drive.services

import fr.damienraymond.drive.model.InputData

/**
  * Created by damien on 16/05/2017.
  */
trait DataFetcher {
  def fetch(): List[InputData]
}
