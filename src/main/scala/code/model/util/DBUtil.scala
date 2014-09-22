package code.model.util

import code.Config
import com.mongodb.MongoOptions
import net.liftweb.mongodb.{ MongoDB, MongoAddress, MongoHost }
import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.ServerAddress
object DBUtil {
  def initModel {
    val mongoConfig = Config.Mongo
    val opts = MongoClientOptions.builder
      .connectionsPerHost(mongoConfig.connectionsPerHost)
      .build
      
    MongoDB.defineDb(
      mongoConfig.DefaultMongoIdentifier,
      new MongoClient(new ServerAddress(mongoConfig.host, mongoConfig.port), opts),
      mongoConfig.db
    )
  }
}
