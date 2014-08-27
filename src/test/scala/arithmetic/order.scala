package arithmetic

object order extends App {
	import java.text.Collator; 
	import java.util.Locale;
	case class School(name: String, unique: String) extends Ordering[School] {
		 def compare(a:School, b:School) = Collator.getInstance(Locale.CHINESE).compare(a.name, b.name)
	}
	import scala.util.Sorting
	val list = List(new School("郑州", "1"), new School("阿狸", "1"), new School("阿里", "2"), new School("大连", "1"), new School("阿狸", "1"), new School("大连连", "1"))
//	Sorting.quickSort(list)
	list.sortWith { (x, y) => x.name > y.name }
	println(list.sortWith { (x, y) => {
		Collator.getInstance(Locale.CHINESE).compare(x.name, y.name) < 0
		
	}})
}