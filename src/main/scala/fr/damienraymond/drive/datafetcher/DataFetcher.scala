package fr.damienraymond.drive.datafetcher

import fr.damienraymond.drive.model.Agent

/**
  * Created by damien on 16/05/2017.
  */
trait DataFetcher {
  def fetch(): List[Agent]
}
