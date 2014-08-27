package rest
import net.liftweb.http.rest.RestHelper
import net.liftweb.http.LiftRules

object IssuesService extends RestHelper {
	def init(): Unit = {
		LiftRules.statelessDispatch.append(IssuesService)
		
		serve("issues" / "by-state" prefix {
			case "open" :: Nil XmlGet _ => <p>None open</p>
			case "closed" :: Nil XmlGet _ => <p>None closed</p>
			case "closed" :: Nil XmlDelete _ => <p>All deleted</p>
		})
		
		/*$ curl -H 'Content-Type: application/xml' http://localhost:8080/issues/by-state/open
		<?xml version="1.0" encoding="UTF-8"?>
		<p>None open</p>
		$ curl -X DELETE -H 'Content-Type: application/xml' http://localhost:8080/issues/by-state/closed
		<?xml version="1.0" encoding="UTF-8"?>
		<p>All deleted</p>
		$ curl -X DELETE -H 'Content-Type: application/xml' http://localhost:8080/issues/by-state/closed
		<?xml version="1.0" encoding="UTF-8"?>
		*/
	}
}

object Reunite extends RestHelper {
	import net.liftweb.http.rest.RestHelper
	import net.liftweb.http.LiftRules
	import xml.Text
	private def reunite(name: String, suffix: String) =
	if (suffix.isEmpty) name else name+"."+suffix
	serve {
		case "download" :: file :: Nil Get req =>
		Text("You requested "+reunite(file, req.path.suffix))
	}
	def init() : Unit = {
		LiftRules.statelessDispatch.append(Reunite)
	}
	/*$ curl http://127.0.0.1:8080/download/123.png
	<?xml version="1.0" encoding="UTF-8"?>
	You requested 123.png
	*/
}
