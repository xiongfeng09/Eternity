package box

//Empty, EmptyBox, Failure, Full, ParamFailure
object boxTest extends App {
	//generate random
	//1
	import util.Random.nextInt
	
	Stream.continually(nextInt(100)).take(10)
	
	//2
	import util.Random
	val n = 3
	Seq.fill(n)(Random.nextInt)
	
	
	import net.liftweb.common._
	val l = Map(1 -> Full(1), 2 -> Full(2), 3 -> Empty, 4 -> Empty.failMsg("试一试"))
	val f = l.get(nextInt(3))
	
	List.range(0, 20, 1).foreach { _ =>
		val a = l.get(nextInt(4) + 1)
		println("----" + a)
		println("++++" + a.map(_.compoundFailMsg("这是一个空")))
	}
}