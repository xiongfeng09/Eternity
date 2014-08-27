package code

import net.liftweb.util.Props
import net.liftweb.mongodb.MongoIdentifier

object Config {
  object Mongo {
    val host = Props.get("mongo_host").openOrThrowException {
      "the mongo host MUST be defined in props"
    }
    val port = Props.getInt("mongo_port").openOrThrowException {
      "valid mongo port MUST be defined in props"
    }
    val db = Props.get("mongo_db").openOrThrowException {
      "the mongo db MUST be defined in props"
    }
    val connectionsPerHost = Props.getInt("mongo_connectionsPerHost").openOr(10)
    val threadsAllowedToBlockForConnectionMultiplier =
    Props.getInt("mongo_threadsAllowedToBlockForConnectionMultiplier").openOr(5)
        
    object DefaultMongoIdentifier extends MongoIdentifier {
      val jndiName = "default"
     }
  }
}
