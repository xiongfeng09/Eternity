package actor

object ActorTest extends App {
	import scala.actors._
	object SeriousActor extends Actor {
		def act() {
			for (i <- 1 to 5) {
			println("To be or not to be.")
			Thread.sleep(1000)
			}
		}
	}
	object SeriousActor1 extends Actor {
		def act() {
			for (i <- 1 to 5) {
			println("go go go.")
			Thread.sleep(1000)
			}
		}
	}
//	SeriousActor.start
//	SeriousActor1.start
	import scala.actors.Actor._
	val echoActor = actor {
		while (true) {
		receive {
			case msg:Int =>
			println("received int: "+ msg)
			}
		}
	}
//	echoActor ! "asdf"
	
	object NameResolver extends Actor {
		import java.net.{InetAddress, UnknownHostException}
		def act() {
			react {
				case (name: String, actor: Actor) =>
					actor ! getIp(name)
					act()
				case "EXIT" =>
					println("Name resolver exiting.")
					// quit
				case msg =>
					println("Unhandled message: "+ msg)
					act()
			}
		}
		def getIp(name: String): Option[InetAddress] = {
			try {
				Some(InetAddress.getByName(name))
			} catch {
				case _:UnknownHostException => None
			}
		}
	}
	
	def sillyActor2 = actor {
		def emoteLater() {
			val mainActor = self
			actor {
				Thread.sleep(1000)
				mainActor ! "Emote"
			}
		}
		var emoted = 0
		emoteLater()
		loop {
			react {
				case "Emote" =>
					println("I'm acting!")
					emoted += 1
					if (emoted < 5)
						emoteLater()
				case msg =>
					println("Received: "+ msg)
			}
		}
	}
	import scala.xml.NodeSeq
	val b = <div>
					1111111111
					<a>aaa</a>
					<b>bbb</b>
					<span>
						<a>saaa</a>
						<b>sbbb</b>
					</span>
				</div>
				def ta(in: scala.xml.NodeSeq): NodeSeq = {
					println(in.size)
					in.filterNot(_.text.trim.isEmpty).map { node =>
//						node.attributes.asAttrMap.foreach {
//							case (k, v) if (v.contains("@")) => {
//								node.attributes.append(scala.xml.MetaData)
//							}
//							case _ @ (k, v) => (k, v)
//						}
						node match {
							case scala.xml.Text(a) => 
						}
						println("节点" + node)
						println("子节点长度" + node.child.size)
						val childs = node.child
						
						if (childs.size == 0) <i>"pppppppppp"</i>
						else if (childs.size == 1) {
							val f = scala.xml.Elem(node.prefix, node.label,node.attributes, node.scope, true, {
//								childs.toArray[scala.xml.Node]:_*
								<div>{ (node.text + "****") }</div>
							})
							println("++++++++++++++++++++++++++++++++++++++++")
							println(f)
							println("----------------------------------------")
							f
						}
						else {
							scala.xml.Elem(node.prefix, node.label,node.attributes, node.scope, true, {
								ta(childs).toArray[scala.xml.Node]:_*
							})
						}
					}
				}
//		println(b.child.size)
//		println(b.child.filterNot(_.text.trim.isEmpty).map(a => println(a.getClass())))
				val k = ta(b)
				println(k)
				k
}