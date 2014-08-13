package code.model.util

import code.Config
import com.mongodb.MongoOptions
import net.liftweb.mongodb.{ MongoDB, MongoAddress, MongoHost }
object DBUtil {
  def initModel {
    val mongoConfig = Config.Mongo
    val mongoOptions = new MongoOptions
    mongoOptions.connectionsPerHost = mongoConfig.connectionsPerHost
    mongoOptions.threadsAllowedToBlockForConnectionMultiplier =
    mongoConfig.threadsAllowedToBlockForConnectionMultiplier
        
    MongoDB.defineDb(
      mongoConfig.DefaultMongoIdentifier,
      MongoAddress(MongoHost(mongoConfig.host, mongoConfig.port, mongoOptions), mongoConfig.db)
    )
  }
}