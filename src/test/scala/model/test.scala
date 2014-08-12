package model

object test {

}


trait BaseMsg {
	var fromUser: String = _
	var toUser: String = _
//	val createTime: Long
//	val msgType: String
//	val msgId: Long
}
class TextMsg(fromUser: String, toUser: String, createTime: Long, msgType: String, magId: Long, content: String) extends BaseMsg
//case class TextMsg1(val content: String) extends BaseMsg
//val a = new TextMsg("11", "11", 1, "1", 1, "1")